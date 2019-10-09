package ChessGameTest.Model;

import org.chessgame.model.Board;
import org.chessgame.model.board_element.static_element.Spot;
import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.model.abstracts.BoardElement;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.iterator.BoardIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestBoard {

    public static class BoardClassTest extends Board {


        public BoardClassTest() {

        }

        @Override
        public int getHeight() {
            return super.getHeight();
        }

        @Override
        public int getWidth() {
            return super.getWidth();
        }

        @Override
        public void generateBoard() {
            super.generateBoard();
        }

        @Override
        public Spot getElement(int y, int x) {
            return super.getElement(y, x);
        }

        @Override
        public Boolean isInstanceOfPiece(BoardElement boardElement) {
            return super.isInstanceOfPiece(boardElement);
        }

        @Override
        public void setEmptyElement(int y, int x) {
            super.setEmptyElement(y, x);
        }

        @Override
        public void initPiecesPosition() {
            super.initPiecesPosition();
        }
    }

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
            if(bi.next() != null) {
                count++;
            }
        }

        assertEquals(size, count, "generation board");
    }

    @Test
    void testGetElement() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean validate = false;

        this.boardClassTest.initPiecesPosition();
        if (boardClassTest.getElement(1, 0).getBoardElement() instanceof Pawn) {
            isEmptyElement = true;
        }

        if (boardClassTest.getElement(2, 0).getBoardElement() instanceof Empty) {
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

        this.boardClassTest.initPiecesPosition();

        for(int x = 0; x < this.boardClassTest.getWidth(); x++) {
            if (this.boardClassTest.getElement(1, x).getBoardElement() instanceof Pawn) {
                countWhitePawn++;
            }
        }

        assertEquals(whitePawnNb, countWhitePawn, "testInitPiecesPosition count number of pawn");
    }

    @Test
    void testSetEmpty() {
        boolean isEmpty = false;
        int posX = 0, posY = 0;
        this.boardClassTest.initPiecesPosition();
        BoardIterator boardIterator = this.boardClassTest.iterator();
        BoardElement boardElement = null;

        while (boardIterator.hasNext() && !this.boardClassTest.isInstanceOfPiece(boardElement)) {
            boardElement = boardIterator.next().getBoardElement();
            posX = boardIterator.getX();
            posY = boardIterator.getY();
        }

        this.boardClassTest.setEmptyElement(posY, posX);

        boardElement = this.boardClassTest.getElement(posY, posX).getBoardElement();

        if (boardElement instanceof Empty) {
            isEmpty = true;
        }

        assertTrue(isEmpty, "testSetEmpty");
    }
}
