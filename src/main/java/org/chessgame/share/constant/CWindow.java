package org.chessgame.share.constant;

import org.chessgame.share.config.JsonConfigRW;

import java.awt.*;

public class CWindow {
    private CWindow() {}

    private static Dimension d = new JsonConfigRW().getDimension("window", "current");
    public static int HEIGHT = d.height;
    public static int WIDTH = d.width;
}
