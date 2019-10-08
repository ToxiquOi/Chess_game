package org.chessgame;

import org.chessgame.model.Board;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.view_interface.IGUIFacade;
import org.chessgame.view.view_interface.ILayer;

import java.awt.*;
import java.util.logging.Level;


/**
 *
 * @author tox
 */
public class Main implements Runnable {

    private static final String APP_TITLE = "chess";
    private IGUIFacade gui;
//    private ILayer charsLayer;
    private static ChessLogger logger = new ChessLogger(Main.class);
    private final Board board = new Board();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main chess = new Main();
        chess.setGui(new AWTGUIFacade());
        chess.start();
    }

    public void start() {
        this.init();
        Thread thread = new Thread(this, "Chess_Game");
        thread.start();
    }

    private void setGui(IGUIFacade gui) {
        this.gui = gui;
    }

    private void init() {

    }

    private ILayer defineAllSpriteInLayer(String fileName) {
        ILayer layer = this.gui.createLayer();
        layer.setTileSize(new Dimension(60, 60));
        layer.setTexture(fileName);
        layer.setSpriteCount((2 * 5) * 2);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                int index = x + y * 8;
                layer.setSpriteLocation(index, x * CBoard.TILE_WIDTH_PX, y * CBoard.TILE_HEIGHT_PX);
                int tileIndex = this.board.getElement(y, x).getBoardElement().getCElement();
                if(tileIndex < 0) {
                    tileIndex = 0;
                }
                int tileX = (tileIndex - 1) % layer.getTextureWidth();
                int tileY = (tileIndex - 1) / layer.getTextureHeight();
                if (tileY >= layer.getTextureHeight()) {
                    tileX = 0;
                    tileY = 0;
                }
                layer.setSpriteTexture(tileIndex, tileX, tileY);
            }
        }
        return layer;
    }

    public void run() {
        this.gui.createWindow(APP_TITLE);
        this.gui.createSpriteLoader(this.board);

        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();


        while (!this.gui.isClosingRequested()) {
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
    }

    private void render() {
        if (!gui.beginPaint()){
            logger.log(Level.INFO, "graphics is null");
            System.out.println("graphics is null");
            return;
        }
        try {
            gui.clearBackground();
            gui.drawBackground();
            gui.drawChars();

        } finally {
            gui.endPaint();
        }
    }

    public void bench() {
        int count = 0;
        long begin = System.nanoTime();

        while(!this.gui.isClosingRequested()) {
            this.render();
            count++;
        }

        long end = System.nanoTime();
        double fps = count / ((end - begin) / 1000000000.0);
        logger.log(Level.INFO,"FPS: " + fps);
    }

}
