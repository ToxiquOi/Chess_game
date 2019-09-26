package ChessGame.View.awt;

import ChessGame.Controller.Listener.Mouse;
import ChessGame.Model.Board;
import ChessGame.Model.Abstract.BoardElement;
import ChessGame.Model.Abstract.Piece;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Constant.CWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Component.BoardComponent;
import ChessGame.View.awt.service.SpriteLoader;
import ChessGame.View.awt.service.TileSelector;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameMonitor extends Frame implements Runnable {

    private Thread thread;
    private Mouse mouse;

    private SpriteLoader spriteLoader;
    private TileSelector tileSelector;
    private Board board;

    private boolean running = false;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);

    private BoardComponent boardComponent;


    public void start() {
        this.running = true;
        this.thread = new Thread(this, "Chess_Game");
        this.thread.start();
    }

    public GameMonitor(String title) throws HeadlessException {
        super(title);
    }

    public void addBoard(Board board) {
        this.board = board;
    }

    public void addMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setSriteLoader() {
        this.spriteLoader = new SpriteLoader(this);
        this.spriteLoader.init(this.board.iterator());
    }

    public void setTileSelector() {
        this.tileSelector = new TileSelector(this.board, this.mouse);
    }

    public void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.boardComponent = this.createBoardComponent();

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


    public void bench() {
        this.running = true;
        int count = 0;
        long begin = System.nanoTime();

        while(this.running) {
            this.render();
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
        BufferStrategy bs = this.boardComponent.getBufferStrategy();

        if (bs == null) {
            this.boardComponent.createBufferStrategy(2);
            bs = this.boardComponent.getBufferStrategy();
        }

        Graphics g;
        BoardIterator bi = this.board.iterator();

        this.tileSelector.clickChecker();

        try {
            g = bs.getDrawGraphics();

            // -------- draw start-------
            this.boardComponent.draw(g);
            this.tileSelector.drawTile(g);

            while(bi.hasNext()) {
                BoardElement boardElement = bi.next();

                if (bi.isInstanceOfPiece(boardElement)) {
                    Piece piece = (Piece)boardElement;

                    BufferedImage image = this.spriteLoader.getBufferedImage(piece);
                    g.drawImage(image, bi.getY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
                                bi.getX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
                                image.getWidth(), image.getHeight(), null);

                }
            }
            // -------- draw end-------
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            bs.show();
        }
    }
}
