package org.chessgame.view.awt.mode;

import org.chessgame.model.Board;
import org.chessgame.share.interfaces.IKeyboard;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.event.KeyEvent;

public class PlayGameMode extends GameMode {

    private final Board board = new Board();

    @Override
    public void init() {
        this.keyboard = this.gui.getKeyboard();
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
        if (this.keyboard.getLastPressedKey() == KeyEvent.VK_ESCAPE) {
            this.gui.setClosingRequest(true);
        }
    }

}
