package org.chessgame.view.awt.factory;

import org.chessgame.view.awt.abstracts.GameMode;
import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.mode.MainMenuGameMode;
import org.chessgame.view.awt.mode.OptionMenuGameMode;

import java.util.HashMap;

public class FlyWeightMenuFactory {

    private HashMap<String, MenuGameMode> menuItems = new HashMap<>();

    public FlyWeightMenuFactory() {
        this.menuItems.put(MainMenuGameMode.class.getSimpleName(), new MainMenuGameMode());
        this.menuItems.put(OptionMenuGameMode.class.getSimpleName(), new OptionMenuGameMode());
    }

    public GameMode getMenuItems(Class c) {
        return menuItems.get(c.getSimpleName());
    }
}
