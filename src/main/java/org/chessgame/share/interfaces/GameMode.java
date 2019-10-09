package org.chessgame.share.interfaces;

import org.chessgame.Main;

public abstract class GameMode {

    protected String title;
    private Main parent;
    protected IGUIFacade gui;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParent(Main main) {
        this.parent = main;
    }

    public void setGameMode(GameMode mode) {
        parent.setGameMode(mode);
    }

    public void init() {

    }

    public void render() {

    }

    public void update() {

    }

    public void handleInput() {

    }

    public void setGuiFacade(IGUIFacade gui) {
        this.gui = gui;
    }
}
