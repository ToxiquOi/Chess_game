package org.chessgame.view.awt.mode;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class WelcomeGameMode extends GameMode {

    @Override
    public void init() {
        this.keyboard = new Keyboard();
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
        switch (this.keyboard.getLastPressedKey()) {
            case KeyEvent.VK_ESCAPE:
                this.keyboard.consumeLastPressedKey();
                this.setGameMode(new PlayGameMode());
                return;

            case KeyEvent.VK_SPACE:
                this.keyboard.consumeLastPressedKey();
                logger.log(Level.INFO, "space pressed");
        }
    }
}
