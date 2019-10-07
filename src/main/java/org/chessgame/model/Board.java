package org.chessgame.model;

import org.chessgame.model.board_element.Spot;
import org.chessgame.model.board_element.pieces.*;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.constant.CBoard;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.constant.CWindow;
import org.chessgame.share.iterator.BoardIterator;
import org.chessgame.share.services.ChessLogger;

import java.io.Serializable;
import java.util.Iterator;

public class Board implements Iterable<Spot>, Serializable {

    private static ChessLogger chessLogger = new ChessLogger(Board.class);
    protected Spot[][] boardElement = new Spot[CBoard.TILE_HEIGHT_TAB][CBoard.TILE_WIDTH_TAB];

    public Board() {
        this.generateBoard();
        this.initPiecesPosition();
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
                this.boardElement[y][x] = new Spot(x, y, new Empty());
            }
        }
    }

    public Spot getElement(int y, int x) {
        if (y < CBoard.TILE_HEIGHT_TAB && x < CBoard.TILE_WIDTH_TAB && y >= 0 && x >= 0) {
            return this.boardElement[y][x];
        }
        return null;
    }

    public void setEmptyElement(int y, int x) {
        this.boardElement[x][y].setPiece(new Empty());
    }

    protected void initPiecesPosition() {
        this.boardElement[0][0].setPiece(new Rook(EColorChess.BLACK));
        this.boardElement[0][1].setPiece(new Knight(EColorChess.BLACK));
        this.boardElement[0][2].setPiece(new Bishop(EColorChess.BLACK));
        this.boardElement[0][3].setPiece(new Queen(EColorChess.BLACK));
        this.boardElement[0][4].setPiece(new King(EColorChess.BLACK));
        this.boardElement[0][5].setPiece(new Bishop(EColorChess.BLACK));
        this.boardElement[0][6].setPiece(new Knight(EColorChess.BLACK));
        this.boardElement[0][7].setPiece(new Rook(EColorChess.BLACK));
        for (int i = 0; i < 8; i++) {
            this.boardElement[1][i].setPiece(new Pawn(EColorChess.BLACK));
            this.boardElement[6][i].setPiece(new Pawn(EColorChess.WHITE));
        }
        this.boardElement[7][0].setPiece(new Rook(EColorChess.WHITE));
        this.boardElement[7][1].setPiece(new Knight(EColorChess.WHITE));
        this.boardElement[7][2].setPiece(new Bishop(EColorChess.WHITE));
        this.boardElement[7][3].setPiece(new Queen(EColorChess.WHITE));
        this.boardElement[7][4].setPiece(new King(EColorChess.WHITE));
        this.boardElement[7][5].setPiece(new Bishop(EColorChess.WHITE));
        this.boardElement[7][6].setPiece(new Knight(EColorChess.WHITE));
        this.boardElement[7][7].setPiece(new Rook(EColorChess.WHITE));
    }

    @Override
    public BoardIterator iterator() {
        return new BoardIterator(this);
    }

    public Boolean isInstanceOfPiece(Spot spot) {
        return spot.getBoardElement() instanceof Piece;
    }

//    public Position findPiecePosition(Piece piece) {
//        BoardIterator boardIterator = this.iterator();
//
//        while (boardIterator.hasNext()) {
//            CElement boardElement = boardIterator.next();
//            Boolean b = this.isInstanceOfPiece(boardElement);
//
//            if (Boolean.TRUE.equals(b)) {
//                Piece p = (Piece) boardElement;
//                if (p.getEelement() == piece.getEelement() && p.getEColorChess() == piece.getEColorChess()) {
//                    break;
//                }
//            }
//
//        }
//
//        return new Position(boardIterator.getY(), boardIterator.getX());
//    }
}
