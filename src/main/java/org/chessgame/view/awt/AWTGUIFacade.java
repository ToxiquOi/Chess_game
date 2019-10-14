package org.chessgame.view.awt;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.controller.listener.Mouse;
import org.chessgame.share.exception.MonitorNullException;
import org.chessgame.share.interfaces.IKeyboard;
import org.chessgame.share.interfaces.IMouse;
import org.chessgame.model.Board;
import org.chessgame.model.abstracts.BoardElement;
import org.chessgame.model.abstracts.Piece;
import org.chessgame.model.board_element.static_element.Spot;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.component.AWTLayer;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.graphics.CaseBoard;
import org.chessgame.view.awt.services.SpriteLoader;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.share.interfaces.ILayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

public class AWTGUIFacade implements IGUIFacade {

    protected Graphics g;
    protected GameMonitor monitor;
    protected SpriteLoader spriteLoader;
    protected Board board;

    private static ChessLogger chessLogger = new ChessLogger(AWTGUIFacade.class);


    @Override
    public void createWindow(String title) {
        this.monitor = new GameMonitor(title);
        this.monitor.setLayout(new BorderLayout());
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

    public void setClosingRequest(boolean b) {
        this.monitor.setClosingRequest(b);
    }

    public Keyboard getKeyboard() {
        if (this.monitor == null)
            throw new MonitorNullException("Il faut d'abord créer une fenêtre");
        return this.monitor.getKeyboard();
    }

    public Mouse getMouse() {
        if (this.monitor == null)
            throw new MonitorNullException("Il faut d'abord créer une fenêtre");
        return this.monitor.getMouse();
    }

    public boolean isClosingRequested() {
        return this.monitor.isClosingRequested();
    }


    @Override
    public Boolean beginPaint() {
        if (this.g != null) {
            this.g.dispose();
            return null;
        }
        this.g = this.monitor.createGraphics();
        boolean b = this.g != null;
        return b;
    }

    @Override
    public Boolean endPaint() {
        if (this.g == null) {
            return false;
        }

        this.g.dispose();
        this.g = null;
        this.monitor.switchBuffer();
        return true;
    }

    @Override
    public Boolean clearBackground() {
        if (this.g == null) {
            chessLogger.log(Level.INFO, "clearBackground: Graphics is null");
            return false;
        }
        this.g.setColor(Color.BLACK);
        this.g.fillRect(0, 0, CWindow.WIDTH, CWindow.HEIGHT);

        return true;
    }

    @Override
    public Boolean drawChars() {
        if(this.g == null) {
            chessLogger.log(Level.INFO, "drawChars: Graphics is null");
            return false;
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
        return true;
    }

    @Override
    public Boolean drawBackground() {
        if (this.g == null) {
            return false;
        }
        for(int x = 0; x < CBoard.TILE_HEIGHT_TAB; x++) {
            for(int y = 0; y < CBoard.TILE_WIDTH_TAB; y++) {
                if(((x + y) % 2) == 1) {
                    this.g.setColor(Color.WHITE);
                }
                else {
                    this.g.setColor(Color.DARK_GRAY);
                }
                CaseBoard.draw(x, y, this.g);
            }
        }

        return true;
    }

    @Override
    public Boolean drawLayer(ILayer layer) {
        if (this.g == null) {
            return false;
        }
        if (layer == null) {
            throw new IllegalArgumentException("pas de layer");
        }
        if (!(layer instanceof AWTLayer)) {
            throw new IllegalArgumentException("Type de layer Invalide");
        }
        AWTLayer awtLayer = (AWTLayer) layer;
        awtLayer.draw(this.g);
        return true;
    }

}
