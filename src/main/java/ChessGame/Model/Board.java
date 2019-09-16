package ChessGame.Model;

import ChessGame.Model.BoardElement.Pieces.*;
import ChessGame.Model.BoardElement.StaticElement.Empty;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Constant.CWindow;
import ChessGame.Share.Iterator.BoardIterator;

public class Board implements Iterable<BoardElement>{

    private BoardElement[][] board;

    public Board() {
        this.board = new BoardElement[CBoard.TILE_HEIGHT_TAB][CBoard.TILE_WIDTH_TAB];
        this.generateBoard();
    }

    public int getHeight(){
        return CWindow.HEIGHT / CBoard.TILE_HEIGHT_PX;
    }

    public int getWidth() {
        return CWindow.WIDTH / CBoard.TILE_WIDTH_PX;
    }

    private void generateBoard() {
        for (int y = 0; y < CBoard.TILE_HEIGHT_TAB; y++) {
//            System.out.println("y: " + y);
            for (int x = 0; x < CBoard.TILE_WIDTH_TAB; x++) {

//                System.out.println("x: " + x);
                this.board[y][x] = new Empty(x, y);
            }
        }

        this.piecesDisposition(ColorChess.WHITE);
        this.piecesDisposition(ColorChess.BLACK);

    }

    public BoardElement getElement(int y, int x) {
        return this.board[x][y];
    }

    private void piecesDisposition(ColorChess colorChess) {
        int x1 = (colorChess == ColorChess.WHITE)? 0 : 7;
        int x2 = (colorChess == ColorChess.WHITE)? 1 : 6;

        for(int i = 0; i < 8; i++) {
            if(i == 0 || i == 7) {
                this.board[i][x1] = new Rook(x1, i, colorChess);
            }
            if(i == 1 || i == 6) {
                this.board[i][x1] = new Knight(x1, i, colorChess);
            }
            if(i == 2 || i == 5) {
                this.board[i][x1] = new Bishop(x1, i, colorChess);
            }
            if(i == 3) {
                this.board[i][x1] = new Queen(x1, i, colorChess);
            }
            if(i == 4) {
                this.board[i][x1] = new King(x1, i, colorChess);
            }

            this.board[i][x2] = new Pawn(x2, i, colorChess);
        }
    }


    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }
}
