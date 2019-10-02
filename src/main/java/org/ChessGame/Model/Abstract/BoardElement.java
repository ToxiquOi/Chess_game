package org.ChessGame.Model.Abstract;

import org.ChessGame.Share.Enum.EElement;
import org.ChessGame.Model.Interface.IElement;

public abstract class BoardElement implements IElement {
    public BoardElement() {
    }

    public EElement getEelement() {
        String className = this.getClass().getSimpleName();
        return EElement.valueOf(className.toUpperCase());
    }
}
