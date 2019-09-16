package ChessGame.Controller.Listener;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Constant.CBoard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    private Board board;
    private int pieceSelectedX;
    private int pieceSelectedY;
    private boolean[] buttons = new boolean[4];
    private int x;
    private int y;


    public Mouse(Board board) {
        this.board = board;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isButtonPressed(int button){
        return this.buttons[button];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("clicked at mouseX: " + e.getX() / 100 + "mouseY: " + e.getY() / 100);
        this.x = e.getX();
        this.y = e.getY();

//        System.out.println(this.board.getElement(e.getY() / CBoard.TILE_HEIGHT_PX, e.getX() / CBoard.TILE_WIDTH_PX).getElement().toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() <= 3) {
            this.buttons[e.getButton()] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() <= 3) {
            this.buttons[e.getButton()] = false;
//            System.out.println("released at mouseX: " + this.x + " mouseY: " + this.y);

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("enter in window");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit in window");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
//        System.out.println("Dragged mouseX: " + this.x / 100 + " mouseY: " + this.y / 100);
//        System.out.println("dragged " + e.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
//        System.out.println(this.board.getElement(e.getY() / CBoard.TILE_HEIGHT_PX, e.getX() / CBoard.TILE_WIDTH_PX).getElement().toString());

    }
}
