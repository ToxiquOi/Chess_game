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

    Thread thread;

    private boolean running = false;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private SpriteLoader spriteLoader;
    private Board board;

    private int pieceSelectedX = 0;
    private int pieceSelectedY = 0;
    private Color colorSelect = null;

    private Mouse mouse;

    public void start() {
        this.running = true;
        this.thread = new Thread(this, "Chess_Game");
        this.thread.start();
    }

    public GameMonitor(String title, Board board, Mouse mouse) throws HeadlessException {
        super(title);
        this.board = board;
        this.mouse = mouse;
        this.spriteLoader = new SpriteLoader(board.iterator(), this);
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

        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    running = false;
                }
            }
        );
    }


    private BoardComponent createBoardComponent() {
        BoardComponent boardComponent = new BoardComponent(d, this.board.iterator());
        boardComponent.addMouseListener(this.mouse);
        boardComponent.addMouseMotionListener(this.mouse);
        this.add(boardComponent);
        this.pack();
        return boardComponent;
    }

    private void selectTile() {
        this.pieceSelectedX = this.mouse.getX() / CBoard.TILE_WIDTH_PX;
        this.pieceSelectedY = this.mouse.getY() / CBoard.TILE_HEIGHT_PX;
    }

    public void bench() {
        this.running = true;
        int count = 0;
        long begin = System.nanoTime();

        BoardComponent boardComponent = this.createBoardComponent();

        while(this.running) {
            this.render(boardComponent);
            count++;
        }

        long end = System.nanoTime();
        double fps = count / ((end - begin) / 1000000000.0);
        System.out.println("FPS: " + fps);

        this.dispose();
    }

    @Override
    public void run() {
        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();

        BoardComponent boardComponent = this.createBoardComponent();

        while (this.running) {
            long nowTime = System.nanoTime();
            if((nowTime - lastTime) < nanoPerFrame) {
                continue;
            }
            lastTime = nowTime;

            this.render(boardComponent);

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

    private void render(BoardComponent bc) {
        BufferStrategy bs = bc.getBufferStrategy();

        if (bs == null) {
            bc.createBufferStrategy(2);
            bs = bc.getBufferStrategy();
        }

        Graphics g;
        BoardIterator bi = this.board.iterator();


        if (this.mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            this.selectTile();
            this.colorSelect = new Color(0x128E00);

        } else if (this.mouse.isButtonPressed(MouseEvent.BUTTON3)) {
            this.selectTile();
            if(this.pieceSelectedY > 0 && this.pieceSelectedX > 0 && this.pieceSelectedY <= CBoard.TILE_HEIGHT_TAB && this.pieceSelectedX <= CBoard.TILE_WIDTH_TAB && this.colorSelect != null) {
                this.colorSelect = new Color(0xEB0900);
                BoardElement boardElement = this.board.getElement(this.mouse.getY() / CBoard.TILE_HEIGHT_PX, this.mouse.getX() / CBoard.TILE_WIDTH_PX);
                if(boardElement instanceof Piece) {
                    Piece piece = (Piece) boardElement;
                    piece.die();
                }
            }
        }

        try {
            g = bs.getDrawGraphics();
            // -------- draw start-------
            bc.draw(g);



            if(this.pieceSelectedY > 0 && this.pieceSelectedX > 0 && this.pieceSelectedY < CBoard.TILE_HEIGHT_TAB && this.pieceSelectedX < CBoard.TILE_WIDTH_TAB && this.colorSelect != null) {
                new BorderTile(g, this.pieceSelectedX, this.pieceSelectedY, this.colorSelect);
            }


            while(bi.hasNext()) {
                BoardElement boardElement = bi.next();

                if (boardElement instanceof Piece) {
                    Piece piece = (Piece)boardElement;

                    if(piece.isAlive()) {
                        // get image
                        BufferedImage image = this.spriteLoader.getBufferedImage(piece);

                        // draw sprite
                        g.drawImage(image, piece.getPosY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
                                piece.getPosX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
                                image.getWidth(), image.getHeight(), null);
                    }
                }
            }

            // -------- draw end-------
            bs.show();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
