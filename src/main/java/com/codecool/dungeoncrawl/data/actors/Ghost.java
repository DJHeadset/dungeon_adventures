package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Ghost extends NPC {

    private final Random random = new Random();

    public Ghost(Cell cell) {
        super(cell);
        health = 100000;
        attack = 3;

    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    public void act(Player playerGlobal) {
        Player player = isPlayerNearby();
        if(player != null){
            attack(player);
        }
       else if (playerGlobal.getStatusEffect().equals("cursed")) {
            int playerX = playerGlobal.getX();
            int playerY = playerGlobal.getY();

            int moveX = Integer.compare(playerX, cell.getX());
            int moveY = Integer.compare(playerY, cell.getY());
            if (random.nextBoolean()) {
                if (moveX != 0) {
                    move(moveX, 0);
                } else {
                    move(moveX, moveY);
                }
            } else {
                if (moveY != 0) {
                    move(0, moveY);
                } else {
                    move(moveX, moveY);
                }
            }
        }

    }
}

