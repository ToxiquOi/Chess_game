package org.chessgame;

import org.chessgame.view.awt.mode.PlayGameMode;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.view.awt.abstracts.GameMode;

import javax.swing.*;
import java.util.logging.Level;


/**
 *
 * @author tox
 */
public class Main implements Runnable {

    private static final String APP_TITLE = "chess";
    protected Thread thread;

    private static ChessLogger logger = new ChessLogger(Main.class);
    GameMode currentMode;
    IGUIFacade gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main chess = new Main();
        chess.setGuiFacade(new AWTGUIFacade());
        chess.setGameMode(new PlayGameMode());
        chess.run();
    }


    private void setGuiFacade(IGUIFacade gui) {
        this.gui = gui;
    }

    public synchronized void setGameMode(GameMode mode) {
        try {
            this.currentMode = mode;
            this.currentMode.setParent(this);
            this.currentMode.setGuiFacade(this.gui);
            this.currentMode.setTitle(APP_TITLE);
            this.currentMode.init();
        } catch(Exception ex) {
            logger.log(Level.WARNING, ex.toString());
            JOptionPane.showMessageDialog(null, ex.toString(),"Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void run() {

        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();


        while (!this.gui.isClosingRequested()) {
            long nowTime = System.nanoTime();
            if((nowTime - lastTime) < nanoPerFrame) {
                continue;
            }
            lastTime = nowTime;

            synchronized (this) {
                this.currentMode.handleInput();
                this.currentMode.render();
            }

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
        Thread.currentThread().interrupt();
    }


    public void bench() {
        int count = 0;
        long begin = System.nanoTime();

        while(!this.gui.isClosingRequested()) {
            this.currentMode.render();
            count++;
        }

        long end = System.nanoTime();
        double fps = count / ((end - begin) / 1000000000.0);
        logger.log(Level.INFO,"FPS: " + fps);
    }

}
