package ChessGameTest.View;

import org.chessgame.model.Board;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.services.SpriteLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TestAWTGUIFacade {
    private static class AWTFacadeClassTest extends AWTGUIFacade {
        GameMonitor getMonitor() {
            return this.monitor;
        }
        SpriteLoader getSpriteLoader() {
            return this.spriteLoader;
        }
        Board getBoard() {
            return this.board;
        }
        Graphics getGraphics() {
            return this.g;
        }
        void setGraphics(Graphics g) {
            this.g = g;
        }
    }

    private AWTFacadeClassTest awtguiFacade;

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
    void testBeginPaintIfGraphicsNotNull() {
        this.awtguiFacade.setGraphics(this.awtguiFacade.getMonitor().createGraphics());
        assertNull(this.awtguiFacade.beginPaint(), "test BeginPaint if Graphics not null");
    }

    @Test
    void testEndPaint() {
        this.awtguiFacade.beginPaint();
        assertTrue(this.awtguiFacade.endPaint(), "test begin paint");
    }

    @Test
    void testEndPaintIfGraphicsIsNull() {
        assertNull(this.awtguiFacade.endPaint(), "test endPaint if Graphics is null");
    }

    @Test
    void testClearBackground() {
        this.awtguiFacade.beginPaint();
        this.awtguiFacade.clearBackground();

        assertEquals(Color.BLACK, this.awtguiFacade.getGraphics().getColor(), "testClearBackground");
    }

    @Test
    void testClearBackgroundIfGraphicsIsNull() {
        assertNull(this.awtguiFacade.clearBackground(), "test clear background if graphics is null");
    }

    @Test
    void testCreateNewLayer() {
        assertNotNull(this.awtguiFacade.createLayer(), "test create layer");
    }

    @Test
    void testCreateSpriteLoader() {
        this.awtguiFacade.createSpriteLoader(new Board());
        assertNotNull(this.awtguiFacade.getSpriteLoader(), "test create sprite loader");
    }

    @Test
    void testBoardDefineWhenSpriteLoaderAsCreate() {
        this.awtguiFacade.createSpriteLoader(new Board());
        assertNotNull(this.awtguiFacade.getBoard(), "test board is define");
    }

    @Test
    void testDrawChar() {
        this.awtguiFacade.createSpriteLoader(new Board());
        this.awtguiFacade.beginPaint();
        assertTrue(this.awtguiFacade.drawChars(), "test draw characters");
    }

    @Test
    void testDrawCharIfGraphicsIsNull() {
        this.awtguiFacade.createSpriteLoader(new Board());
        assertNull(this.awtguiFacade.drawChars(), "test draw characters if graphics is null");
    }
}
