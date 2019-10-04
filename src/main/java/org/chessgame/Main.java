package org.chessgame;

import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.awt.awtinterface.IGUIFacade;

import java.util.logging.Level;

/**
 *
 * @author tox
 */
public class Main implements Runnable {

    private static final String APP_TITLE = "chess";
    private IGUIFacade gui;
    private static ChessLogger logger = new ChessLogger(Main.class);

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
        Thread thread = new Thread(this, "Chess_Game");
        thread.start();
    }

    private void setGui(IGUIFacade gui) {
        this.gui = gui;
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
            return;
        }
        try {
            gui.clearBackground();
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

//        this.dispose();
    }

}
