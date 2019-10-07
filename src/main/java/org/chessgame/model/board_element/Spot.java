package org.chessgame.model.board_element;

import org.chessgame.model.abstract_class.BoardElement;

public class Spot {
    private BoardElement boardElement;
    private int x;
    private int y;

    public Spot(int x, int y, BoardElement boardElement)
    {
        this.setPiece(boardElement);
        this.setX(x);
        this.setY(y);
    }

    public BoardElement getBoardElement() {
        return this.boardElement;
    }

    public void setPiece(BoardElement boardElement) {
        this.boardElement = boardElement;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
