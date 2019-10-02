package org.chessgame;

import org.chessgame.controller.InputController;
import org.chessgame.model.Board;
import org.chessgame.view.awt.GameMonitor;

/**
 *
 * @author tox
 */
public class Main {

    private static final String AppTitle = "chess";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        InputController inputController = new InputController();
        GameMonitor gm = new GameMonitor(AppTitle);

        gm.addBoard(board);
        gm.addMouse(inputController.getMouse());
        gm.setSriteLoader();
        gm.setTileSelector();
        gm.init();

        gm.setVisible(true);
        gm.start();
//        gm.bench();
    }

}