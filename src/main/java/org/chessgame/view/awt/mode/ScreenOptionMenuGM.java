package org.chessgame.view.awt.mode;

import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.component.GameMonitor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ScreenOptionMenuGM extends MenuGameMode {

    GameMonitor monitor;
    Choice c;
    ArrayList<String> choiceItems;

    public ScreenOptionMenuGM() {
        this.items.add("return");
        this.choiceItems = new ArrayList<>();
        this.choiceItems.add("small");
        this.choiceItems.add("standard");
    }

    @Override
    public void init() {
        super.init();
        if(this.c == null)
        {
            this.c = this.gui.addChoiceSelector(this.choiceItems, 100, 100, 20, 20);
        }
    }

    @Override
    public void render() {
        super.render();
        this.gui.drawChoiceSelector(this.c);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void handleInput() {
        switch (this.keyboard.getLastPressedKey()) {
            case KeyEvent.VK_ESCAPE:
                this.keyboard.consumeLastPressedKey();
                this.setGameMode(this.getMenuFactory().getMenuItems(OptionMenuGM.class));
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
                        this.setGameMode(this.getMenuFactory().getMenuItems(OptionMenuGM.class));
                        break;
                    case 1:
                    case 2:
                        break;
                }
        }
    }
}
