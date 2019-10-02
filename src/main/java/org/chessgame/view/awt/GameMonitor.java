package org.chessgame.view.awt;

import org.chessgame.controller.listener.Mouse;
import org.chessgame.model.Board;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.component.BoardComponent;
import org.chessgame.view.awt.services.SpriteLoader;
import org.chessgame.view.awt.services.TileSelector;

import java.util.logging.Level;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameMonitor extends Frame implements Runnable {

    private Board board;
    private Mouse mouse;
    private static ChessLogger logger = new ChessLogger(GameMonitor.class);

    private SpriteLoader spriteLoader;
    private TileSelector tileSelector;

    private boolean running = false;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);

    private BoardComponent boardComponent;


    public void start() {
        this.running = true;
        Thread thread = new Thread(this, "Chess_Game");
        thread.start();
    }

    public GameMonitor(String title) {
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
        BoardComponent boardComponent = new BoardComponent(d);
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
        logger.log(Level.INFO,"FPS: " + fps);

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
                    logger.log(Level.WARNING, e.toString());
                    Thread.currentThread().interrupt();
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

                boolean b = bi.isInstanceOfPiece(boardElement);
                if (Boolean.TRUE.equals(b)) {
                    Piece piece = (Piece)boardElement;

                    BufferedImage image = this.spriteLoader.getBufferedImage(piece);
                    g.drawImage(image, bi.getY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
                                bi.getX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
                                image.getWidth(), image.getHeight(), null);

                }
            }
            // -------- draw end-------
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, e.toString());
        } finally {
            bs.show();
        }
    }
}
