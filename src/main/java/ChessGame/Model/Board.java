package ChessGame.Model;

import ChessGame.Model.Abstract.Position;
import ChessGame.Model.BoardElement.Pieces.*;
import ChessGame.Model.BoardElement.StaticElement.Empty;
import ChessGame.Model.Abstract.BoardElement;
import ChessGame.Model.Abstract.Piece;
import ChessGame.Share.Constant.CBoard;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Constant.CWindow;
import ChessGame.Share.Iterator.BoardIterator;

public class Board implements Iterable<BoardElement> {

    protected BoardElement[][] board;

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
                this.board[x][y] = new Empty();
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

    public Position findPiecePosition(Piece piece) {
        BoardIterator boardIterator = this.iterator();

        while (boardIterator.hasNext()) {
            BoardElement boardElement = boardIterator.next();

            if (this.isInstanceOfPiece(boardElement)) {
                Piece p = (Piece) boardElement;
                if (p.getEelement() == piece.getEelement() && p.getEColorChess() == piece.getEColorChess()) {
                    break;
                }
            }

        }

        return new Position(boardIterator.getX(), boardIterator.getY());
    }


    public void moveElement(int moveToY, int moveToX, BoardElement boardElement) {
        if (this.isInstanceOfPiece(boardElement)) {
            Piece piece = (Piece) boardElement;

            Position pos = this.findPiecePosition(piece);

            this.setEmptyElement(pos.getPosX(), pos.getPosY());
            this.board[moveToX][moveToY] = piece;
        }
    }

    public void setEmptyElement(int y, int x) {
        this.board[x][y] = new Empty();
    }

    protected void initPiecesPosition(EColorChess eColorChess) {
        int y1 = (eColorChess == EColorChess.WHITE)? 7 : 0;
        int y2 = (eColorChess == EColorChess.WHITE)? 6 : 1;

        for(int i = 0; i < 8; i++) {
            if(i == 0 || i == 7) {
                this.board[i][y1] = new Rook(eColorChess);
            }
            if(i == 1 || i == 6) {
                this.board[i][y1] = new Knight(eColorChess);
            }
            if(i == 2 || i == 5) {
                this.board[i][y1] = new Bishop(eColorChess);
            }
            if(i == 3) {
                this.board[i][y1] = new Queen(eColorChess);
            }
            if(i == 4) {
                this.board[i][y1] = new King(eColorChess);
            }

            this.board[i][y2] = new Pawn(eColorChess);
        }
    }

    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }
}
