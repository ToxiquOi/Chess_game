package org.chessgame.view.awt.mode;

import org.chessgame.share.constant.CBoard;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.interfaces.IImage;
import org.chessgame.view.awt.abstracts.GameMode;
import org.chessgame.view.awt.abstracts.MenuGameMode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainMenuGameMode extends MenuGameMode {


    public MainMenuGameMode() {
        this.items.add("Play");
        this.items.add("Options");
        this.items.add("Exit");
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {
        switch (this.keyboard.getLastPressedKey()) {
            case KeyEvent.VK_ESCAPE:
                this.keyboard.consumeLastPressedKey();
                this.gui.setClosingRequest(true);
                break;

            case KeyEvent.VK_UP:
                this.keyboard.consumeLastPressedKey();
                if (this.selectedItem > 0) {
                    this.selectedItem--;
                }
                break;

            case KeyEvent.VK_DOWN:
                this.keyboard.consumeLastPressedKey();
                if (this.selectedItem < this.items.size() - 1) {
                    this.selectedItem++;
                }
                break;

            case KeyEvent.VK_ENTER:
                this.keyboard.consumeLastPressedKey();
                switch (selectedItem) {
                    case 0:
                        this.setGameMode(new PlayGameMode());
                        break;

                    case 1:
                        this.setGameMode(new OptionMenuGameMode());
                        break;

                    case 2:
                        this.gui.setClosingRequest(true);
                        break;
                }
        }
    }
}
