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
    HEALTH_POTION("healthPotion"),
    TREE("tree"),
    TREE2("tree2"),
    ROAD("road"),
    DOOR("door"),
    CURSED("cursed"),
    ALTAIR("altair");
    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
