package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    PRINCESS("princess"),
    WEAPON("weapon"),
    DOORMAN("doorman"),
    GOLD("gold"),
    ARMOR("armor"),
    BOSS("boss"),
    DOOR("door");
    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
