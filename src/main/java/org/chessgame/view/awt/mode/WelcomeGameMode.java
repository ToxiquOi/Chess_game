package org.chessgame.view.awt.mode;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.KeyEvent;

public class WelcomeGameMode extends GameMode {

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
        Keyboard keyboard = gui.getKeyboard();

        switch(keyboard.getLastPressedKey()) {
            case KeyEvent.VK_ESCAPE:
                gui.setClosingRequest(true);
                return;

            case KeyEvent.VK_SPACE:

            case KeyEvent.VK_ENTER:
                keyboard.consumeLastPressedKey();
                setGameMode(new PlayGameMode());
                return;
        }
    }
}
