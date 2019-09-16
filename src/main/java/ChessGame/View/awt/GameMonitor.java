package ChessGame.View.awt;

import ChessGame.Model.Board;
import ChessGame.Share.Abstract.Model.BoardElement;
import ChessGame.Share.Abstract.Model.Piece;
import ChessGame.Share.Enum.ColorChess;
import ChessGame.Share.Enum.EWindow;
import ChessGame.Share.Iterator.BoardIterator;
import ChessGame.View.awt.Component.BoardComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GameMonitor extends Frame implements Runnable {

    private boolean running = false;
    private Dimension d = new Dimension(EWindow.WIDTH, EWindow.HEIGHT);
    private BoardComponent boardComponent;
    private BoardIterator bIterator;

    public GameMonitor(String title, Board board) throws HeadlessException {
        super(title);
        this.bIterator = board.iterator();
        this.init();
    }

    private void init() {
        this.setPreferredSize(this.d);
        this.setMaximumSize(this.d);
        this.setMinimumSize(this.d);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.pack();
        this.running = true;

        this.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    running = false;
                }
            }
        );
    }

    @Override
    public void run() {
        int fps = 60;
        long nanoPerFrame = (long) (1000000000.0 / fps);
        long lastTime = System.nanoTime();

        this.createBoardComponent();

        while (this.running) {
            long nowTime = System.nanoTime();
            if((nowTime - lastTime) < nanoPerFrame) {
                continue;
            }
            lastTime = nowTime;

            this.render();

            long elapsed = System.nanoTime() - lastTime;
            long millisleep = (nanoPerFrame - elapsed) / 1000000;
            if(millisleep > 0) {
                try {
                    Thread.sleep(millisleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.dispose();
    }

    private void render() {
        Component component = this.getComponent(0);

        if (component instanceof BoardComponent) {
            BoardComponent bc = (BoardComponent) component;
            BufferStrategy bs = bc.getBufferStrategy();

            if (bs == null) {
                bc.createBufferStrategy(2);
                bs = bc.getBufferStrategy();
            }

            Graphics g;

            try {
                g = bs.getDrawGraphics();
                if(g == null) {
                    System.out.println("fck");
                }
                else {
                    System.out.println("draw");
                }
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 100, 100);
                bs.show();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void createBoardComponent() {
        BoardComponent boardComponent = new BoardComponent(d, this.bIterator);
        this.add(boardComponent);
        this.pack();
    }
}

//    private void render() {
//        while(this.bIterator.hasNext()) {
//            BoardElement boardElement = this.bIterator.next();
//
//            if (boardElement instanceof Piece) {
//                Piece element = (Piece) boardElement;
//
//                final BufferedImage image = this.loadTexture(element);
//                JPanel component = new JPanel() {
//                    @Override
//                    public void paint(Graphics g) {
//                        super.paint(g);
//                        if(image == null){
//                            System.out.println("null");
//                        }
//                        g.drawImage(image,  element.getPosX() * 100, element.getPosY() * 100,null);
//                    }
//                };
//                this.add(component);
//
//            } else {
////                System.out.println(boardElement.getElement());
//            }
//        }
//    }

//    private BufferedImage loadTexture(Piece element) {
//        String firstChar = "" + element.getElement().toString().charAt(0);
//        String elementNameFormated = element.getElement().toString().toLowerCase().replace(firstChar.toLowerCase(), firstChar);
//        String spritePath = ((element.getColorChess() == ColorChess.WHITE)? "White" : "Black") + elementNameFormated + ".png";
//
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new FileInputStream(System.getProperty("user.dir") + "/rsc/Pieces/" + spritePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return image;
//    }
