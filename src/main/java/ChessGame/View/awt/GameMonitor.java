package ChessGame.View.awt;

import ChessGame.Controller.Listener.Mouse;
import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Constant.CWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Component.BoardComponent;
import ChessGame.View.awt.Graphics.BorderTile;
import ChessGame.View.awt.service.SpriteLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameMonitor extends Frame implements Runnable {

    private boolean running = false;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    SpriteLoader spriteLoader;
    private Board board;

    private int pieceSelectedX = 0;
    private int pieceSelectedY = 0;
    private Mouse mouse;

    public GameMonitor(String title, Board board) throws HeadlessException {
        super(title);
        this.board = board;
        this.spriteLoader = new SpriteLoader(board.iterator());
        this.mouse = new Mouse(board);
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.pack();
        this.running = true;

        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    running = false;
                }
            }
        );
    }


    private void createBoardComponent() {
        BoardComponent boardComponent = new BoardComponent(d, this.board.iterator());
        boardComponent.addMouseListener(this.mouse);
        boardComponent.addMouseMotionListener(this.mouse);
        this.add(boardComponent);
        this.pack();
    }


    @Override
    public void run() {
        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();

        this.createBoardComponent();

        while (this.running) {
            long nowTime = System.nanoTime();
            if((nowTime - lastTime) < nanoPerFrame) {
                continue;
            }
            lastTime = nowTime;

            this.render();

            long elapsed = System.nanoTime() - lastTime;
            long millisleep = (nanoPerFrame - elapsed) / 1000000;
            if(millisleep > 0) {
                try {
                    Thread.sleep(millisleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.dispose();
    }

    private void render() {
        Component component = this.getComponent(0);
        if (this.mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            this.pieceSelectedX = this.mouse.getX() / CBoard.TILE_WIDTH_PX;
            this.pieceSelectedY = this.mouse.getY() / CBoard.TILE_HEIGHT_PX;
        }

        if (component instanceof BoardComponent) {
            BoardComponent bc = (BoardComponent) component;
            BufferStrategy bs = bc.getBufferStrategy();

            if (bs == null) {
                bc.createBufferStrategy(2);
                bs = bc.getBufferStrategy();
            }

            Graphics g;
            BoardIterator bi = this.board.iterator();

            try {
                g = bs.getDrawGraphics();
                // -------- draw start-------

                bc.draw(g);

                if(this.pieceSelectedY >= 0 && this.pieceSelectedX >= 0 && this.pieceSelectedY <= CBoard.TILE_HEIGHT_TAB && this.pieceSelectedX <= CBoard.TILE_WIDTH_TAB) {
                    new BorderTile(g, this.pieceSelectedX, this.pieceSelectedY);
                }

                while(bi.hasNext()){
                    BoardElement boardElement = bi.next();

                    if (boardElement instanceof Piece) {
                        Piece piece = (Piece)boardElement;
                        BufferedImage image = this.spriteLoader.getBufferedImage(piece);
                        // posX * tileWidth + (tileWidth / 2 - (imageWidth / 2))
                        g.drawImage(image, piece.getPosY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
                                piece.getPosX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
                                 image.getWidth(), image.getHeight(), null);
                    }
                }

                // -------- draw end-------
                bs.show();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
