package ChessGameTest.View.ClassTest;

import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.enumeration.EElement;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.view.awt.service.SpriteLoader;

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
