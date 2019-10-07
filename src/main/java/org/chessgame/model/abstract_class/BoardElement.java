package org.chessgame.model.abstract_class;

import org.chessgame.share.enumeration.CElement;
import org.chessgame.model.interface_element.IElement;
import org.chessgame.share.services.ChessLogger;

import java.lang.reflect.Field;
import java.util.logging.Level;

public abstract class BoardElement implements IElement {
    @Override
    public int getCElement() {
        return -1;
    }
}
