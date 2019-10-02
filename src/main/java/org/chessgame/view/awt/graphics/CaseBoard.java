package org.chessgame.view.awt.graphics;


import java.awt.*;

public class CaseBoard {

    private static int Width = 100;
    private static int Height = 100;

    public CaseBoard(int posX, int posY, Graphics g){
        g.fillRect(posX * 100, posY * 100, Width, Height);
    }

    public static void draw(int posX, int posY, Graphics g) {
        g.fillRect(posX * 100, posY * 100, Width, Height);
    }
}
