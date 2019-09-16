package ChessGame.Controller.Listener;

import ChessGame.Model.Board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {

    private Board board;
    private boolean[] buttons = new boolean[4];
    private int x;
    private int y;


    public MouseController(Board board) {
        this.board = board;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked at mouseX: " + this.x + "mouseY: " + this.y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() <= 3) {
            this.buttons[e.getButton()] = true;
            System.out.println("pressed at mouseX: " + this.x + "mouseY: " + this.y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() <= 3) {
            this.buttons[e.getButton()] = false;
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
        System.out.println("mouseX: " + this.x + "mouseY: " + this.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        System.out.println("mouseX: " + this.x + "mouseY: " + this.y);
    }
}
