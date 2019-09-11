package ChessGame;

import ChessGame.Model.Board;
import ChessGame.View.awt.GameMonitor;

/**
 *
 * @author tox
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        GameMonitor gw = new GameMonitor("Chess", board);
        gw.setVisible(true);
        gw.run();
    }

}
