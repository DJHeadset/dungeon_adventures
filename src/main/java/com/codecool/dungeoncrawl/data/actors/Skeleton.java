package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.Random;

public class Skeleton extends Actor {

    private Random random = new Random();
    private int health;
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 10;
        this.exp = 5;

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getTileName().equals("floor") && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void attack(){
    }

    public void act(){
        if(isPlayerNearby()){

            attack();
        }
        else{
            int movements = 1;
            int moveX = random.nextInt(3)-1;
            int moveY = random.nextInt(3)-1;
            if(random.nextBoolean()){
                move(moveX, 0);
            }
            else {
                move(0, moveY);
            }
        }
    }

    private boolean isPlayerNearby(){
        return cell.getNeighbor(0,1).getActor() instanceof Player ||
                cell.getNeighbor(0,-1).getActor() instanceof Player  ||
                cell.getNeighbor(1,0).getActor() instanceof Player  ||
                cell.getNeighbor(-1,0).getActor() instanceof Player ;
    }

    public void setHealth(){
        health--;
    }
}
