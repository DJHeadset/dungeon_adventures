package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            System.out.println("DANGER!!!!");
        }
        if(nextCell.getTileName().equals("floor")){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
