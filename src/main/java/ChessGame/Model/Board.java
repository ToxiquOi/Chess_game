package ChessGame.Model;

import ChessGame.Model.BoardElement.Pieces.*;
import ChessGame.Model.BoardElement.StaticElement.Empty;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Enum.EWindow;
import ChessGame.Share.Iterator.BoardIterator;

public class Board implements Iterable<BoardElement>{

    private BoardElement[][] board;

    public Board() {
        this.generateBoard();
    }

    public int getHeight(){
        return EWindow.HEIGHT / 100;
    }

    public int getWidth() {
        return EWindow.WIDTH / 100;
    }

    private void generateBoard() {
        this.board = new BoardElement[this.getWidth()][this.getWidth()];

        for (int y = 0; y < 8; y++) {
//            System.out.println("y: " + y);
            for (int x = 0; x < 8; x++) {

//                System.out.println("x: " + x);
                this.board[y][x] = new Empty(x, y);
            }
        }

        this.piecesDisposition(ColorChess.WHITE);
        this.piecesDisposition(ColorChess.BLACK);

    }

    public BoardElement getElement(int x, int y) {
        return this.board[y][x];
    }

    private void piecesDisposition(ColorChess colorChess) {
        int x1 = (colorChess == ColorChess.WHITE)? 0 : 7;
        int x2 = (colorChess == ColorChess.WHITE)? 1 : 6;

        for(int i = 0; i < 8; i++) {
            if(i == 0 || i == 7) {
                this.board[x1][i] = new Rook(x1, i, colorChess);
            }
            if(i == 1 || i == 6) {
                this.board[x1][i] = new Knight(x1, i, colorChess);
            }
            if(i == 2 || i == 5) {
                this.board[x1][i] = new Bishop(x1, i, colorChess);
            }
            if(i == 3) {
                this.board[x1][i] = new Queen(x1, i, colorChess);
            }
            if(i == 4) {
                this.board[x1][i] = new King(x1, i, colorChess);
            }

            this.board[x2][i] = new Pawn(x2, i, colorChess);
        }
    }


    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }
}
