package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.Queen;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestQueen {
    @Test
    void testCElementReturnWhite() {
        Queen piece = new Queen(EColorChess.WHITE);
        assertEquals(CElement.WHITE_QUEEN, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        Queen piece = new Queen(EColorChess.BLACK);
        assertEquals(CElement.BLACK_QUEEN, piece.getCElement(), "test is black rook");
    }
}
