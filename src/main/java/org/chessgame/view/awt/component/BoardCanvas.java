package org.chessgame.view.awt.component;

import java.awt.*;

public class BoardCanvas extends Canvas {

    private Dimension d;

    public BoardCanvas(Dimension d) {
        this.d = d;
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
    }
}
