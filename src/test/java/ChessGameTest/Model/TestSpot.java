package ChessGameTest.Model;

import org.chessgame.model.board_element.static_element.Spot;
import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSpot {
    private Spot spot;
    private int x;
    private int y;

    @BeforeEach
    public void setUp() {
        this.x = 0;
        this.y = 0;
        this.spot = new Spot(this.x, this.y, new Empty());
    }

    @Test
    void testIsInitializedWithGoogValue() {
        boolean xAsGoodValue = false;
        boolean yAsGoodValue = false;
        boolean boardElementInstanceOfEmpty = false;
        boolean allCritValidate = false;

        if(this.x == this.spot.getX()) {
            xAsGoodValue = true;
        }
        if (this.y == this.spot.getY()) {
            yAsGoodValue = true;
        }
        if (this.spot.getBoardElement() instanceof Empty) {
            boardElementInstanceOfEmpty = true;
        }

        if (xAsGoodValue && yAsGoodValue && boardElementInstanceOfEmpty) {
            allCritValidate = true;
        }

        assertTrue(allCritValidate, "initialized with good value");
    }

    @Test
    void testSetBoardElement() {
        Pawn pawn = new Pawn(EColorChess.WHITE);
        this.spot.setPiece(pawn);

        assertTrue(this.spot.getBoardElement() instanceof Pawn, "test set another object in spot");
    }
}
