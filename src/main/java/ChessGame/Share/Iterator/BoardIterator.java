package ChessGame.Share.Iterator;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Constant.CBoard;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardIterator implements Iterator<BoardElement> {

    private Board board;
    private int x;
    private int y;

    public BoardIterator(Board board) {
        this.board = board;
        this.x = -1;
        this.y = 0;
    }

    @Override
    public boolean hasNext() {
        if(this.x + 1 < board.getWidth()) {
            return true;
        }
        else return this.y + 1 < board.getHeight();
    }

    @Override
    public BoardElement next() {
        if(this.x + 1 < this.board.getWidth()) {
            this.x++;
        }
        else if(this.y + 1 < this.board.getHeight()) {
            this.x = 0;
            this.y++;
        }
        else {
            throw new NoSuchElementException("position x: " + this.x + ", y: " + this.y);
        }

        return this.board.getElement(this.x, this.y);
    }

    public Boolean isInstanceOfPiece(BoardElement boardElement){
        return this.board.isInstanceOfPiece(boardElement);
    }

    public void resetIterator() {
        this.x = -1;
        this.y = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
