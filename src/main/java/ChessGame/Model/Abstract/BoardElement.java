package ChessGame.Model.Abstract;

import ChessGame.Share.Enum.EElement;
import ChessGame.Model.Interface.IElement;

public abstract class BoardElement implements IElement {
    public BoardElement() {
    }

    public EElement getEelement() {
        String className = this.getClass().getSimpleName();
        return EElement.valueOf(className.toUpperCase());
    }
}
