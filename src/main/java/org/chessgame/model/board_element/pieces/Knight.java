/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.board_element.pieces;


import org.chessgame.model.abstract_class.Piece;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public class Knight extends Piece {

    private EMove[] moves = {
            EMove.UP_LEFT,
            EMove.UP_RIGHT,
            EMove.DOWN_LEFT,
            EMove.DOWN_RIGHT,
            EMove.RIGHT_UP,
            EMove.RIGHT_DOWN,
            EMove.LEFT_UP,
            EMove.LEFT_DOWN,
    };

    public Knight(EColorChess eColorChess) {
        super(eColorChess);
    }

    @Override
    public int getCElement() {
        if (this.eColorChess == EColorChess.WHITE) {
            return CElement.WHITE_KNIGHT;
        } else {
            return CElement.BLACK_KNIGHT;
        }
    }
}
