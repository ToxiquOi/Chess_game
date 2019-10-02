package org.chessgame.view.awt.service;

import org.chessgame.controller.listener.Mouse;
import org.chessgame.model.abstract_class.BoardElement;
import org.chessgame.model.abstract_class.Piece;
import org.chessgame.model.Board;
import org.chessgame.share.constant.CBoard;
import org.chessgame.view.awt.graphics.BorderTile;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TileSelector {

    private Mouse mouse;
    private Board board;

    private int pieceSelectedX = 0;
    private int pieceSelectedY = 0;
    private Color colorSelect = null;

    public TileSelector(Board board, Mouse mouse) {
        this.board = board;
        this.mouse = mouse;
    }

    private boolean selectTile() {
        if(this.board.isInstanceOfPiece(this.board.getElement(this.mouse.getY() / CBoard.TILE_HEIGHT_PX, this.mouse.getX() / CBoard.TILE_WIDTH_PX))) {
            this.pieceSelectedX = this.mouse.getX() / CBoard.TILE_WIDTH_PX;
            this.pieceSelectedY = this.mouse.getY() / CBoard.TILE_HEIGHT_PX;
            return true;
        }
        else {
            this.pieceSelectedX = -1;
            this.pieceSelectedY = -1;
            return false;
        }
    }

    public void clickChecker() {
        if (this.mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            if(this.selectTile()) {
                this.colorSelect = new Color(0x128E00);
            }

        } else if (this.mouse.isButtonPressed(MouseEvent.BUTTON3)) {
            if(this.selectTile()) {
                this.colorSelect = new Color(0xEB0900);
                BoardElement boardElement = this.board.getElement(this.mouse.getY() / CBoard.TILE_HEIGHT_PX, this.mouse.getX() / CBoard.TILE_WIDTH_PX);
                if(this.board.isInstanceOfPiece(boardElement)) {
                    Piece piece = (Piece) boardElement;
//                    this.board.moveElement((this.mouse.getY() / CBoard.TILE_HEIGHT_PX) + 2, (this.mouse.getX() / CBoard.TILE_WIDTH_PX) + 2, piece);
                    piece.die();
                    this.board.setEmptyElement(this.mouse.getY() / CBoard.TILE_HEIGHT_PX, this.mouse.getX() / CBoard.TILE_WIDTH_PX);
                }
            }
        }
    }

    public void drawTile(Graphics g) {
        if(this.pieceSelectedX != -1 && this.pieceSelectedY != -1 && this.colorSelect != null) {
            BorderTile.draw(g, this.pieceSelectedX, this.pieceSelectedY, this.colorSelect);
        }
    }
}
