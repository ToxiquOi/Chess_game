package ChessGameTest.View;

import org.chessgame.model.Board;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.services.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestSpriteLoader {

    public static class SpriteLoaderClassTest extends SpriteLoader {

        public SpriteLoaderClassTest( Frame frame) {
            super(frame);
        }

        @Override
        public ArrayList<HashMap<String, BufferedImage>> getBufferedImages() {
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

    private Board board = new Board();
    private GameMonitor gm = new GameMonitor("test");
    private SpriteLoaderClassTest spriteLoaderClassTest;

    @BeforeEach
    void setUp() {
        this.spriteLoaderClassTest = new SpriteLoaderClassTest(gm);
    }

    @Test
    void testInitSpriteLoader() {
        this.spriteLoaderClassTest.init(this.board.iterator());
        ArrayList<HashMap<String, BufferedImage>> arrayList = this.spriteLoaderClassTest.getBufferedImages();

        assertNotNull(arrayList.get(0), "test init sprite loader");
    }

    @Test
    void testGetTextureFromFile() {
        Pawn pawn = new Pawn(EColorChess.WHITE);

        this.spriteLoaderClassTest.init(this.board.iterator());
        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull(this.spriteLoaderClassTest.getBufferedImages().get(0).get(pawn.getClass().getSimpleName()), "test get texture from file");
    }

    @Test
    void testGetBufferedImage() {
        Pawn pawn = new Pawn(EColorChess.WHITE);

        this.spriteLoaderClassTest.getBufferedImages().add(new HashMap<>());
        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull(this.spriteLoaderClassTest.getBufferedImage(pawn), "test get buffered image");
    }

}
