package ChessGameTest.Model;

import org.chessgame.model.abstract_class.BoardElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoardElement {
    private static class BoardElementClassTest extends BoardElement {

    }

    BoardElementClassTest boardElement;

    @BeforeEach
    void setUp() {
        this.boardElement = new BoardElementClassTest();
    }

    @Test
    void testGetCElement() {
        assertEquals(-1, this.boardElement.getCElement(), "test get CElement");
    }
}
