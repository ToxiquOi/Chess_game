package org.chessgame.view.awt.component;

import org.chessgame.share.constant.CBoard;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

public class BoardPanel extends Panel {

    private transient BufferStrategy bs;
    private BoardCanvas boardCanvas;
    private Dimension d = new Dimension(CBoard.BOARD_WIDTH, CBoard.BOARD_HEIGHT);

    public BoardPanel() {

    }

    public void init() {
        this.createBoardCanvas();
    }

    private void createBoardCanvas() {
        if (this.boardCanvas == null) {
            this.boardCanvas = new BoardCanvas(d);
            this.add(this.boardCanvas);
        }
        if (this.boardCanvas.getWidth() != this.d.width || this.boardCanvas.getHeight() != this.d.height) {
            this.boardCanvas.setPreferredSize(this.d);
            this.boardCanvas.setMinimumSize(this.d);
            this.boardCanvas.setMaximumSize(this.d);
        }
    }

    public Graphics createGraphics() {
        this.bs = this.boardCanvas.getBufferStrategy();
        if (this.bs == null) {
            this.boardCanvas.createBufferStrategy(2);
            this.bs = this.boardCanvas.getBufferStrategy();
        }

        return this.bs.getDrawGraphics();
    }

    public void switchBuffer() {
        if(this.bs != null) {
            this.bs.show();
        }
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        this.boardCanvas.addMouseListener(l);
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        this.boardCanvas.addMouseMotionListener(l);
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        this.boardCanvas.addMouseWheelListener(l);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        this.boardCanvas.addKeyListener(l);
    }
}
