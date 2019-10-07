package org.chessgame.view.view_interface;

public interface IGUIFacade {
    void createWindow(String title);
    boolean beginPaint();
    void endPaint();
    void clearBackground();
    boolean isClosingRequested();
    void drawBackground();
    void drawLayer(ILayer layer);
    ILayer createLayer();
}
