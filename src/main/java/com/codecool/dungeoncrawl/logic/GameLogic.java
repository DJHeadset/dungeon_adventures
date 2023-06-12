package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class GameLogic {
    private final GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }
    public String getPlayerAttack() {
        return Integer.toString(map.getPlayer().getAttack());
    }
    public String getPlayerDefense() {
        return Integer.toString(map.getPlayer().getDefense());
    }
    public String getPlayerGold() {
        return Integer.toString(map.getPlayer().getGold());
    }

    public GameMap getMap() {
        return map;
    }
}
