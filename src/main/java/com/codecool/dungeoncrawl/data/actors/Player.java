package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

public class Player extends Actor {

    private int gold;
    private boolean armor;
    private boolean weapon;

    private String statusEffect = "none";

    public int getGold() {
        return gold;
    }

    public boolean hasWeapon() {
        return weapon;
    }
    public boolean hasArmor() {
        return armor;
    }

    public Player(Cell cell) {
        super(cell);
        this.health = 30;
        this.attack = 10;
        this.defense = 5;
        this.gold = 1500;
        this.weapon = false;
        this.armor = false;
    }

    public String getStatusEffect(){
        return statusEffect;
    }

    public String getTileName() {
        if(this.hasWeapon() && !this.hasArmor()) {
            return "playerWithWeapon";
        } else if(!this.hasWeapon() && this.hasArmor()) {
            return "playerWithArmor";
        } else if(this.hasWeapon() && this.hasArmor()) {
            return "playerFullGear";
        } else {
            return "player";
        }
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
           if(nextCell.getActor() instanceof Skeleton){
               ((Skeleton) nextCell.getActor()).setHealth();
           }
        }
        if(nextCell.getTileName().equals("floor") && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getTileName().equals("gold")) {
            gold += 10;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getTileName().equals("armor")) {
            defense += 10;
            armor = true;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getTileName().equals("weapon")) {
            attack += 10;
            weapon = true;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getTileName().equals("doorman")) {
            gold -= 10;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getTileName().equals("healthPotion")) {
            health += 10;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if(nextCell.getTileName().equals("cursed")){
            attack += 20;
            statusEffect = "cursed";
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if (nextCell.getTileName().equals("altair")) {
            statusEffect = "holy";
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
}
