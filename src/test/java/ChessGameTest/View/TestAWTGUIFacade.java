package ChessGameTest.View;

import org.chessgame.model.Board;
import org.chessgame.view.awt.AWTGUIFacade;
import org.chessgame.view.awt.component.AWTLayer;
import org.chessgame.view.awt.component.GameMonitor;
import org.chessgame.view.awt.factory.FlyweightSpriteFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TestAWTGUIFacade {
    private static class AWTFacadeClassTest extends AWTGUIFacade {
        public GameMonitor getMonitor() {
            return this.monitor;
        }
        FlyweightSpriteFactory getSpriteLoader() {
            return this.flyweightSpriteFactory;
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
        assertFalse(this.awtguiFacade.endPaint(), "test endPaint if Graphics is null");
    }

    @Test
    void testClearBackground() {
        this.awtguiFacade.beginPaint();
        assertTrue(this.awtguiFacade.clearBackground(), "testClearBackground");
    }

    @Test
    void testClearBackgroundIfGraphicsIsNull() {
        assertFalse(this.awtguiFacade.clearBackground(), "test clear background if graphics is null");
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
        assertFalse(this.awtguiFacade.drawChars(), "test draw characters if graphics is null");
    }

    @Test
    void testDrawBackground() {
        this.awtguiFacade.beginPaint();
        assertTrue(this.awtguiFacade.drawBackground(), "test draw background");
    }

    @Test
    void testDrawBackgroundIfGraphicsIsNull() {
        assertFalse(this.awtguiFacade.drawBackground(), "test draw background if graphics is null");
    }

    @Test
    void testDrawLayer() {
        this.awtguiFacade.beginPaint();
        AWTLayer awtLayer = new AWTLayer();
        awtLayer.setSpriteCount(1);
        awtLayer.setTileSize(new Dimension(75, 50));
        awtLayer.setTexture("chess_pieces.png");
        awtLayer.setSpriteTexture(0, 0, 0);
        awtLayer.setSpriteLocation(0, 1, 2);

        assertTrue(this.awtguiFacade.drawLayer(awtLayer), "test draw layer");
    }

    @Test
    void testDrawLayerIfGraphicsIsNull() {
        assertFalse(this.awtguiFacade.drawLayer(new AWTLayer()));
    }

}
