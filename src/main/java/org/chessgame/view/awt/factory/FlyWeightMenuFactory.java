package org.chessgame.view.awt.factory;

import org.chessgame.view.awt.abstracts.GameMode;
import org.chessgame.view.awt.abstracts.MenuGameMode;
import org.chessgame.view.awt.mode.MainMenuGM;
import org.chessgame.view.awt.mode.OptionMenuGM;
import org.chessgame.view.awt.mode.ScreenOptionMenuGM;

import java.util.HashMap;

public class FlyWeightMenuFactory {

    private HashMap<String, MenuGameMode> menuItems = new HashMap<>();

    public FlyWeightMenuFactory() {
        this.menuItems.put(MainMenuGM.class.getSimpleName(), new MainMenuGM());
        this.menuItems.put(OptionMenuGM.class.getSimpleName(), new OptionMenuGM());
        this.menuItems.put(ScreenOptionMenuGM.class.getSimpleName() , new ScreenOptionMenuGM());
    }

    public GameMode getMenuItems(Class c) {
        return menuItems.get(c.getSimpleName());
    }
}
