package org.chessgame.view.awt;

import org.chessgame.view.awt.awtinterface.ILayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AWTLayer implements ILayer {

    int tileWidth, tileHeight;
    BufferedImage bufferedImage;


    @Override
    public int getTileWidth() {
        return this.tileWidth;
    }

    @Override
    public int getTileHeight() {
        return this.tileHeight;
    }

    @Override
    public int getTextureWidth() {
        return 0;
    }

    @Override
    public int getTextureHeight() {
        return 0;
    }

    @Override
    public void setTileSize(Dimension d) {

    }

    @Override
    public void setTexture(String fileName) {

    }

    @Override
    public void setSpriteCount(int count) {

    }

    @Override
    public void setSpriteTexture(int index, int tileX, int tileY) {

    }

    @Override
    public void setSpriteLocation(int index, int screenX, int screenY) {

    }
}
