package ChessGame.View.awt.Graphics;


import java.awt.*;

public class CaseBoard {

    private static int WIDTH = 100;
    private static int HEIGHT = 100;

    public CaseBoard(int posX, int posY, Graphics g){
        g.fillRect(posX * 100, posY * 100, WIDTH, HEIGHT);
    }


}
