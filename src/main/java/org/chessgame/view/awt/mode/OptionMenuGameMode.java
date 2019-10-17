package org.chessgame.view.awt.mode;

import org.chessgame.view.awt.abstracts.MenuGameMode;

import java.awt.event.KeyEvent;

public class OptionMenuGameMode extends MenuGameMode {

    public OptionMenuGameMode() {
        this.items.add("Screen");
        this.items.add("Sound");
        this.items.add("Return");
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
                    case 1:
                        break;

                    case 2:
                        this.setGameMode(new MainMenuGameMode());
                        break;
                }
        }
    }
}
