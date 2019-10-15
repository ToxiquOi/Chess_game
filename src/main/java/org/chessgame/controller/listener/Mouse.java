package org.chessgame.controller.listener;

import org.chessgame.share.interfaces.IMouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mouse extends MouseAdapter implements IMouse, Serializable {

    private static Logger logger = Logger.getLogger(MouseAdapter.class.getSimpleName());
    private boolean[] buttons;
    private int x;
    private int y;

    public Mouse() {
        this.buttons = new boolean[4];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public boolean isButtonPressed(int button) {
        if (button >= buttons.length) {
            return false;
        }
        return buttons[button];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
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
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        logger.log(Level.INFO, "mouse moved x: " + e.getX());
        this.setX(e.getX());
        this.setY(e.getY());
    }
}
