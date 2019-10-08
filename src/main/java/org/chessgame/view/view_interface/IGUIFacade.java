package org.chessgame.view.view_interface;

import org.chessgame.model.Board;

public interface IGUIFacade {
    void createWindow(String title);
    boolean beginPaint();
    void endPaint();
    void clearBackground();
    boolean isClosingRequested();
    void drawBackground();
    void drawLayer(ILayer layer);
    void drawChars();
    void createSpriteLoader(Board board);
    ILayer createLayer();
    void dispose();
}
