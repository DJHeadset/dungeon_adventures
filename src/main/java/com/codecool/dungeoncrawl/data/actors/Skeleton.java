package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Skeleton extends NPC {
    private final Random random = new Random();

    public char name;

    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.attack = 10;
        this.defense = 1;
        this.exp = 5;
        name = (char) random.nextInt(26);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getTileName().equals("floor") && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void act() {
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        } else {
            int moveX = random.nextInt(3) - 1;
            int moveY = random.nextInt(3) - 1;
            if (random.nextBoolean()) {
                move(moveX, 0);
            } else {
                move(0, moveY);
            }
        }
    }


}

