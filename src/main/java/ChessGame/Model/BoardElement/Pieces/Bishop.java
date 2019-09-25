/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Model.BoardElement.Pieces;


import ChessGame.Model.Abstract.Piece;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EMove;

/**
 *
 * @author tox
 */
public class Bishop extends Piece {

    private EMove[] moves = {
            EMove.DIAG_DOWN_LEFT,
            EMove.DIAG_DOWN_RIGHT,
            EMove.DIAG_UP_LEFT,
            EMove.DIAG_UP_RIGHT
    };


    public Bishop(EColorChess eColorChess) {
        super(eColorChess);
    }

    public Bishop(Piece piece) {
        super(piece);
    }
}
