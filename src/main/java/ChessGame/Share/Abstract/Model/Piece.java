/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Share.Abstract.Model;

import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Enum.Move;
import ChessGame.Share.Interfaces.Model.IMovement;

/**
 *
 * @author tox
 */
public abstract class Piece extends BoardElement {

    protected boolean isAlive;
    private ColorChess colorChess;

    public Piece(int x, int y, ColorChess colorChess) {
        super(x, y);
        this.colorChess = colorChess;
        this.isAlive = true;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void die() {
        this.isAlive = false;
    }

    public ColorChess getColorChess() {
        return colorChess;
    }

}
