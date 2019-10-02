package org.chessgame.model;

import org.chessgame.model.abstract_class.Position;
import org.chessgame.model.board_element.pieces.*;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;

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
        return boardElement instanceof Piece;
    }

    public Position findPiecePosition(Piece piece) {
        BoardIterator boardIterator = this.iterator();

        while (boardIterator.hasNext()) {
            BoardElement boardElement = boardIterator.next();
            Boolean b = this.isInstanceOfPiece(boardElement);

            if (Boolean.TRUE.equals(b)) {
                Piece p = (Piece) boardElement;
                if (p.getEelement() == piece.getEelement() && p.getEColorChess() == piece.getEColorChess()) {
                    break;
                }
            }

        }

        return new Position(boardIterator.getY(), boardIterator.getX());
    }


    public void moveElement(int moveToY, int moveToX, BoardElement boardElement) {
        System.out.println("moveElement: " + boardElement.getEelement());
        Boolean b = this.isInstanceOfPiece(boardElement);
        if (Boolean.TRUE.equals(b)) {
            Piece piece = (Piece) boardElement;
            Position pos = this.findPiecePosition(piece);

            if (moveToY < CBoard.TILE_HEIGHT_TAB && moveToX < CBoard.TILE_WIDTH_TAB && moveToY >= 0 && moveToX >= 0) {
                this.setEmptyElement(pos.getPosX(), pos.getPosY());
                this.board[moveToX][moveToY] = piece;
            }
        }
    }

    public void setEmptyElement(int y, int x) {
        this.board[x][y] = new Empty();
    }

    protected void initPiecesPosition(EColorChess eColorChess) {
        int y1 = (eColorChess == EColorChess.WHITE)? 0 : 7;
        int y2 = (eColorChess == EColorChess.WHITE)? 1 : 6;

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
