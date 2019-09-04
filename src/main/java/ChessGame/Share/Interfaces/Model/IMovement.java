/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Share.Interfaces.Model;

import ChessGame.Share.Enum.Move;

/**
 *
 * @author tox
 */
public interface IMovement {
    void move(Move move);
    void moveUp(int x, int y);
    void moveDown(int x, int y);
    void moveLeft(int x, int y);
    void moveRight(int x, int y);
}
