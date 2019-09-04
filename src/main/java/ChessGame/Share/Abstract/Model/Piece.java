/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessGame.Share.Abstract.Model;

import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Enum.Move;
import ChessGame.Share.Interfaces.Model.IMovement;

/**
 *
 * @author tox
 */
public abstract class Piece extends BoardElement implements IMovement {

    protected boolean isAlive;
    private ColorChess colorChess;

    public Piece(int x, int y, ColorChess colorChess) {
        super(x, y);
        this.colorChess = colorChess;
    }

    public boolean isAlive() {
        return this.isAlive();
    }

    public ColorChess getColorChess() {
        return colorChess;
    }

    @Override
    public void move(Move move) {
        
        switch(move) {
            case UP: 
                this.moveUp(this.getPosX() + 1, this.getPosY());
                break;
            
            case DOWN: 
                this.moveDown(this.getPosX() - 1, this.getPosY());
                break;
                
            case LEFT:
                this.moveLeft(this.getPosX(), this.getPosY() - 1);
                break;
                
            case RIGHT:
                this.moveRight(this.getPosX(), this.getPosY() + 1);
                break;
                
            default: 
                break;
        }
    }
}
