package org.chessgame.share.interfaces;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface IMouse {
    boolean isButtonPressed(int button);
    int getX();
    int getY();
}
