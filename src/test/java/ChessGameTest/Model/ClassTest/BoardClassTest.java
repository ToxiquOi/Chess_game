package ChessGameTest.Model.ClassTest;

import org.chessgame.model.abstract_class.Position;
import org.chessgame.model.Board;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.enumeration.EColorChess;

public class BoardClassTest extends Board {


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
    public BoardElement getElement(int y, int x) {
        return super.getElement(y, x);
    }

    @Override
    public Position findPiecePosition(Piece piece) {
        return super.findPiecePosition(piece);
    }

    @Override
    public void moveElement(int y, int x, BoardElement boardElement) {
        super.moveElement(y, x, boardElement);
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
    public void initPiecesPosition(EColorChess eColorChess) {
        super.initPiecesPosition(eColorChess);
    }
}
