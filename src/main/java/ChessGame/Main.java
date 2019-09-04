package ChessGame;

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
        GameMonitor gw = new GameMonitor("Chess");
        gw.setVisible(true);
        gw.run();
    }

}
