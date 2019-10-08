package org.chessgame.view.awt.services;

import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static java.awt.Transparency.TRANSLUCENT;

public class SpriteLoader implements Serializable {

    private static ChessLogger chessLogger = new ChessLogger(SpriteLoader.class);
    private ArrayList<HashMap<String, BufferedImage>> bufferedImages = new ArrayList<>(2);
    private Frame frame;

    public SpriteLoader( Frame frame) {
        this.frame = frame;
    }

    public void init(BoardIterator boardIterator) {

        this.bufferedImages.add(0, new HashMap<>());
        this.bufferedImages.add(1, new HashMap<>());

        while (boardIterator.hasNext()) {

            BoardElement boardElement = boardIterator.next().getBoardElement();
            boolean b = boardIterator.isInstanceOfPiece(boardElement);
            if (Boolean.TRUE.equals(b)) {
                Piece element = (Piece) boardElement;
                this.getTextureFromFile(element);
            }
        }
    }

    protected ArrayList<HashMap<String, BufferedImage>> getBufferedImages() {
        return this.bufferedImages;
    }

    protected void getTextureFromFile(Piece element) {
        String elementName = element.getClass().getSimpleName();
        String spritePath = ((element.getEColorChess() == EColorChess.WHITE)? "White" : "Black") + elementName + ".png";

        BufferedImage image = null;
        String rootPath = System.getProperty("user.dir");
        if (rootPath.contains("test")) {
            rootPath = rootPath.replace("src/test", "");
        }

        try {
            image = ImageIO.read(new FileInputStream(rootPath + "/src/main/resources/Pieces/" + spritePath));
            image = this.makeCompatibleFormatImage(image);

        } catch (IOException e) {
            chessLogger.log(Level.WARNING, e.toString());
        }

        this.bufferedImages.get(element.getEColorChess() == EColorChess.WHITE? 0 : 1).put(element.getClass().getSimpleName(), image);
    }

    protected BufferedImage makeCompatibleFormatImage(BufferedImage image) {
        BufferedImage compatibleTexture = this.frame.getGraphicsConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), TRANSLUCENT);
        Graphics gCompText = compatibleTexture.createGraphics();
        gCompText.drawImage(image, 0, 0, null);

        return compatibleTexture;
    }

    public BufferedImage getBufferedImage(Piece piece) {
        return this.bufferedImages.get((piece.getEColorChess() == EColorChess.WHITE)? 0 : 1).get(piece.getClass().getSimpleName());
    }
}
