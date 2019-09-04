package ChessGame.Share.Abstract.Model;

import ChessGame.Share.Enum.Element;
import ChessGame.Share.Interfaces.Model.IElement;

public abstract class BoardElement extends Position implements IElement {
    public BoardElement(int x, int y) {
        super(x, y);
    }

    public Element getElement() {
        String className = this.getClass().getSimpleName();
        return Element.valueOf(className.toUpperCase());
    }
}
