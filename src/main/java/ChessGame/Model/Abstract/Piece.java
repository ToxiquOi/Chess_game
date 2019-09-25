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


    public Piece(EColorChess EColorChess) {
        this.EColorChess = EColorChess;
        this.isAlive = true;
    }

    public Piece(Piece piece) {
        this.isAlive = piece.getIsAlive();
        this.EColorChess = piece.getEColorChess();
        this.moves = piece.getMoves();
    }

    public boolean getIsAlive() {
        return this.isAlive;
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
