package ChessGameTest.View.ClassTest;

import ChessGame.Model.Abstract.Piece;
import ChessGame.Share.Enum.EElement;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.service.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class SpriteLoaderClassTest extends SpriteLoader {

    public SpriteLoaderClassTest( Frame frame) {
        super(frame);
    }

    @Override
    public ArrayList<HashMap<EElement, BufferedImage>> getBufferedImages() {
        return super.getBufferedImages();
    }

    @Override
    public void init(BoardIterator boardIterator) {
        super.init(boardIterator);
    }

    @Override
    public BufferedImage getBufferedImage(Piece piece) {
        return super.getBufferedImage(piece);
    }

    @Override
    public void getTextureFromFile(Piece element) {
        super.getTextureFromFile(element);
    }

    @Override
    public BufferedImage makeCompatibleFormatImage(BufferedImage image) {
        return super.makeCompatibleFormatImage(image);
    }
}
