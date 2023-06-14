package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Boss;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;
    private ArrayList<Skeleton> skeletons = new ArrayList<>();
    private Ghost ghost;
    private Boss boss;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public void removeGhost() {
        ghost.getCell().setActor(null);
    }

    public Ghost getGhost() {
        return ghost;
    }
    public Boss getBoss() {
        return boss;
    }

    public List<Skeleton> getSkeletons() {
        return skeletons;
    }

    public void addSkeleton(Skeleton skeleton) {
        skeletons.add(skeleton);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void setSkeletons(List<Skeleton> activeSkeletons) {
        skeletons = new ArrayList<>(activeSkeletons);
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public void bossRemove() {
        boss.getCell().setActor(null);
    }
}

