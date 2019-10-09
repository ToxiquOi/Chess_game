package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.Pawn;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPawn {
    @Test
    void testCElementReturnWhite() {
        Pawn piece = new Pawn(EColorChess.WHITE);
        assertEquals(CElement.WHITE_PAWN, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        Pawn piece = new Pawn(EColorChess.BLACK);
        assertEquals(CElement.BLACK_PAWN, piece.getCElement(), "test is black rook");
    }
}
