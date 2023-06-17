package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {

    protected int health;
    protected int defense;
    protected int attack;
    protected int exp;
    public Cell cell;

    public Actor(int health, int defense, int attack, int exp, Cell cell) {
        this.health = health;
        this.defense =  defense;
        this.attack = attack;
        this.exp = exp;
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void move(int dx, int dy);

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

