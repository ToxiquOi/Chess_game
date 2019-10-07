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
//import org.chessgame.view.awt.services.SpriteLoader;
//import org.chessgame.view.awt.services.TileSelector;

import java.util.logging.Level;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameMonitor extends Frame {

    private BufferStrategy bs;
    private Dimension d = new Dimension(CWindow.WIDTH, CWindow.HEIGHT);
    private BoardComponent boardComponent;
    private boolean closingRequested = false;


    public GameMonitor(String title) {
        super(title);
    }

    public boolean isClosingRequested() {
        return this.closingRequested;
    }

    public void init() {
        this.setSize(this.d);
        this.setResizable(false);
        this.createBoardComponent();
//        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingRequested = true;
                dispose();
            }
        });
        this.closingRequested = false;
    }

    private void createBoardComponent() {
        this.boardComponent = new BoardComponent(d);
        this.add(boardComponent);
        this.pack();
    }

    public Graphics createGraphics() {
        if (this.bs == null) {
            this.boardComponent.createBufferStrategy(2);
            this.bs = this.boardComponent.getBufferStrategy();
        }

        return this.boardComponent.getGraphics();
    }

    public void switchBuffer() {
        if(this.bs != null) {
            this.bs.show();
        }
    }

//    private void render() {
//
//        Graphics g;
//        BoardIterator bi = this.board.iterator();
//
//        this.tileSelector.clickChecker();
//
//        try {
//            g = this.createGraphics();
//
//            // -------- draw start-------
//            this.boardComponent.draw(g);
//            this.tileSelector.drawTile(g);
//
//            while(bi.hasNext()) {
//                BoardElement boardElement = bi.next();
//
//                boolean b = bi.isInstanceOfPiece(boardElement);
//                if (Boolean.TRUE.equals(b)) {
//                    Piece piece = (Piece)boardElement;
//
//                    BufferedImage image = this.spriteLoader.getBufferedImage(piece);
//                    g.drawImage(image, bi.getY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
//                                bi.getX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
//                                image.getWidth(), image.getHeight(), null);
//
//                }
//            }
//            // -------- draw end-------
//        } catch (NullPointerException e) {
//            logger.log(Level.WARNING, e.toString());
//        } finally {
//            bs.show();
//        }
//    }
}
