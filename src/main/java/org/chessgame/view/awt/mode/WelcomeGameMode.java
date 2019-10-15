package org.chessgame.view.awt.mode;

import org.chessgame.share.interfaces.IMouse;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeGameMode extends GameMode {

    private Logger logger = Logger.getLogger(WelcomeGameMode.class.getSimpleName());

    @Override
    public void init() {
        this.gui.createWindow(this.title);
    }

    @Override
    public void render() {
        if (!this.gui.beginPaint()) {
            return;
        }
        try {
            this.gui.clearBackground();

        } finally {
            this.gui.endPaint();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {
        IMouse mouse = this.gui.getMouse();
        logger.log(Level.WARNING, "x: " + mouse.getX());
        if (mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            logger.log(Level.WARNING, "btn1");
        }

        if (mouse.isButtonPressed(MouseEvent.BUTTON2)) {
            logger.log(Level.WARNING, "btn2");
        }
    }
}
