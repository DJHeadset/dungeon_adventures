package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.logic.MapChanger;


public class Player extends Actor {

    private int maxHealth;
    private int gold;
    private boolean armor;
    private boolean weapon;

    private MapChanger mapChanger;

    private int level;

    private String statusEffect;

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public boolean hasWeapon() {
        return weapon;
    }

    public boolean hasArmor() {
        return armor;
    }

    public void handleExp(int exp) {
        this.exp += exp;
        while (this.exp >= 10) {
            this.level++;
            this.maxHealth += 10;
            this.attack += 2;
            this.exp = this.exp - 10;
        }
    }

    public Player(Cell cell) {
        super(cell);
        this.health = 30;
        this.maxHealth = health;
        this.attack = 5;
        this.defense = 5;
        this.exp = 0;
        this.level = 1;
        this.gold = 0;
        this.weapon = false;
        this.armor = false;
        this.statusEffect = "none";
    }

    public String getStatusEffect() {
        return statusEffect;
    }

    public String getTileName() {
        if (this.hasWeapon() && !this.hasArmor()) {
            return "playerWithWeapon";
        } else if (!this.hasWeapon() && this.hasArmor()) {
            return "playerWithArmor";
        } else if (this.hasWeapon() && this.hasArmor()) {
            return "playerFullGear";
        } else {
            return "player";
        }
    }

    @Override
    public void move(int dx, int dy) {
        if(health <= 0){
            mapChanger.changeMap("/game-over.txt");
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();
        if ((nextCellType == CellType.FLOOR || nextCellType == CellType.ROAD) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.GOLD) {
            gold += 10;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.ARMOR) {
            defense += 15;
            armor = true;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.WEAPON) {
            attack += 15;
            weapon = true;
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.DOORMAN) {
            if(gold>=10){
                gold -= 10;
                cell.setActor(null);
                nextCell.setType(CellType.FLOOR);
                nextCell.setActor(this);
                cell = nextCell;
            }
        } else if (nextCellType == CellType.DOOR) {
            mapChanger.changeMap("/map02.txt");
        } else if (nextCellType == CellType.HEALTH_POTION) {
            health += 15;
            if(health>maxHealth) {
                health = maxHealth;
            }
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.CURSED) {
            attack += 10;
            statusEffect = "cursed";
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCellType == CellType.ALTAIR) {
            statusEffect = "holy";
            cell.setActor(null);
            nextCell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            cell = nextCell;
        } else if( nextCellType == CellType.PRINCESS){
            mapChanger.changeMap("/gg.txt");
        }
    }

    public void setMapChanger(MapChanger mapChanger) {
        this.mapChanger = mapChanger;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
