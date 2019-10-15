package org.chessgame.view.awt.component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowAdapterCustom extends WindowAdapter {

    private static Logger logger = Logger.getLogger(WindowAdapterCustom.class.getSimpleName());
    GameMonitor monitor;
    BoardComponent boardComponent;

    public WindowAdapterCustom(GameMonitor monitor, BoardComponent boardComponent) {
        this.monitor = monitor;
        this.boardComponent = boardComponent;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        logger.log(Level.INFO, "closing request");
        this.monitor.setClosingRequest(true);
        this.monitor.dispose();
        Thread.currentThread().interrupt();
        super.windowClosing(e);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        if (this.boardComponent != null) {
            this.boardComponent.requestFocusInWindow();
            super.windowClosing(e);
        }
    }
}
