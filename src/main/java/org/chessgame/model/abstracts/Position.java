/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.abstracts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tox
 */
public class Position {
    private static final List<Integer> pos = new ArrayList<>(2);
    private static final List<Integer> initialPos = new ArrayList<>(2);

    public Position(int x, int y) {
        this.setPos(x, y);
        this.setInitialPos(x,y);
    }

    private void setInitialPos(int x, int y) {
        initialPos.clear();
        initialPos.add(0, x);
        initialPos.add(1, y);
    }

    private void setPos(int x, int y){
        pos.clear();
        pos.add(0, x);
        pos.add(1, y);
    }

    public List<Integer> getInitialPos() {
        return initialPos;
    }

    public int getInitialPosX() {
        return initialPos.get(0);
    }

    public int getInitialPosY() {
        return initialPos.get(1);
    }


    public List<Integer> getPos() {
        return pos;
    }

    public int getPosX() {
        return pos.get(0);
    }

    public int getPosY() {
        return pos.get(1);
    }
}
