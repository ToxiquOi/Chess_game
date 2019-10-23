package org.chessgame.view.awt.mode;

import org.chessgame.model.Board;
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
        this.gui.clearBackground();
        this.gui.drawBackground();
        this.gui.drawChars();
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
