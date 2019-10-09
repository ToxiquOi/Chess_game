package ChessGameTest.Model;

import org.chessgame.model.board_element.pieces.Knight;
import org.chessgame.share.constant.CElement;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKngiht {
    @Test
    void testCElementReturnWhite() {
        Knight piece = new Knight(EColorChess.WHITE);
        assertEquals(CElement.WHITE_KNIGHT, piece.getCElement(), "test is white rook");
    }

    @Test
    void testCElementReturnBlack() {
        Knight piece = new Knight(EColorChess.BLACK);
        assertEquals(CElement.BLACK_KNIGHT, piece.getCElement(), "test is black rook");
    }
}
