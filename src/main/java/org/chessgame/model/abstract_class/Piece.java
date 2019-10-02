/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.abstract_class;

import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public abstract class Piece extends BoardElement {

    private boolean isAlive;
    protected EColorChess eColorChess;
    private EMove[] moves;


    public Piece(EColorChess eColorChess) {
        this.eColorChess = eColorChess;
        this.isAlive = true;
    }

    public Piece(Piece piece) {
        this.isAlive = piece.getIsAlive();
        this.eColorChess = piece.getEColorChess();
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
        return this.eColorChess;
    }


}
