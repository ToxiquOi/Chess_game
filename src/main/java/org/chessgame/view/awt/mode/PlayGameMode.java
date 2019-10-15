package org.chessgame.view.awt.mode;

import org.chessgame.model.Board;
import org.chessgame.view.awt.abstracts.GameMode;

public class PlayGameMode extends GameMode {



    private final Board board = new Board();

    @Override
    public void init() {
        this.gui.createWindow(this.title);
        this.gui.createSpriteLoader(this.board);
    }

    @Override
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

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {

    }

}
