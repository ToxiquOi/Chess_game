package ChessGame.Model;

import ChessGame.Model.BoardElement.Pieces.*;
import ChessGame.Model.BoardElement.StaticElement.Empty;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Constant.CWindow;
import ChessGame.Share.Iterator.BoardIterator;

public class Board implements Iterable<BoardElement> {

    private BoardElement[][] board;

    public Board() {
        this.board = new BoardElement[CBoard.TILE_HEIGHT_TAB][CBoard.TILE_WIDTH_TAB];
        this.generateBoard();
        this.initPiecesPosition(EColorChess.WHITE);
        this.initPiecesPosition(EColorChess.BLACK);
    }

    public int getHeight(){
        return CWindow.HEIGHT / CBoard.TILE_HEIGHT_PX;
    }

    public int getWidth() {
        return CWindow.WIDTH / CBoard.TILE_WIDTH_PX;
    }


    protected void generateBoard() {
        for (int y = 0; y < CBoard.TILE_HEIGHT_TAB; y++) {
            for (int x = 0; x < CBoard.TILE_WIDTH_TAB; x++) {
                this.board[y][x] = new Empty(x, y);
            }
        }
    }

    public BoardElement getElement(int y, int x) {
        if (y < CBoard.TILE_HEIGHT_TAB && x < CBoard.TILE_WIDTH_TAB && y >= 0 && x >= 0) {
            return this.board[x][y];
        }
        return null;
    }

    public Boolean isInstanceOfPiece(BoardElement boardElement) {
        if(boardElement instanceof Piece) {
            return true;
        }
        return false;
    }

    public void moveElement(int y, int x, Piece piece) {
        this.board[piece.getPosY()][piece.getPosY()] = new Empty(piece.getPosY(), piece.getPosX());
        this.board[y][x] = piece;
        piece.setPos(x, y);
    }

    public void setEmptyElement(int y, int x) {
        Empty empty = new Empty(x, y);
        this.board[y][x] = empty;
    }

    protected void initPiecesPosition(EColorChess eColorChess) {
        int x1 = (eColorChess == EColorChess.WHITE)? 7 : 0;
        int x2 = (eColorChess == EColorChess.WHITE)? 6 : 1;

        for(int i = 0; i < 8; i++) {
            if(i == 0 || i == 7) {
                this.board[i][x1] = new Rook(x1, i, eColorChess);
            }
            if(i == 1 || i == 6) {
                this.board[i][x1] = new Knight(x1, i, eColorChess);
            }
            if(i == 2 || i == 5) {
                this.board[i][x1] = new Bishop(x1, i, eColorChess);
            }
            if(i == 3) {
                this.board[i][x1] = new Queen(x1, i, eColorChess);
            }
            if(i == 4) {
                this.board[i][x1] = new King(x1, i, eColorChess);
            }

            this.board[i][x2] = new Pawn(x2, i, eColorChess);
        }
    }


    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }
}
