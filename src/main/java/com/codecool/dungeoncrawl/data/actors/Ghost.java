package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.Random;

public class Ghost extends Actor{

    private Random random = new Random();
    public Ghost(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
    public void move(int dx, int dy) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (nextCell.getActor() instanceof Player) {
                System.out.println("ghost attack!!!!");
            } else {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }


    }



    public void act(Player player){
        if(player.getStatusEffect().equals("cursed")) {
            int playerX = player.getX();
            int playerY = player.getY();

            if (isPlayerNearby()) {
                attack();
            } else {
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

    private void attack() {
    }

    private boolean isPlayerNearby(){
        return cell.getNeighbor(0,1).getActor() instanceof Player ||
                cell.getNeighbor(0,-1).getActor() instanceof Player  ||
                cell.getNeighbor(1,0).getActor() instanceof Player  ||
                cell.getNeighbor(-1,0).getActor() instanceof Player ;
    }
}

