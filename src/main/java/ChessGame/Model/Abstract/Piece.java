/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Model.Abstract;

import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EMove;

/**
 *
 * @author tox
 */
public abstract class Piece extends BoardElement {

    private boolean isAlive;
    protected EColorChess EColorChess;
    private EMove[] moves;


    public Piece(int x, int y, EColorChess EColorChess) {
        super(x, y);
        this.EColorChess = EColorChess;
        this.isAlive = true;
    }

    public EMove[] getMoves() {
        return this.moves;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void die() {
        this.isAlive = false;
    }

    public EColorChess getEColorChess() {
        return EColorChess;
    }

}
