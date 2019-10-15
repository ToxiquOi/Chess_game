package org.chessgame.view.awt.component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowAdapterCustom extends WindowAdapter {

    private static Logger logger = Logger.getLogger(WindowAdapterCustom.class.getSimpleName());
    GameMonitor monitor;
    BoardPanel boardPanel;

    public WindowAdapterCustom(GameMonitor monitor, BoardPanel boardPanel) {
        this.monitor = monitor;
        this.boardPanel = boardPanel;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        logger.log(Level.INFO, "closing request");
        this.monitor.setClosingRequest(true);
        this.monitor.dispose();
        Thread.currentThread().interrupt();
        System.exit(0);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        if (this.boardPanel != null) {
            this.boardPanel.requestFocusInWindow();
        }
    }
}
