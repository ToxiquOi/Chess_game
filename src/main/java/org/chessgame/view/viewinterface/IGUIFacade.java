package org.chessgame.view.viewinterface;

public interface IGUIFacade {
    void createWindow(String title);
    boolean beginPaint();
    void endPaint();
    void clearBackground();
    boolean isClosingRequested();
    void drawLayer(ILayer layer);
    ILayer createLayer();
}
