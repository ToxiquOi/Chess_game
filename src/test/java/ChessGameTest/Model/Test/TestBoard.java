package ChessGameTest.Model.Test;

import org.chessgame.model.abstract_class.Piece;
import org.chessgame.model.abstract_class.Position;
import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.enumeration.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.iterator.BoardIterator;
import ChessGameTest.Model.ClassTest.BoardClassTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestBoard {

    private BoardClassTest boardClassTest;

    @BeforeEach
    void setUp() {
        this.boardClassTest = new BoardClassTest();
        this.boardClassTest.generateBoard();
    }

    @Test
    void testGenerateBoard() {
        int count = 0, size = CBoard.TILE_HEIGHT_TAB * CBoard.TILE_WIDTH_TAB;

        BoardIterator bi = this.boardClassTest.iterator();

        while (bi.hasNext()) {
            if(bi.next().getEelement() == CElement.EMPTY) {
                count++;
            }
        }

        assertEquals(size, count, "generation board");
    }

    @Test
    void testGetElement() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean validate = false;

        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);
        if (boardClassTest.getElement(1, 0).getEelement() == CElement.PAWN) {
            isEmptyElement = true;
        }

        if (boardClassTest.getElement(2, 0).getEelement() == CElement.EMPTY) {
            isPawnElement = true;
        }

        if (isEmptyElement && isPawnElement) {
            validate = true;
        }

        assertTrue(validate, "testGetElement");
    }

    @Test
    void testInitPiecesPosition() {
        int countWhitePawn = 0, whitePawnNb = 8;

        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);

        for(int x = 0; x < this.boardClassTest.getWidth(); x++) {
            if (this.boardClassTest.getElement(1, x) instanceof Pawn) {
                countWhitePawn++;
            }
        }

        assertEquals(whitePawnNb, countWhitePawn, "testInitPiecesPosition count number of pawn");
    }

    @Test
    void testSetEmpty() {
        boolean isEmpty = false;
        int posX = 0, posY = 0;
        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);
        BoardIterator boardIterator = this.boardClassTest.iterator();
        BoardElement boardElement = null;

        while (boardIterator.hasNext() && !this.boardClassTest.isInstanceOfPiece(boardElement)) {
            boardElement = boardIterator.next();
            posX = boardIterator.getX();
            posY = boardIterator.getY();
        }

        this.boardClassTest.setEmptyElement(posY, posX);

        boardElement = this.boardClassTest.getElement(posY, posX);

        if (boardElement instanceof Empty) {
            isEmpty = true;
        }

        assertTrue(isEmpty, "testSetEmpty");
    }

    @Test
    void testFindPiece() {
        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);
        boolean validate = false;
        int posY = 1, posX = 0;
        Position pos = null;

        BoardElement boardElement = this.boardClassTest.getElement(posY, posX);
        if (this.boardClassTest.isInstanceOfPiece(boardElement)) {
            Piece piece = (Piece) boardElement;
            pos = this.boardClassTest.findPiecePosition(piece);
        }

        // pas faire attention pour le moment
        if (posX == pos.getPosX() && posY == pos.getPosY()) {
            validate = true;
        }

        assertTrue(validate, "testFindPiece");
    }


//    public void testMovePiece() {
//        boolean isEmptyElement = false, isPawnElement = false;
//        boolean isPieceMoved = false;
//
//        int initialPosX = 0, initialPosY = 1;
//        int movedPosX = 0, movedPosY = 3;
//
//        boardClassTest.initPiecesPosition(EColorChess.WHITE);
//
//        BoardElement boardElement = this.boardClassTest.getElement(initialPosY, initialPosX);
//        System.out.println(boardElement.getEelement());
//        this.boardClassTest.moveElement(movedPosY, movedPosX, boardElement);
//
//
//        boardElement = this.boardClassTest.getElement(initialPosY, initialPosX);
//        System.out.println(boardElement.getEelement());
//        if (boardElement.getEelement() == CElement.EMPTY) {
//            isEmptyElement = true;
//            System.out.println("isEmpty");
//        }
//
//        boardElement = this.boardClassTest.getElement(movedPosY, movedPosX);
//        System.out.println(boardElement.getEelement());
//        if (boardElement.getEelement() == CElement.PAWN) {
//            isPawnElement = true;
//            System.out.println("isPawn");
//        }
//
//        if (isEmptyElement && isPawnElement) {
//            isPieceMoved = true;
//        }
//
//        assertTrue("testMovePieces", isPieceMoved);
//    }

}
