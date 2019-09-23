package ChessGameTest.Model.Test;

import ChessGame.Model.BoardElement.Pieces.Pawn;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EElement;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGameTest.Model.ClassTest.BoardClassTest;
import junit.framework.TestCase;
import org.junit.Test;



public class TestBoard extends TestCase {

    private BoardClassTest boardClassTest;

    public TestBoard(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.boardClassTest = new BoardClassTest();
        this.boardClassTest.generateBoard();
    }

    public void testGenerateBoard() {
        int count = 0, size = CBoard.TILE_HEIGHT_TAB * CBoard.TILE_WIDTH_TAB;

        BoardIterator bi = this.boardClassTest.iterator();

        while (bi.hasNext()) {
            if(bi.next().getElement() == EElement.EMPTY) {
                count++;
            }
        }

        assertEquals("generation board", size, count);
    }


    public void testGetElement() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean validate = false;

        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);

        if (boardClassTest.getElement(0, 0).getElement() == EElement.EMPTY) {
            isEmptyElement = true;
        }

        if (boardClassTest.getElement(6, 0).getElement() == EElement.PAWN) {
            isPawnElement = true;
        }

        if (isEmptyElement && isPawnElement) {
            validate = true;
        }

        assertTrue("testGetElement", validate);
    }


    public void testInitPiecesPosition() {
        int countWhitePawn = 0, whitePawnNb = 8;

        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);

        for(int x = 0; x < this.boardClassTest.getWidth(); x++) {
            if (this.boardClassTest.getElement(6, x) instanceof Pawn) {
                countWhitePawn++;
            }
        }

        assertEquals("testInitPiecesPosition count number of pawn", whitePawnNb, countWhitePawn);
    }


    //TODO: finish test
    public void testSetEmpty() {
        boolean isEmpty = false;
        int posX = 0, posY = 6;
        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);

        System.out.println(this.boardClassTest.getElement(posY, posX).getElement());

        BoardElement boardElement = this.boardClassTest.getElement(posY, posX);

        if(boardElement.getElement().toString() == EElement.) {
            System.out.println("setEmpty");
            this.boardClassTest.setEmptyElement(posY, posX);
        }

        boardElement = this.boardClassTest.getElement(posY, posX);
        if (boardElement.getElement() == EElement.EMPTY) {
            System.out.println("setTrue");
            isEmpty = true;
        }

        assertTrue("testSetEmpty", isEmpty);
    }

    //TODO: finish test
    public void testMovePiece() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean isPieceMoved = false;
        int initialPosX = 0, initialPosY = 6;
        int movedPosX = 0, movedPosY = 4;

        BoardClassTest boardClassTest = new BoardClassTest();
        boardClassTest.generateBoard();
        boardClassTest.initPiecesPosition(EColorChess.WHITE);

        BoardElement boardElement = boardClassTest.getElement(initialPosY, initialPosX);

        if (boardClassTest.getElement(initialPosY, initialPosX).getElement() == EElement.EMPTY) {
            isEmptyElement = true;
        }

        if (boardClassTest.getElement(movedPosY, movedPosX).getElement() == EElement.PAWN) {
            isPawnElement = true;
        }

        if (isEmptyElement && isPawnElement) {
            isPieceMoved = true;
        }


        assertTrue("testMovePieces", isPieceMoved);
    }

}
