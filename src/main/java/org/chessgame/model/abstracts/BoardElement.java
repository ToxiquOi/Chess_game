package org.chessgame.model.abstracts;

import org.chessgame.share.interfaces.IElement;

public abstract class BoardElement implements IElement {
    @Override
    public int getCElement() {
        return -1;
    }
}
