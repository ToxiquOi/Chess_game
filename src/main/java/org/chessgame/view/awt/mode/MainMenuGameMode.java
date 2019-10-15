package org.chessgame.view.awt.mode;

import org.chessgame.share.constant.CWindow;
import org.chessgame.share.interfaces.IImage;
import org.chessgame.view.awt.abstracts.GameMode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainMenuGameMode extends GameMode {

    private ArrayList<String> items = new ArrayList<>(3);

    private int selectedItem;

    private IImage selectImage;

    public MainMenuGameMode() {
        this.items.add("Play");
        this.items.add("Options");
        this.items.add("Exit");
    }

    @Override
    public void init() {
        this.keyboard = this.gui.getKeyboard();
        this.selectImage = this.gui.createImage("select.png");
    }

    @Override
    public void render() {
        this.gui.clearBackground();
        this.gui.setColor(Color.WHITE);

        Dimension menuSize = this.paintMenu(0, 0, true);
//        this.paintMenu((CWindow.WIDTH - menuSize.width) / 2, (CWindow.HEIGHT - menuSize.height) / 2, false);
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
                        break;

                    case 2:
                        this.gui.setClosingRequest(true);
                        break;
                }
        }
    }

//    private Dimension calcSizeMenu() {
//
//    }

    private Dimension paintMenu(int x, int y, boolean computeSize) {
        int menuWidth = 0;
        int menuHeight = 0;

        for (int i=0;i<items.size();i++) {
            String text = items.get(i);
            Dimension textSize = gui.getTextMetrics(text);
            if (!computeSize) {
                gui.drawText(text, x, y, textSize.width, textSize.height);
                if (i == selectedItem) {
                    gui.drawImage(selectImage, x - selectImage.getWidth(), y);
                }
            }
            y += textSize.height;
            menuHeight += textSize.height;
            if (textSize.width > menuWidth) {
                menuWidth = textSize.width;
            }
        }
        return new Dimension(menuWidth,menuHeight);
    }

}
