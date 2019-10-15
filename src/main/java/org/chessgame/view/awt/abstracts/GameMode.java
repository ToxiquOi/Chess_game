package org.chessgame.view.awt.abstracts;

import org.chessgame.Main;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.share.services.ChessLogger;

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
        this.gui.createWindow(this.title);
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
