package org.chessgame;

import org.chessgame.view.awt.mode.MainMenuGameMode;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.view.awt.abstracts.GameMode;
import org.chessgame.view.awt.factory.FlyWeightMenuFactory;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tox
 */
public class Main implements Runnable {

    private static final String APP_TITLE = "chess";
    protected Thread thread;

    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    private FlyWeightMenuFactory menuFactory;
    private GameMode currentMode;
    private IGUIFacade gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main chess = new Main();
        chess.setGuiFacade(new AWTGUIFacade());
        chess.createWindow();
        chess.setMenuFactory(new FlyWeightMenuFactory());
        chess.setGameMode(chess.getMenuFactory().getMenuItems(MainMenuGameMode.class)) ;
        chess.start();
    }

    public void start() {
        Thread thread = new Thread(this, "chess_main");
        thread.start();
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

    public void setMenuFactory(FlyWeightMenuFactory menuFactory) {
        this.menuFactory = menuFactory;
    }

    public FlyWeightMenuFactory getMenuFactory() {
        if (this.menuFactory == null) {
            logger.log(Level.WARNING, "Factory menu is null");
            return null;
        }
        return this.menuFactory;
    }

    public void createWindow() {
        this.gui.createWindow(APP_TITLE);
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

            if (!this.gui.beginPaint()) {
                return;
            }
            try {
                synchronized (this) {
                    this.currentMode.handleInput();
                    this.currentMode.render();
                }
            } finally {
                this.gui.endPaint();
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
