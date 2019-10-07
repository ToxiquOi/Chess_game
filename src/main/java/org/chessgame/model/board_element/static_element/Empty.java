package org.chessgame.model.board_element.static_element;

import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.share.enumeration.CElement;

public class Empty extends BoardElement {
    @Override
    public int getCElement() {
        return CElement.EMPTY;
    }
}
