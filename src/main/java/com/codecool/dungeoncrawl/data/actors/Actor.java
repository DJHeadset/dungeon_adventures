package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;

public abstract class Actor implements Drawable {

    protected int health;
    protected int defense;
    protected int attack;
    protected int exp;
    public Cell cell;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }
    protected void attack(Actor actor) {
        System.out.println("attacker: " + this.getTileName() + "defender: " + actor.getTileName());
        int damage = this.attack - actor.defense;
        if(damage > 0) {
            System.out.println(actor.health);
            actor.health = actor.health - damage;
            System.out.println(actor.health);
        }
        if(this instanceof Player){
            if(actor.health <= 0){
                ((Player) this).handleExp(actor.exp);
            }
        }
        else {
            if(actor.health <= 0){
                ((Player)actor).mapChanger.changeMap("/gameover.txt");
            }
        }
    }


    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}

