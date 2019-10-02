/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chessgame.model.abstract_class;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tox
 */
public class Position {
    public List<Integer> pos = new ArrayList<>(2);
    public List<Integer> initialPos = new ArrayList<>(2);

    public Position(int x, int y) {
        this.setPos(x, y);
        this.setInitialPos(x,y);
    }

    private void setInitialPos(int x, int y) {
        this.initialPos.clear();
        this.initialPos.add(0, x);
        this.initialPos.add(1, y);
    }

    public void setPos(int x, int y){
        this.pos.clear();
        this.pos.add(0, x);
        this.pos.add(1, y);
    }

    public List<Integer> getInitialPos() {
        return this.initialPos;
    }

    public int getInitialPosX() {
        return this.initialPos.get(0);
    }

    public int getInitialPosY() {
        return this.initialPos.get(1);
    }


    public List<Integer> getPos() {
        return pos;
    }

    public int getPosX() {
        return this.pos.get(0);
    }

    public int getPosY() {
        return this.pos.get(1);
    }
}
