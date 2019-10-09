package org.chessgame.share.interfaces;

public interface IKeyboard {
    boolean isKeyPressed(int keyCode);
    int getLastPressedKey();
    int consumeLastPressedKey();
}
