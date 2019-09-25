package ChessGame.View.awt.service;

import ChessGame.Controller.Listener.Mouse;
import ChessGame.Model.Abstract.BoardElement;
import ChessGame.Model.Abstract.Piece;
import ChessGame.Model.Board;
import ChessGame.Share.Constant.CBoard;
import ChessGame.View.awt.Graphics.BorderTile;

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
            new BorderTile(g, this.pieceSelectedX, this.pieceSelectedY, this.colorSelect);
        }
    }
}
