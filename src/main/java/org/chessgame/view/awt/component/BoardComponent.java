package org.chessgame.view.awt.component;

import java.awt.*;

public class BoardComponent extends Canvas {

    private Dimension d;

    public BoardComponent(Dimension d) {
        this.d = d;
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
    }
}
