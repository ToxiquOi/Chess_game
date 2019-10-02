package org.chessgame.view.awt.services;

import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EElement;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class SpriteLoader {

    private static ChessLogger chessLogger = new ChessLogger(SpriteLoader.class);
    private ArrayList<HashMap<EElement, BufferedImage>> bufferedImages = new ArrayList<>(2);
    private Frame frame;

    public SpriteLoader( Frame frame) {
        this.frame = frame;
    }

    public void init(BoardIterator boardIterator) {

        this.bufferedImages.add(0, new HashMap<>());
        this.bufferedImages.add(1, new HashMap<>());

        while (boardIterator.hasNext()) {

            BoardElement boardElement = boardIterator.next();
            if (boardIterator.isInstanceOfPiece(boardElement)) {
                Piece element = (Piece) boardElement;
                this.getTextureFromFile(element);
            }
        }
    }

    protected ArrayList<HashMap<EElement, BufferedImage>> getBufferedImages() {
        return this.bufferedImages;
    }

    protected void getTextureFromFile(Piece element) {
        String firstChar = "" + element.getEelement().toString().charAt(0);
        String elementNameFormated = element.getEelement().toString().toLowerCase().replace(firstChar.toLowerCase(), firstChar);
        String spritePath = ((element.getEColorChess() == EColorChess.WHITE)? "White" : "Black") + elementNameFormated + ".png";

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

        this.bufferedImages.get(element.getEColorChess() == EColorChess.WHITE? 0 : 1).put(element.getEelement(), image);
    }

    protected BufferedImage makeCompatibleFormatImage(BufferedImage image) {
        BufferedImage compatibleTexture = this.frame.getGraphicsConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), Color.TRANSLUCENT);
        Graphics gCompText = compatibleTexture.createGraphics();
        gCompText.drawImage(image, 0, 0, null);

        return compatibleTexture;
    }

    public BufferedImage getBufferedImage(Piece piece) {
        return this.bufferedImages.get((piece.getEColorChess() == EColorChess.WHITE)? 0 : 1).get(piece.getEelement());
    }
}
