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

    public Knight(Piece piece) {
        super(piece);
    }
}
