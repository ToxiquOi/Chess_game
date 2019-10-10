package org.chessgame.view.awt.mode;

import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.view.awt.abstracts.GameMode;

public class WelcomeGameMode extends GameMode {

    @Override
    public void init() {
        this.gui.createWindow(this.title);
    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {

    }
}
