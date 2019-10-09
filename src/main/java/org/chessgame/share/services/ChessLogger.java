package org.chessgame.share.services;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessLogger {

    private Handler handler;
    protected Logger logger;

    public ChessLogger(Class c) {
         this.logger = Logger.getLogger(c.getSimpleName());
    }

    public String getName() {
        return logger.getName();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
        this.logger.addHandler(handler);

    }

    public Handler getHandler() {
        return this.handler;
    }

    public void removeHandler() {
        this.logger.removeHandler(this.handler);
        this.handler = null;
    }

    public void log(Level level, String msg) {
        logger.log(level, msg);
    }
}
