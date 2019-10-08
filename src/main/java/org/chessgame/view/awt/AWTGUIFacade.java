package org.chessgame.view.awt;

import org.chessgame.model.Board;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.model.board_element.Spot;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.component.AWTLayer;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.graphics.CaseBoard;
import org.chessgame.view.awt.services.SpriteLoader;
import org.chessgame.view.view_interface.IGUIFacade;
import org.chessgame.view.view_interface.ILayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

public class AWTGUIFacade implements IGUIFacade {

    private Graphics g;
    private GameMonitor monitor;
    private SpriteLoader spriteLoader;
    private Board board;
    private static ChessLogger chessLogger = new ChessLogger(AWTGUIFacade.class);


    @Override
    public void createWindow(String title) {
        this.monitor = new GameMonitor(title);
        this.monitor.init();
        this.monitor.createBoardComponent();
        this.monitor.setLocationRelativeTo(null);
        this.monitor.setVisible(true);
    }

    @Override
    public ILayer createLayer() {
        return new AWTLayer();
    }

    @Override
    public void createSpriteLoader(Board board) {
        this.board = board;
        this.spriteLoader = new SpriteLoader(this.monitor);
        this.spriteLoader.init(board.iterator());
    }

    @Override
    public void dispose() {
        this.monitor.dispose();
    }

    public boolean isClosingRequested() {
        return this.monitor.isClosingRequested();
    }


    @Override
    public boolean beginPaint() {
        if (this.g != null) {
            this.g.dispose();
        }
        this.g = this.monitor.createGraphics();
        return this.g != null;
    }

    @Override
    public void endPaint() {
        if (this.g == null) {
            return;
        }
        this.g.dispose();
        this.g = null;
        this.monitor.switchBuffer();
    }

    @Override
    public void clearBackground() {
        if (this.g == null) {
            chessLogger.log(Level.INFO, "clearBackground: g == null");
            return;
        }
        this.g.setColor(Color.BLACK);
        this.g.fillRect(0, 0, CWindow.WIDTH, CWindow.HEIGHT);
    }

    @Override
    public void drawChars() {
        if(this.g == null) {
            chessLogger.log(Level.INFO, "drawChars: g == null");
            return;
        }

        BoardIterator bi = this.board.iterator();

        while(bi.hasNext()) {
            Spot spot = bi.next();
            BoardElement boardElement = spot.getBoardElement();

            boolean b = bi.isInstanceOfPiece(boardElement);
            if (Boolean.TRUE.equals(b)) {
                Piece piece = (Piece) boardElement;

                BufferedImage image = this.spriteLoader.getBufferedImage(piece);
                g.drawImage(image, spot.getY() * CBoard.TILE_HEIGHT_PX + (CBoard.TILE_HEIGHT_PX / 2 - (image.getHeight() / 2)),
                        spot.getX() * CBoard.TILE_WIDTH_PX + (CBoard.TILE_WIDTH_PX / 2 - (image.getWidth() / 2)),
                        image.getWidth(), image.getHeight(), null);

            }
        }

        bi.resetIterator();
    }

    @Override
    public void drawBackground() {
        if (this.g == null) {
//            System.out.println("drawBackground: " + this.g == null);
            return;
        }
        for(int x = 0; x < (this.monitor.getWidth() / CBoard.TILE_WIDTH_PX); x++) {
            for(int y = 0; y < (this.monitor.getHeight() / CBoard.TILE_HEIGHT_PX); y++) {
                if(((x + y) % 2) == 1) {
                    this.g.setColor(Color.WHITE);
                }
                else {
                    this.g.setColor(Color.DARK_GRAY);
                }
                CaseBoard.draw(x, y, this.g);
            }
        }
    }

    @Override
    public void drawLayer(ILayer layer) {
        if (this.g == null) {
            return;
        }
        if (layer == null) {
            throw new IllegalArgumentException("pas de layer");
        }
        if (!(layer instanceof AWTLayer)) {
            throw new IllegalArgumentException("Type de layer Invalide");
        }
        AWTLayer awtLayer = (AWTLayer) layer;
        awtLayer.draw(this.g);
    }

}
