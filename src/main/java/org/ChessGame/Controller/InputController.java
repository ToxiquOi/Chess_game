package org.ChessGame.Controller;

import org.ChessGame.Controller.Listener.Mouse;


public class InputController {

    private Mouse mouse;

    public InputController() {
        this.mouse = new Mouse();
    }

    public Mouse getMouse() {
        return this.mouse;
    }
}
