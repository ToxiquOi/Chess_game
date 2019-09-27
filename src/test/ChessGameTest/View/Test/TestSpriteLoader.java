package ChessGameTest.View.Test;

import ChessGame.Controller.InputController;
import ChessGame.Model.Board;
import ChessGame.Model.BoardElement.Pieces.Pawn;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EElement;
import ChessGame.View.awt.GameMonitor;
import ChessGameTest.View.ClassTest.SpriteLoaderClassTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestSpriteLoader {

    private Board board = new Board();
    private GameMonitor gm = new GameMonitor("test");
    private SpriteLoaderClassTest spriteLoaderClassTest;
    InputController inputController = new InputController();

    @BeforeEach
    void setUp() {
        this.spriteLoaderClassTest = new SpriteLoaderClassTest(gm);
    }

    @Test
    void testInitSpriteLoader() {
        this.spriteLoaderClassTest.init(this.board.iterator());
        ArrayList<HashMap<EElement, BufferedImage>> arrayList = this.spriteLoaderClassTest.getBufferedImages();

        assertNotNull(arrayList.get(0), "test init sprite loader");
    }

    @Test
    void testGetTextureFromFile() {
        Pawn pawn = new Pawn(EColorChess.WHITE);

        this.spriteLoaderClassTest.init(this.board.iterator());
        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull(this.spriteLoaderClassTest.getBufferedImages().get(0).get(pawn.getEelement()), "test get texture from file");
    }

    @Test
    void testGetBufferedImage() {
        Pawn pawn = new Pawn(EColorChess.WHITE);

        this.spriteLoaderClassTest.getBufferedImages().add(new HashMap<>());

        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull(this.spriteLoaderClassTest.getBufferedImage(pawn), "test get buffered image");
    }

}
