/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.board_element.pieces;


import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public class Pawn extends Piece {

    private EMove[] moves = {
            EMove.UP,
    };

    public Pawn(EColorChess eColorChess) {
        super(eColorChess);
    }

    public Pawn(Piece piece) {
        super(piece);
    }
}
