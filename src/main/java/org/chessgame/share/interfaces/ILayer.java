package org.chessgame.share.interfaces;

import java.awt.*;

public interface ILayer {
    int getTileWidth();
    int getTileHeight();
    int getTextureWidth();
    int getTextureHeight();
    void setTileSize(Dimension d);
    void setTexture(String fileName);
    void setSpriteCount(int count);
    void setSpriteTexture(int index, int tileX, int tileY);
    void setSpriteLocation(int index, int screenX, int screenY);
}
