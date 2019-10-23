package org.chessgame.share.interfaces;

import org.chessgame.model.Board;
import org.chessgame.view.awt.component.AWTLayer;
import org.chessgame.view.awt.component.GameMonitor;

import java.awt.*;

public interface IGUIFacade {
    void createWindow(String title);
    Boolean beginPaint();
    Boolean endPaint();
    Boolean clearBackground();
    boolean isClosingRequested();
    void setClosingRequest(boolean b);
    Boolean drawBackground();
    Boolean drawLayer(ILayer layer);
    GameMonitor getMonitor();
    void saveLayer(AWTLayer layer);
    Boolean drawChars();
    void createSpriteLoader(Board board);
    ILayer createLayer();
    void dispose();
    IKeyboard getKeyboard();
    IMouse getMouse();
    IImage createImage(String fileName);
    void drawImage(IImage image, int x, int y);
    void setColor(Color c);
    Dimension getTextMetrics(String text);
    void setTextSize(int size);
    void drawText(String text, int x, int y, int width, int height);
}
