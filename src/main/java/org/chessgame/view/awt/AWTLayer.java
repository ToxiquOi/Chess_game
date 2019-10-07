package org.chessgame.view.awt;

import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.services.ChessLogger;
import org.chessgame.view.viewinterface.ILayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

import static java.awt.Transparency.TRANSLUCENT;

public class AWTLayer implements ILayer {

    private static ChessLogger logger = new ChessLogger(AWTLayer.class);

    private int tileWidth, tileHeight;
    private BufferedImage texture;
    private int textureWidth, textureHeight;
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
        if (tileWidth == 0 || tileHeight == 0) {
            throw new RuntimeException("Taille des tuiles non définie");
        }
        try {
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        textureWidth = texture.getWidth() / tileWidth;
        textureHeight = texture.getHeight() / tileHeight;
    }

    @Override
    public void setSpriteCount(int count) {
        sprites = new int[count][4];
    }

    @Override
    public void setSpriteTexture(int index, int tileX, int tileY) {
        if (sprites == null) {
            throw new RuntimeException("Sprites non définis");
        }
        if (index < 0 || index >= sprites.length) {
            throw new IllegalArgumentException("Index sprite " + index + " invalide");
        }
        if (tileX < 0 || tileX >= textureWidth || tileY < 0 || tileY >= textureHeight) {
            throw new IllegalArgumentException("Coordonnées tuiles " + tileX + "," + tileY + " invalides");
        }
        sprites[index][0] = tileX;
        sprites[index][1] = tileY;
    }

    @Override
    public void setSpriteLocation(int index, int screenX, int screenY) {
        if (sprites == null || index < 0 || index >= sprites.length)
            return;
        sprites[index][2] = screenX;
        sprites[index][3] = screenY;
    }

    public void draw(Graphics g) {
        if (texture == null) {
            throw new RuntimeException("Texture non chargée");
        }
        if (sprites == null) {
            throw new RuntimeException("Sprites non définis");
        }
        if (tileWidth == 0 || tileHeight == 0) {
            throw new RuntimeException("Taille des tuiles non définie");
        }
        for (int i = 0; i < sprites.length; i++) {
            int tileX = sprites[i][0];
            int tileY = sprites[i][1];
            int screenX = sprites[i][2];
            int screenY = sprites[i][3];
            g.drawImage(texture, screenX, screenY, screenX + tileWidth, screenY + tileHeight, tileX * tileWidth, tileY * tileHeight, tileX * tileWidth + tileWidth, tileY * tileHeight + tileHeight, null);
        }
    }

//    protected BufferedImage makeCompatibleFormatImage(BufferedImage image) {
//        BufferedImage compatibleTexture = this.frame.getGraphicsConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), TRANSLUCENT);
//        Graphics gCompText = compatibleTexture.createGraphics();
//        gCompText.drawImage(image, 0, 0, null);
//
//        return compatibleTexture;
//    }
}
