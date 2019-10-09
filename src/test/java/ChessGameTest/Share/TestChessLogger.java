package ChessGameTest.Share;

import org.chessgame.share.services.ChessLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TestChessLogger {
    private static class ChessLoggerClassTest extends ChessLogger {
        public ChessLoggerClassTest(Class c) {
            super(c);
        }

        public Logger getLogger() {
            return this.logger;
        }
    }

    ChessLoggerClassTest chessLogger;

    @BeforeEach
    void setUp() {
        this.chessLogger = new ChessLoggerClassTest(TestChessLogger.class);
    }

    @Test
    void testGetName() {
        assertEquals(TestChessLogger.class.getSimpleName(), this.chessLogger.getName(), "test get name");
    }

    @Test
    void testCreateLogger() {
        assertNotNull(this.chessLogger.getLogger(), "test create logger");
    }

    @Test
    void testSetHandler() {
        this.chessLogger.setHandler(new ConsoleHandler());
        assertNotNull(this.chessLogger.getHandler(), "test set handler");
    }

    @Test
    void testRemoveHandler() {
        this.chessLogger.setHandler(new ConsoleHandler());
        this.chessLogger.removeHandler();
        assertNull(this.chessLogger.getHandler(), "test set handler");
    }
}
