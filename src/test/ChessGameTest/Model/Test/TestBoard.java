package ChessGameTest.Model.Test;

import ChessGame.Model.Abstract.Piece;
import ChessGame.Model.Abstract.Position;
import ChessGame.Model.BoardElement.Pieces.Pawn;
import ChessGame.Model.BoardElement.StaticElement.Empty;
import ChessGame.Model.Abstract.BoardElement;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EElement;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGameTest.Model.ClassTest.BoardClassTest;
import junit.framework.TestCase;



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
            if(bi.next().getEelement() == EElement.EMPTY) {
                count++;
            }
        }

        assertEquals("generation board", size, count);
    }


    public void testGetElement() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean validate = false;

        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);

        if (boardClassTest.getElement(0, 0).getEelement() == EElement.EMPTY) {
            isEmptyElement = true;
        }

        if (boardClassTest.getElement(6, 0).getEelement() == EElement.PAWN) {
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


    public void testSetEmpty() {
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

        assertTrue("testSetEmpty", isEmpty);
    }

    public void testFindPiece() {
        this.boardClassTest.initPiecesPosition(EColorChess.WHITE);
        boolean validate = false;
        int posY = 6, posX = 0;
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

        assertTrue("testFindPiece", validate);
    }


    public void testMovePiece() {
        boolean isEmptyElement = false, isPawnElement = false;
        boolean isPieceMoved = false;

        int initialPosX = 0, initialPosY = 6;
        int movedPosX = 0, movedPosY = 4;

        boardClassTest.initPiecesPosition(EColorChess.WHITE);

        BoardElement boardElement = this.boardClassTest.getElement(initialPosY, initialPosX);
        this.boardClassTest.moveElement(movedPosY, movedPosX, boardElement);


        boardElement = this.boardClassTest.getElement(initialPosY, initialPosX);

        if (boardElement.getEelement() == EElement.EMPTY) {
            isEmptyElement = true;
            System.out.println("isEmpty");
        }

        boardElement = this.boardClassTest.getElement(movedPosY, initialPosX);
        if (boardElement.getEelement() == EElement.PAWN) {
            isPawnElement = true;
            System.out.println("isPawn");
        }

        if (isEmptyElement && isPawnElement) {
            isPieceMoved = true;
        }

        assertTrue("testMovePieces", isPieceMoved);
    }

}
