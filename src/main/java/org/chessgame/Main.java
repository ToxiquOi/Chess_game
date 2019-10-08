package org.chessgame;

import org.chessgame.model.Board;
import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.share.enumeration.EColorChess;
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
    private static ChessLogger logger = new ChessLogger(Main.class);
    private final Board board = new Board();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main chess = new Main();
        chess.setGui(new AWTGUIFacade());
        chess.init();
        chess.run();
    }

    private void setGui(IGUIFacade gui) {
        this.gui = gui;
    }

    private void init() {
        this.gui.createWindow(APP_TITLE);
    }


    public void run() {
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
        this.gui.dispose();
    }

    private void render() {
        if (!gui.beginPaint()) {
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

        this.gui.createWindow(APP_TITLE);
        this.gui.createSpriteLoader(this.board);

        while(!this.gui.isClosingRequested()) {
            this.render();
            count++;
        }

        long end = System.nanoTime();
        double fps = count / ((end - begin) / 1000000000.0);
        logger.log(Level.INFO,"FPS: " + fps);
    }

}
