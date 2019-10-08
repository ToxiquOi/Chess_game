package ChessGameTest.View;

import org.chessgame.view.awt.component.GameMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.WindowAdapter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameMonitor  {
    private static class GameMonitorTestClass extends GameMonitor {

        public GameMonitorTestClass(String title) {
            super(title);
        }
    }

    GameMonitorTestClass monitor;


    @BeforeEach
    void setUp() {
        this.monitor = new GameMonitorTestClass("test_chess");
        this.monitor.init();
    }

    @Test
    void testInit() {
        boolean isClosingRequestIsFalse = false;
        boolean isWindowAdaptaterExist = false;
        boolean allCritAsValidate = false;

        if (!this.monitor.isClosingRequested()) {
            isClosingRequestIsFalse = true;
        }

        if (this.monitor.getWindowListeners()[0] instanceof WindowAdapter) {
            isWindowAdaptaterExist = true;
        }

        if (isClosingRequestIsFalse && isWindowAdaptaterExist) {
            allCritAsValidate = true;
        }

        assertTrue(allCritAsValidate, "test init GameMonitor");
    }

    @Test
    void testCreateGraphics() {
        this.monitor.createBoardComponent();
        Graphics g = this.monitor.createGraphics();
        assertNotNull(g, "test create graphics");
    }
}
