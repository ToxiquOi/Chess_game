package ChessGame.Share.Iterator;

import ChessGame.View.awt.Canvas.BoardComponent;
import ChessGame.View.awt.Graphics.CaseBoard;

import java.util.Iterator;

public class CaseBoardIterator implements Iterator<CaseBoard> {

    private BoardComponent boardComponent;
    private int x;
    private int y;

    public CaseBoardIterator(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
        this.x = -1;
        this.y = 0;
    }

    @Override
    public boolean hasNext() {
        if(this.x + 1 < this.boardComponent.getWidth()) {
            return true;
        }

        if (this.y + 1 < this.boardComponent.getHeight()) {
            return true;
        }

        return false;
    }

    @Override
    public CaseBoard next() {
        return null;
    }

//    public CaseBoard next(Graphics g) {
//        if(this.x + 1 < this.boardComponent.getWidth()){
//            this.x++;
//        }
//        else if(this.y + 1 < this.boardComponent.getHeight()){
//            this.x = 0;
//            this.y++;
//        }
//        else {
//            throw new NoSuchElementException();
//        }
//
//
////        return this.boardComponent.setAndGetElement(this.x, this.y, g);
//    }
}
