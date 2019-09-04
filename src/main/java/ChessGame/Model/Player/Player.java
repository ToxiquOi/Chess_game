package ChessGame.Model.Player;

import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.ColorChess;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public ColorChess playerColorChess;
    public List<Piece> pieceArrayList = new ArrayList<>(16);

    public Player(ColorChess playerColorChess) {
        this.playerColorChess = playerColorChess;
    }

    private void loadPiecesList(ColorChess colorChess) {

    }
}
