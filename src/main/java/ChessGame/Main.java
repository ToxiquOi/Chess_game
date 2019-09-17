package ChessGame;

import ChessGame.Controller.InputController;
import ChessGame.Model.Board;
import ChessGame.View.awt.GameMonitor;

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
        InputController inputController = new InputController(board);
        GameMonitor gm = new GameMonitor(AppTitle, board, inputController.getMouse());
        gm.setVisible(true);
        gm.run();
    }

}
