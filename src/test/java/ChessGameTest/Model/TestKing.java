package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.King;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKing {
    @Test
    void testCElementReturnWhite() {
        King piece = new King(EColorChess.WHITE);
        assertEquals(CElement.WHITE_KING, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        King piece = new King(EColorChess.BLACK);
        assertEquals(CElement.BLACK_KING, piece.getCElement(), "test is black rook");
    }
}
