package org.chessgame.view.awt.factory;

import org.chessgame.view.awt.abstracts.GameMode;
import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.mode.MainMenuGM;
import org.chessgame.view.awt.mode.OptionMenuGM;
import org.chessgame.view.awt.mode.ScreenOptionMenuGM;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlyWeightMenuFactory {

    private static final Logger logger = Logger.getLogger(FlyWeightMenuFactory.class.getSimpleName());
    private HashMap<String, MenuGameMode> menuItems = new HashMap<>();

    public FlyWeightMenuFactory() {
        this.menuItems.put(MainMenuGM.class.getSimpleName(), null);
        this.menuItems.put(OptionMenuGM.class.getSimpleName(), null);
        this.menuItems.put(ScreenOptionMenuGM.class.getSimpleName() , null);
    }

    public GameMode getMenuItems(Class c) {
        if (menuItems.get(c.getSimpleName()) == null) {
            try {
                Constructor constructor = c.getConstructor();
                MenuGameMode menu = (MenuGameMode) constructor.newInstance();
                this.menuItems.put(c.getSimpleName(), menu);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                logger.log(Level.WARNING, e.toString());
            }
        }

        return this.menuItems.get(c.getSimpleName());
    }
}
