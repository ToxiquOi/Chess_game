package ChessGame.View.awt.service;

import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Iterator.BoardIterator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SpriteLoader {
    private ArrayList<HashMap<String, BufferedImage>> bufferedImages = new ArrayList<>(2);

    public SpriteLoader(BoardIterator boardIterator) {
        this.bufferedImages.add(0, new HashMap<>());
        this.bufferedImages.add(1, new HashMap<>());

        while (boardIterator.hasNext()) {

            BoardElement boardElement = boardIterator.next();
            if (boardElement instanceof Piece) {
                Piece element = (Piece) boardElement;
                this.getTextureFromFile(element);
            }
        }
    }


    private void getTextureFromFile(Piece element) {
        String firstChar = "" + element.getElement().toString().charAt(0);
        String elementNameFormated = element.getElement().toString().toLowerCase().replace(firstChar.toLowerCase(), firstChar);
        String spritePath = ((element.getColorChess() == ColorChess.WHITE)? "White" : "Black") + elementNameFormated + ".png";

        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(System.getProperty("user.dir") + "/rsc/Pieces/" + spritePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.bufferedImages.get(element.getColorChess() == ColorChess.WHITE? 0 : 1).put(element.getElement().toString(), image);
    }

    public BufferedImage getBufferedImage(Piece piece) {
        return this.bufferedImages.get((piece.getColorChess() == ColorChess.WHITE)? 1 : 0).get(piece.getElement().toString());
    }
}
