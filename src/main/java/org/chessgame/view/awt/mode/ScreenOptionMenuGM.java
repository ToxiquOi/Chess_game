package org.chessgame.view.awt.mode;

import org.chessgame.share.interfaces.IImage;
import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.component.GameMonitor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScreenOptionMenuGM extends MenuGameMode {

    GameMonitor monitor;
    Choice c;

    HashMap<String, ArrayList<String>> items;
    private IImage selectImage;

    ArrayList<String> choiceItems;
    int subSelectA = 0;
    int subSelectB = 0;

    public ScreenOptionMenuGM() {
        this.items = new HashMap<>(2);
        this.choiceItems = new ArrayList<>();

        this.choiceItems.add("small");
        this.choiceItems.add("standard");

        this.items.put("window", this.choiceItems);
        this.items.put("board", this.choiceItems);
        this.items.put("return", null);



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
                    case 1:
                        break;
                    case 2:
                        this.setGameMode(this.getMenuFactory().getMenuItems(OptionMenuGM.class));
                        break;
                }
        }
    }

    @Override
    protected Dimension paintMenu(int x, int y, boolean computeSize) {
        int menuWidth = 0;
        int menuHeight = 0;
        int i = 0;

        //get iterator of HashMap entry
        Iterator it = this.items.entrySet().iterator();

        while (it.hasNext()) {
            i++;
            HashMap.Entry<String, ArrayList<String>> pair = (HashMap.Entry) it.next();
            Dimension subMenuSize = new Dimension(0,0);

            this.gui.setTextSize(30);
            String textMenu =  pair.getKey();
            String textSubMenu = null;

            Dimension textMenuSize = this.gui.getTextMetrics(textMenu);

            if (pair.getValue() != null) {
                textSubMenu = pair.getValue().get(0);
                Dimension tmpSubSize = this.gui.getTextMetrics(textSubMenu);
                subMenuSize.setSize(tmpSubSize.width, tmpSubSize.height);
            }

            if(!computeSize) {
                this.gui.drawText(textMenu, x, y, textMenuSize.width, textMenuSize.height);
                // draw selector
                if ( i == selectedItem) {
                    this.gui.drawImage(selectImage, x - selectImage.getWidth(), y - 10);
                }
                if (textSubMenu != null) {
                    ArrayList<String> subMenuList = pair.getValue();
                    if (textMenu == "board") {
                        // TODO: // finish draw submenu
//                        this.gui.drawText();
                    }
                    else if (textMenu == "window") {

                    }
                }
            }

            y += textMenuSize.height + subMenuSize.height;
            menuHeight += textMenuSize.height +  subMenuSize.height;
            if (textMenuSize.width > menuWidth) {
                menuWidth = textMenuSize.width;
            }
            if (subMenuSize.width > menuWidth) {
                menuWidth = subMenuSize.width;
            }
        }


//        for (int i=0; i < this.items.size(); i++) {
//
//            String text = this.items;
//            this.gui.setTextSize(30);
//
//            Dimension textSize = gui.getTextMetrics(text);
//            if (!computeSize) {
//                this.gui.drawText(text, x, y, textSize.width, textSize.height);
//                if (i == selectedItem) {
//                    this.gui.drawImage(selectImage, x - selectImage.getWidth(), y - 10);
//                }
//            }
//            y += textSize.height;
//            menuHeight += textSize.height;
//            if (textSize.width > menuWidth) {
//                menuWidth = textSize.width;
//            }
//        }
       return new Dimension(menuWidth,menuHeight);
    }
}
