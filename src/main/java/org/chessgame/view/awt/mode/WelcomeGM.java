package org.chessgame.view.awt.mode;

import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class WelcomeGM extends GameMode {

    private Logger logger = Logger.getLogger(WelcomeGM.class.getSimpleName());

    @Override
    public void init() {
        this.gui.createWindow(this.title);
        this.keyboard = this.gui.getKeyboard();
    }

    @Override
    public void render() {
        this.gui.clearBackground();
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
                setGameMode(new MainMenuGM());
                break;
        }
    }
}
