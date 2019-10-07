package org.chessgame;

import org.chessgame.model.Board;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.viewinterface.IGUIFacade;
import org.chessgame.view.viewinterface.ILayer;

import java.awt.*;
import java.util.logging.Level;

/**
 *
 * @author tox
 */
public class Main implements Runnable {

    private static final String APP_TITLE = "chess";
    private IGUIFacade gui;
    private ILayer charsLayer;
    private static ChessLogger logger = new ChessLogger(Main.class);
    private final Board board = new Board();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Board board = new Board();
//        InputController inputController = new InputController();
//        GameMonitor gm = new GameMonitor(APP_TITLE);
//
//        gm.addBoard(board);
//        gm.addMouse(inputController.getMouse());
//        gm.setSriteLoader();
//        gm.setTileSelector();
//        gm.init();
//
//        gm.setVisible(true);
//        gm.start();
//        gm.bench();

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
        this.charsLayer = this.gui.createLayer();
        this.charsLayer.setTileSize(new Dimension(60, 60));
        this.charsLayer.setTexture("chess_pieces.png");
        this.charsLayer.setSpriteCount(1);
        this.charsLayer.setSpriteTexture(0, 1, 1);
        this.charsLayer.setSpriteLocation(0, 1 * this.charsLayer.getTileWidth(), 4 * this.charsLayer.getTileHeight());
    }

    private void defineAllSpriteInLayer(String fileName) {
        ILayer layer = this.gui.createLayer();
        layer.setTileSize(new Dimension(60, 60));
        layer.setTexture("chess_pieces.png");
        layer.setSpriteCount((2 * 5) * 2);
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                int index = i + j * 8;
                layer.setSpriteLocation(index, i * CBoard.TILE_WIDTH_PX, j * CBoard.TILE_HEIGHT_PX);

            }
        }
    }

    public void run() {
        this.gui.createWindow(APP_TITLE);

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
            return;
        }
        try {
            gui.clearBackground();
            gui.drawBackground();
            this.gui.drawLayer(this.charsLayer);
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
