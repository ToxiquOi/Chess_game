/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ChessGame.Model.BoardElement.Pieces;


import org.ChessGame.Model.Abstract.Piece;
import org.ChessGame.Share.Enum.EColorChess;
import org.ChessGame.Share.Enum.EMove;

/**
 *
 * @author tox
 */
public class Rook extends Piece {

    private EMove[] moves = {
            EMove.UP,
            EMove.DOWN,
            EMove.LEFT,
            EMove.RIGHT,
    };

    public Rook(EColorChess eColorChess) {
        super(eColorChess);
    }

    public Rook(Piece piece) {
        super(piece);
    }
}
