package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.Rook;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRook {
    @Test
    void testCElementReturnWhite() {
        Rook piece = new Rook(EColorChess.WHITE);
        assertEquals(CElement.WHITE_ROOK, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        Rook piece = new Rook(EColorChess.BLACK);
        assertEquals(CElement.BLACK_ROOK, piece.getCElement(), "test is black rook");
    }
}
