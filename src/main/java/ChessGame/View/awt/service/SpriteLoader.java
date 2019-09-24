package ChessGame.View.awt.service;

import ChessGame.Model.Abstract.BoardElement;
import ChessGame.Model.Abstract.Piece;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Iterator.BoardIterator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SpriteLoader {

    private ArrayList<HashMap<String, BufferedImage>> bufferedImages = new ArrayList<>(2);
    private Frame frame;

    public SpriteLoader(BoardIterator boardIterator, Frame frame) {
        this.frame = frame;

        this.init(boardIterator);
    }

    private void init(BoardIterator boardIterator) {

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


    private void getTextureFromFile(Piece element) {
        String firstChar = "" + element.getElement().toString().charAt(0);
        String elementNameFormated = element.getElement().toString().toLowerCase().replace(firstChar.toLowerCase(), firstChar);
        String spritePath = ((element.getEColorChess() == EColorChess.WHITE)? "White" : "Black") + elementNameFormated + ".png";

        BufferedImage image = null;

        try {
            image = ImageIO.read(new FileInputStream(System.getProperty("user.dir") + "/rsc/Pieces/" + spritePath));
            image = this.makeCompatibleFormatImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.bufferedImages.get(element.getEColorChess() == EColorChess.WHITE? 0 : 1).put(element.getElement().toString(), image);
    }

    private BufferedImage makeCompatibleFormatImage(BufferedImage image) {
        BufferedImage compatibleTexture = this.frame.getGraphicsConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(), Color.TRANSLUCENT);
        Graphics gCompText = compatibleTexture.createGraphics();
        gCompText.drawImage(image, 0, 0, null);

        return compatibleTexture;
    }

    public BufferedImage getBufferedImage(Piece piece) {
        return this.bufferedImages.get((piece.getEColorChess() == EColorChess.WHITE)? 1 : 0).get(piece.getElement().toString());
    }
}
