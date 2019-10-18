package org.chessgame.view.awt.abstracts;

import org.chessgame.share.constant.CBoard;
import org.chessgame.share.interfaces.IImage;

import java.awt.*;
import java.util.ArrayList;

public class MenuGameMode extends GameMode {

    protected ArrayList<String> items = new ArrayList<>(3);

    protected int selectedItem = 0;

    private IImage backgroundImage;

    private IImage selectImage;

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
        this.paintMenu((CBoard.BOARD_WIDTH - menuSize.width) / 2, (CBoard.BOARD_HEIGHT - menuSize.height) / 2, false);
    }

    protected Dimension paintMenu(int x, int y, boolean computeSize) {
        int menuWidth = 0;
        int menuHeight = 0;

        for (int i=0; i < items.size(); i++) {
            String text = items.get(i);
            this.gui.setTextSize(30);
            Dimension textSize = gui.getTextMetrics(text);
            if (!computeSize) {
                this.gui.drawText(text, x, y, textSize.width, textSize.height);
                if (i == selectedItem) {
                    this.gui.drawImage(selectImage, x - selectImage.getWidth(), y - 10);
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
