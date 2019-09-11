package ChessGame.View.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ChessFont {

    Font chessFont;

    public ChessFont() {
        try {
            InputStream is = new FileInputStream(System.getProperty("user.dir") + "/rsc/stchess.ttf");
            this.chessFont = Font.createFont(Font.TRUETYPE_FONT, is);
            this.chessFont.deriveFont(12f);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Font getChessFont() {
        return chessFont;
    }
}
