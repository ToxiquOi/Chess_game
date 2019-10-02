package org.chessgame.model.abstract_class;

import org.chessgame.share.enumeration.EElement;
import org.chessgame.model.interface_element.IElement;

public abstract class BoardElement implements IElement {
    public BoardElement() {
    }

    public EElement getEelement() {
        String className = this.getClass().getSimpleName();
        return EElement.valueOf(className.toUpperCase());
    }
}
