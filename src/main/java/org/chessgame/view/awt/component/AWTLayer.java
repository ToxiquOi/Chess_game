package org.chessgame.view.awt.component;

import org.chessgame.share.exception.LayerException;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.share.interfaces.ILayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;


public class AWTLayer implements ILayer {

    private static ChessLogger logger = new ChessLogger(AWTLayer.class);

    private int tileWidth;
    private int tileHeight;
    private BufferedImage texture;
    private int textureWidth;
    private int textureHeight;
    private int[][] sprites;


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
        return textureWidth;
    }

    @Override
    public int getTextureHeight() {
        return textureHeight;
    }

    @Override
    public void setTileSize(Dimension d) {
        this.tileWidth = d.width;
        this.tileHeight = d.height;
    }

    @Override
    public void setTexture(String fileName) {
        if (this.tileWidth == 0 || this.tileHeight == 0) {
            throw new LayerException("Taille des tuiles non définie");
        }
        try {
            this.texture = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Pieces/" + fileName)));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        this.textureWidth = this.texture.getWidth() / this.tileWidth;
        this.textureHeight = this.texture.getHeight() / this.tileHeight;
    }

    @Override
    public void setSpriteCount(int count) {
        sprites = new int[count][4];
    }

    @Override
    public void setSpriteTexture(int index, int tileX, int tileY) {
        if (sprites == null) {
            throw new LayerException("Sprites non définis");
        }
        if (index < 0 || index >= this.sprites.length) {
            throw new IllegalArgumentException("Index sprite " + index + " invalide");
        }
        if (tileX < 0 || tileX >= this.textureWidth || tileY < 0 || tileY >= this.textureHeight) {
            throw new IllegalArgumentException("Coordonnées tuiles " + tileX + "," + tileY + " invalides");
        }
        this.sprites[index][0] = tileX;
        this.sprites[index][1] = tileY;
    }

    @Override
    public void setSpriteLocation(int index, int screenX, int screenY) {
        if (sprites == null || index < 0 || index >= sprites.length)
            return;
        this.sprites[index][2] = screenX;
        this.sprites[index][3] = screenY;
    }

    public void draw(Graphics g) {
        if (this.texture == null) {
            throw new LayerException("Texture non chargée");
        }
        if (this.sprites == null) {
            throw new LayerException("Sprites non définis");
        }
        if (this.tileWidth == 0 || this.tileHeight == 0) {
            throw new LayerException("Taille des tuiles non définie");
        }
        for (int[] sprite : this.sprites) {
            int tileX = sprite[0];
            int tileY = sprite[1];
            int screenX = sprite[2];
            int screenY = sprite[3];
            g.drawImage(this.texture, screenX, screenY, screenX + this.tileWidth, screenY + this.tileHeight, tileX * this.tileWidth, tileY * this.tileHeight, tileX * this.tileWidth + this.tileWidth, tileY * this.tileHeight + this.tileHeight, null);
        }
    }
}
