package org.chessgame.view.awt.mode;

import org.chessgame.model.Board;
import org.chessgame.share.interfaces.GameMode;

public class PlayGameMode extends GameMode {



    private final Board board = new Board();


    public void init() {
        this.gui.createWindow(this.title);
        this.gui.createSpriteLoader(this.board);
    }


    public void render() {
        if (!this.gui.beginPaint()) {
            return;
        }
        try {
            this.gui.clearBackground();
            this.gui.drawBackground();
            this.gui.drawChars();

        } finally {
            this.gui.endPaint();
        }
    }


    public void update() {

    }


    public void handleInput() {

    }

}
