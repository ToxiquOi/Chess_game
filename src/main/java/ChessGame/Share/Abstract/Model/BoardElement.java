package ChessGame.Share.Abstract.Model;

import ChessGame.Share.Enum.EElement;
import ChessGame.Share.Interfaces.Model.IElement;

public abstract class BoardElement extends Position implements IElement {
    public BoardElement(int x, int y) {
        super(x, y);
    }

    public EElement getElement() {
        String className = this.getClass().getSimpleName();
        return EElement.valueOf(className.toUpperCase());
    }
}
