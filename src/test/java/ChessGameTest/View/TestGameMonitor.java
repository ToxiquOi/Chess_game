package ChessGameTest.View;

import org.chessgame.view.awt.component.GameMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
    @DisplayName("test monitor init")
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
    @DisplayName("test monitor init")
    void testCreateGraphics() {
        this.monitor.createBoardPanel(null);
        Graphics g = this.monitor.createGraphics();
        assertNotNull(g, "test create graphics");
    }
}
