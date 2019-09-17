package ChessGame.Controller;

import ChessGame.Controller.Listener.Mouse;
import ChessGame.Model.Board;


public class InputController {

    private Mouse mouse;

    public InputController(Board board) {
        this.mouse = new Mouse(board);
    }

    public Mouse getMouse() {
        return this.mouse;
    }
}
