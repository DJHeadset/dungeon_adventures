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
    PLAYER("player"),
    PLAYER_WITH_ARMOR("playerWithArmor"),
    PLAYER_WITH_WEAPON("playerWithWeapon"),
    PLAYER_FULL_GEAR("playerFullGear"),
    BOSS("boss");
    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
