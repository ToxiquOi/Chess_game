package org.chessgame.share.interfaces;

import org.chessgame.model.Board;

public interface IGUIFacade {
    void createWindow(String title);
    Boolean beginPaint();
    Boolean endPaint();
    Boolean clearBackground();
    boolean isClosingRequested();
    Boolean drawBackground();
    Boolean drawLayer(ILayer layer);
    Boolean drawChars();
    void createSpriteLoader(Board board);
    ILayer createLayer();
    void dispose();
    IKeyboard getKeyboard();
    IMouse getMouse();
}
