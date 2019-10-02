/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.interface_element;

import org.chessgame.share.enumeration.EMove;

/**
 *
 * @author tox
 */
public interface IMovement {
    void move(EMove eMove);
    void moveUp(int x, int y);
    void moveDown(int x, int y);
    void moveLeft(int x, int y);
    void moveRight(int x, int y);
    void moveDiagUpLeft(int x, int y);
    void moveDiagUpRight(int x, int y);
    void moveDiagDownLeft(int x, int y);
    void moveDiagDownRight(int x, int y);
    void moveUpLeft(int x, int y);
    void moveUpRight(int x, int y);
    void moveDownLeft(int x, int y);
    void moveDownRight(int x, int y);
    void moveLeftUp(int x, int y);
    void moveLeftDown(int x, int y);
    void moveRightUp(int x, int y);
    void moveRightDown();

}
