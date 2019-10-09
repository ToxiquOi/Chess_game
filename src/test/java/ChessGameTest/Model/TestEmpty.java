package ChessGameTest.Model;

import org.chessgame.model.board_element.static_element.Empty;
import org.chessgame.share.constant.CElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmpty {

    Empty empty;

    @BeforeEach
    void setUp() {
        this.empty = new Empty();
    }

    @Test
    void testGetCElement() {
        assertEquals(CElement.EMPTY, this.empty.getCElement(), "test getCElement return CElement.empty");
    }
}
