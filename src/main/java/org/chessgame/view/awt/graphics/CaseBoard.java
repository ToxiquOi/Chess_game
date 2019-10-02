package org.chessgame.view.awt.graphics;


import java.awt.*;

public class CaseBoard {

    private static int width = 100;
    private static int height = 100;

    public CaseBoard(int posX, int posY, Graphics g){
        g.fillRect(posX * 100, posY * 100, width, height);
    }

    public static void draw(int posX, int posY, Graphics g) {
        g.fillRect(posX * 100, posY * 100, width, height);
    }
}
