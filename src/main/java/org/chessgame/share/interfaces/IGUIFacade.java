package org.chessgame.share.interfaces;

import org.chessgame.controller.listener.Keyboard;
import org.chessgame.controller.listener.Mouse;
import org.chessgame.model.Board;

public interface IGUIFacade {
    void createWindow(String title);
    Boolean beginPaint();
    Boolean endPaint();
    Boolean clearBackground();
    boolean isClosingRequested();
    void setClosingRequest(boolean b);
    Boolean drawBackground();
    Boolean drawLayer(ILayer layer);
    Boolean drawChars();
    void createSpriteLoader(Board board);
    ILayer createLayer();
    void dispose();
    Keyboard getKeyboard();
    Mouse getMouse();
}
