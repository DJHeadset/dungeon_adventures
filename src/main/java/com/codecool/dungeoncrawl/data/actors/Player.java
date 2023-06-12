package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Player extends Actor {

    private final int gold;
    private final boolean weapon;

    public int getGold() {
        return gold;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public Player(Cell cell) {
        super(cell);
        this.health = 30;
        this.attack = 10;
        this.defense = 5;
        this.gold = 1500;
        this.weapon = false;
    }

    public String getTileName() {
        return "player";
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
