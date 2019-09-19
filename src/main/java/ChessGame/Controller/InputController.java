package ChessGame.Controller;

import ChessGame.Controller.Listener.Mouse;
import ChessGame.Model.Board;


public class InputController {

    private Mouse mouse;

    public InputController() {
        this.mouse = new Mouse();
    }

    public Mouse getMouse() {
        return this.mouse;
    }
}
