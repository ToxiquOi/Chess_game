package org.chessgame.view.awt.mode;

import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.component.GameMonitor;

import java.awt.*;

public class ScreenOptionMenuGM extends MenuGameMode {

    GameMonitor monitor;
    Choice c;

    public ScreenOptionMenuGM() {
        this.items.add("return");
    }

    @Override
    public void init() {
        super.init();
        this.c = new Choice();
        this.monitor = this.gui.getMonitor();
        c.setBounds(100,100, 75, 75);
        c.add("small");
        c.add("standard");
        c.setVisible(true);
        this.monitor.add(c);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void handleInput() {

    }
}
