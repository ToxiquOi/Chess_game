/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.board_element.pieces;


import org.chessgame.model.abstracts.Piece;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public class Queen extends Piece {

    private EMove[] moves = {
            EMove.UP,
            EMove.DOWN,
            EMove.LEFT,
            EMove.RIGHT,
            EMove.DIAG_DOWN_LEFT,
            EMove.DIAG_DOWN_RIGHT,
            EMove.DIAG_UP_LEFT,
            EMove.DIAG_UP_RIGHT
    };

    public Queen(EColorChess eColorChess) {
        super(eColorChess);
    }

    @Override
    public int getCElement() {
        if (this.eColorChess == EColorChess.WHITE) {
            return CElement.WHITE_QUEEN;
        } else {
            return CElement.BLACK_QUEEN;
        }
    }
}
