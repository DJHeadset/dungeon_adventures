package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("playerWithWeapon",new Tile(26, 0));
        tileMap.put("playerWithArmor",new Tile(30, 0));
        tileMap.put("playerFullGear",new Tile(28, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("princess", new Tile(29, 3));
        tileMap.put("weapon", new Tile(10,30));
        tileMap.put("doorman", new Tile(24,2));
        tileMap.put("gold", new Tile(9,26));
        tileMap.put("armor", new Tile(4,23));
        tileMap.put("boss", new Tile(30,6));
        tileMap.put("ghost", new Tile(24,8));
        tileMap.put("door", new Tile(20,12));
        tileMap.put("cursed", new Tile(3,24));
        tileMap.put("altair", new Tile(2,14));
        tileMap.put("healthPotion", new Tile(25,23));
        tileMap.put("tree",new Tile(3,1));
        tileMap.put("tree2",new Tile(4,1));
        tileMap.put("road",new Tile(1,0));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
