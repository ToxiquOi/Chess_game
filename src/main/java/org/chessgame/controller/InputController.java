package org.chessgame.controller;

import org.chessgame.controller.listener.Mouse;


public class InputController {

    private Mouse mouse;

    public InputController() {
        this.mouse = new Mouse();
    }

    public Mouse getMouse() {
        return this.mouse;
    }
}
