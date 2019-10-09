package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.Bishop;
import org.chessgame.model.board_element.pieces.King;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBishop {
    @Test
    void testCElementReturnWhite() {
        Bishop piece = new Bishop(EColorChess.WHITE);
        assertEquals(CElement.WHITE_BISHOP, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        Bishop piece = new Bishop(EColorChess.BLACK);
        assertEquals(CElement.BLACK_BISHOP, piece.getCElement(), "test is black rook");
    }
}
