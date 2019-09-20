/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Model.BoardElement.Pieces;


import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.EColorChess;
import ChessGame.Share.Enum.EMove;

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

    public Rook(int x, int y, EColorChess EColorChess) {
        super(x, y, EColorChess);
    }
}
