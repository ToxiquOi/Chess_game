package ChessGameTest.View.Test;

import ChessGame.Controller.InputController;
import ChessGame.Model.Board;
import ChessGame.Model.BoardElement.Pieces.Pawn;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EElement;
import ChessGame.View.awt.GameMonitor;
import ChessGameTest.View.ClassTest.SpriteLoaderClassTest;
import junit.framework.TestCase;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class TestSpriteLoader extends TestCase {

    private Board board;
    private SpriteLoaderClassTest spriteLoaderClassTest;

    public TestSpriteLoader(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        this.board = new Board();
        InputController inputController = new InputController();

        GameMonitor gm = new GameMonitor("test");

        gm.addBoard(board);
        gm.addMouse(inputController.getMouse());

        this.spriteLoaderClassTest = new SpriteLoaderClassTest(gm);
    }

    public void testInitSpriteLoader() {
        this.spriteLoaderClassTest.init(this.board.iterator());
        ArrayList<HashMap<EElement, BufferedImage>> arrayList = this.spriteLoaderClassTest.getBufferedImages();

        assertNotNull("test init sprite loader", arrayList.get(0));
    }

    public void testGetTextureFromFile() {
        Pawn pawn = new Pawn(EColorChess.WHITE);

        this.spriteLoaderClassTest.getBufferedImages().add(new HashMap<>());
        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull("test get texture from file", this.spriteLoaderClassTest.getBufferedImages().get(0).get(pawn.getEelement()));
    }

    public void testGetBufferedImage() {
        Pawn pawn = new Pawn(EColorChess.WHITE);
        this.spriteLoaderClassTest.getBufferedImages().add(new HashMap<>());
        this.spriteLoaderClassTest.getTextureFromFile(pawn);

        assertNotNull("test get buffered image", this.spriteLoaderClassTest.getBufferedImage(pawn));
    }

}
