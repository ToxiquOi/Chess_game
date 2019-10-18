package org.chessgame.view.awt.abstracts;

import org.chessgame.Main;
import org.chessgame.share.interfaces.IGUIFacade;
import org.chessgame.share.interfaces.IKeyboard;
import org.chessgame.view.awt.factory.FlyWeightMenuFactory;

public abstract class GameMode {

    protected String title;
    private Main parent;
    protected IGUIFacade gui;
    protected IKeyboard keyboard;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParent(Main main) {
        this.parent = main;
    }

    protected FlyWeightMenuFactory getMenuFactory() {
        return this.parent.getMenuFactory();
    }

    protected void setGameMode(GameMode mode) {
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
