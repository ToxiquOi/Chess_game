package ChessGameTest.Model;

import org.chessgame.model.abstracts.Piece;
import org.chessgame.share.enumeration.EColorChess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPiece {
    private static class PieceClassTest extends Piece {
        public PieceClassTest(EColorChess eColorChess) {
            super(eColorChess);
        }
    }

    PieceClassTest piece;

    @BeforeEach
    void setUp() {
        this.piece = new PieceClassTest(EColorChess.WHITE);
    }

    @Test
    void testGetIsAlive() {
        assertTrue(this.piece.getIsAlive(), "test get isAlive");
    }

    @Test
    void testGetMoves() {
        assertTrue((this.piece.getMoves() != null), "test get moves");
    }

    @Test
    void testDie() {
        this.piece.die();
        assertFalse(this.piece.getIsAlive(), "test piece die");
    }
}
