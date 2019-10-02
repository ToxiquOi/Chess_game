package org.chessgame.share.services;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessLogger {

    Logger logger;

    public ChessLogger(Class c) {
         this.logger = Logger.getLogger(c.getName());
    }

    public String getName() {
        return logger.getName();
    }

    public boolean isLoggable(Level level) {
        return logger.isLoggable(level);
    }

    public void log(Level level, String msg) {
        logger.log(level, msg);
    }
}
