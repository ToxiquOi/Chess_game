package org.chessgame.model.board_element.static_element;

import org.chessgame.model.abstracts.BoardElement;
import org.chessgame.share.constant.CElement;

public class Empty extends BoardElement {
    @Override
    public int getCElement() {
        return CElement.EMPTY;
    }
}
