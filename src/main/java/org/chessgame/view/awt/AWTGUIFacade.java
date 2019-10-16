package org.chessgame.view.awt;

import org.chessgame.share.exception.MonitorNullException;
import org.chessgame.share.interfaces.*;
import org.chessgame.model.Board;
import org.chessgame.model.abstracts.BoardElement;
import org.chessgame.model.abstracts.Piece;
import org.chessgame.model.board_element.static_element.Spot;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.view.awt.component.AWTImage;
import org.chessgame.view.awt.component.AWTLayer;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.graphics.CaseBoard;
import org.chessgame.view.awt.services.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AWTGUIFacade implements IGUIFacade {

    protected Graphics g;
    protected GameMonitor monitor;
    protected SpriteLoader spriteLoader;
    protected Board board;

    private static Logger chessLogger = Logger.getLogger(AWTGUIFacade.class.getSimpleName());

    @Override
    public void setColor(Color c) {
        if (this.g == null) {
            chessLogger.log(Level.WARNING, "g == null" + '\n');
            return;
        }
        this.g.setColor(c);
    }

    @Override
    public void createWindow(String title) {
        this.monitor = new GameMonitor(title);
        this.monitor.setLayout(new BorderLayout());
        this.monitor.init();
        this.monitor.createBoardPanel(BorderLayout.CENTER);
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

    @Override
    public void setClosingRequest(boolean b) {
        this.monitor.setClosingRequest(b);
    }

    @Override
    public IKeyboard getKeyboard() {
        if (this.monitor == null)
            throw new MonitorNullException("Il faut d'abord créer une fenêtre");
        return this.monitor.getKeyboard();
    }

    @Override
    public IMouse getMouse() {
        if (this.monitor == null)
            throw new MonitorNullException("Il faut d'abord créer une fenêtre");
        return this.monitor.getMouse();
    }

    @Override
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
            chessLogger.log(Level.INFO, "clearBackground: Graphics is null" + '\n');
            return false;
        }
        this.g.setColor(Color.BLACK);
        this.g.fillRect(0, 0, CBoard.BOARD_WIDTH, CBoard.BOARD_HEIGHT);

        return true;
    }

    @Override
    public Boolean drawChars() {
        if(this.g == null) {
            chessLogger.log(Level.INFO, "drawChars: Graphics is null" + '\n');
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

    @Override
    public void saveLayer(AWTLayer layer) {
        BufferedImage image = new BufferedImage(this.monitor.getBoardPanelWidth(), this.monitor.getBoardPanelHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        layer.draw(g);
        g.dispose();
        try {
            ImageIO.write(image, "png", new File(layer.toString()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(AWTGUIFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public IImage createImage(String fileName) {
        AWTImage image = new AWTImage();
        image.loadImage(fileName);
        return image;
    }

    public void drawImage(IImage image, int x, int y) {
        if (this.g == null) {
            return;
        }
        if (image == null)
            throw new IllegalArgumentException("Pas de image");
        if (!(image instanceof AWTImage))
            throw new IllegalArgumentException("Type de image invalide");
        AWTImage awtImage = (AWTImage) image;
        awtImage.draw(this.g, x, y);
    }

    @Override
    public Dimension getTextMetrics(String text) {
        FontMetrics fm = this.g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        return new Dimension(textWidth,textHeight);
    }

    @Override
    public void setTextSize(int size) {
        if (this.g == null)
            return;
        for (int i=2*size;i>=4;i--) {
            Font font = new Font("Arial", Font.PLAIN, i);
            this.g.setFont(font);
            FontMetrics fm = this.g.getFontMetrics();
            if (fm.getHeight() < size) {
                break;
            }
        }
    }

    @Override
    public void drawText(String text, int x, int y, int width, int height) {
        if (this.g == null)
            return;
        FontMetrics fm = this.g.getFontMetrics();
        this.g.clipRect(x, y, width, height);
        this.g.drawString(text, x, y + fm.getAscent());
        this.g.setClip(null);
    }
}
