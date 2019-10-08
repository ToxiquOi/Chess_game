package ChessGameTest.View;

import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.awt.component.GameMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestAWTGUIFacade {
    private static class AWTFacadeClassTest extends AWTGUIFacade {
        public GameMonitor getMonitor() {
            return this.monitor;
        }

        public Graphics getGraphics() {
            return this.g;
        }
    }

    AWTFacadeClassTest awtguiFacade;

    @BeforeEach
    void setUp() {
        this.awtguiFacade = new AWTFacadeClassTest();
        this.awtguiFacade.createWindow("chess_test");
    }

    @Test
    void testIsCreateMonitor() {
        assertNotNull(this.awtguiFacade.getMonitor(), "test create monitor");
    }

    @Test
    void testBeginPaint() {
        assertTrue(this.awtguiFacade.beginPaint(), "test begin paint");
    }

    @Test
    void testEndPaint() {
        this.awtguiFacade.beginPaint();
        this.awtguiFacade.endPaint();
        assertNull(this.awtguiFacade.getGraphics(), "test begin paint");
    }

    @Test
    void testClearBackground() {
        this.awtguiFacade.beginPaint();
        this.awtguiFacade.clearBackground();

        assertEquals(this.awtguiFacade.getGraphics().getColor(), Color.BLACK, "testClearBackground");
    }
}
