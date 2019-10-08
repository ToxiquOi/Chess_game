package org.chessgame.model.abstract_class;

import org.chessgame.model.interface_element.IElement;

public abstract class BoardElement implements IElement {
    @Override
    public int getCElement() {
        return -1;
    }
}
