package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Skeleton extends Actor {
    private Random random = new Random();
    private int health;

    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.attack = 10;
        this.defense = 1;
        this.exp = 5;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public int getHealth() {
        return health;
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

    private void attack(Player player) {
        int damage = attack - player.defense;
        if(damage > 0) {
            player.health -= attack - player.defense;
        }
        health -= player.attack - defense;
        if (health <= 0) {
            player.handleExp(exp);
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

    private Player isPlayerNearby() {
        if (cell.getNeighbor(0, 1).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(0, 1).getActor();
        } else if (cell.getNeighbor(0, -1).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(0, -1).getActor();
        } else if (cell.getNeighbor(1, 0).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(1, 0).getActor();
        } else if (cell.getNeighbor(-1, 0).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(-1, 0).getActor();
        }
        return null;
    }
}

