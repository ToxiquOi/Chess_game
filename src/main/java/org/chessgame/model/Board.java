package org.chessgame.model;

import org.chessgame.model.board_element.static_element.Spot;
import org.chessgame.model.board_element.pieces.*;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.model.abstracts.BoardElement;
import org.chessgame.model.abstracts.Piece;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;

import java.io.Serializable;

public class Board implements Iterable<Spot>, Serializable {

    protected transient Spot[][] boardElement = new Spot[CBoard.TILE_HEIGHT_TAB][CBoard.TILE_WIDTH_TAB];

    public Board() {
        this.generateBoard();
        this.initPiecesPosition();
    }

    public int getHeight(){
        return CBoard.TILE_HEIGHT_TAB;
    }

    public int getWidth() {
        return CBoard.TILE_WIDTH_TAB;
    }

    protected void generateBoard() {
        for (int x = 0; x < CBoard.TILE_HEIGHT_TAB; x++) {
            for (int y = 0; y < CBoard.TILE_WIDTH_TAB; y++) {
                this.boardElement[y][x] = new Spot(x, y, new Empty());
            }
        }
    }

    public Spot getElement(int y, int x) {
        if (y < CBoard.TILE_HEIGHT_TAB && x < CBoard.TILE_WIDTH_TAB && y >= 0 && x >= 0) {
            return this.boardElement[x][y];
        }
        return null;
    }

    public void setEmptyElement(int y, int x) {
        this.boardElement[x][y].setPiece(new Empty());
    }

    protected void initPiecesPosition() {
        this.boardElement[0][0].setPiece(new Rook(EColorChess.WHITE));
        this.boardElement[1][0].setPiece(new Knight(EColorChess.WHITE));
        this.boardElement[2][0].setPiece(new Bishop(EColorChess.WHITE));
        this.boardElement[3][0].setPiece(new Queen(EColorChess.WHITE));
        this.boardElement[4][0].setPiece(new King(EColorChess.WHITE));
        this.boardElement[5][0].setPiece(new Bishop(EColorChess.WHITE));
        this.boardElement[6][0].setPiece(new Knight(EColorChess.WHITE));
        this.boardElement[7][0].setPiece(new Rook(EColorChess.WHITE));
        for (int i = 0; i < 8; i++) {
            this.boardElement[i][1].setPiece(new Pawn(EColorChess.WHITE));
            this.boardElement[i][6].setPiece(new Pawn(EColorChess.BLACK));
        }
        this.boardElement[0][7].setPiece(new Rook(EColorChess.BLACK));
        this.boardElement[1][7].setPiece(new Knight(EColorChess.BLACK));
        this.boardElement[2][7].setPiece(new Bishop(EColorChess.BLACK));
        this.boardElement[3][7].setPiece(new Queen(EColorChess.BLACK));
        this.boardElement[4][7].setPiece(new King(EColorChess.BLACK));
        this.boardElement[5][7].setPiece(new Bishop(EColorChess.BLACK));
        this.boardElement[6][7].setPiece(new Knight(EColorChess.BLACK));
        this.boardElement[7][7].setPiece(new Rook(EColorChess.BLACK));
    }

    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }

    public Boolean isInstanceOfPiece(BoardElement boardElement) {
        return boardElement instanceof Piece;
    }
}
