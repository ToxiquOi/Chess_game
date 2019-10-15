package org.chessgame.view.awt.mode;

import org.chessgame.share.interfaces.IKeyboard;
import org.chessgame.share.interfaces.IMouse;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeGameMode extends GameMode {

    private Logger logger = Logger.getLogger(WelcomeGameMode.class.getSimpleName());

    @Override
    public void init() {
        this.gui.createWindow(this.title);
        this.keyboard = this.gui.getKeyboard();
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
        switch(this.keyboard.getLastPressedKey()) {
            case KeyEvent.VK_ESCAPE:
                gui.setClosingRequest(true);
                break;
            case KeyEvent.VK_ENTER:
                keyboard.consumeLastPressedKey();
                setGameMode(new MainMenuGameMode());
                break;
        }
    }
}
