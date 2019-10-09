/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.abstracts;

import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public abstract class Piece extends BoardElement {

    private boolean isAlive;
    protected EColorChess eColorChess;
    private EMove[] moves = {};


    public Piece(EColorChess eColorChess) {
        this.eColorChess = eColorChess;
        this.isAlive = true;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public EMove[] getMoves() {
        return this.moves;
    }

    public void die() {
        this.isAlive = false;
    }

    public EColorChess getEColorChess() {
        return this.eColorChess;
    }


}
