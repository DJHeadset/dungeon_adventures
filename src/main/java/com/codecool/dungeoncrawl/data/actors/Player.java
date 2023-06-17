package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.logic.MapChanger;


public class Player extends Actor {

    private int expToNextLevel;
    private int maxHealth;
    private int gold;
    private boolean armor;
    private boolean weapon;

    private MapChanger mapChanger;

    private int level;

    private String statusEffect;

    public Player(Cell cell) {
        super(30,5,5,0,cell);
        this.maxHealth = health;
        this.expToNextLevel = 10;
        this.level = 1;
        this.gold = 0;
        this.weapon = false;
        this.armor = false;
        this.statusEffect = "none";
    }

    @Override
    public void move(int dx, int dy) {
        if (health <= 0) {
            mapChanger.changeMap("/game-over.txt");
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();
        if ((nextCellType == CellType.FLOOR || nextCellType == CellType.ROAD) && nextCell.getActor() == null) {
            movePlayer(nextCell);
        } else if (nextCellType == CellType.GOLD) {
            gold += 10;
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.ARMOR) {
            defense += 15;
            armor = true;
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.WEAPON) {
            attack += 15;
            weapon = true;
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.DOORMAN) {
            if (gold >= 10) {
                gold -= 10;
                nextCell.setType(CellType.FLOOR);
            }
        } else if (nextCellType == CellType.DOOR) {
            mapChanger.changeMap("/map02.txt");
        } else if (nextCellType == CellType.HEALTH_POTION) {
            health += 15;
            if (health > maxHealth) {
                health = maxHealth;
            }
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.CURSED) {
            attack += 10;
            statusEffect = "cursed";
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.ALTAIR) {
            statusEffect = "holy";
            nextCell.setType(CellType.FLOOR);
        } else if (nextCellType == CellType.PRINCESS) {
            mapChanger.changeMap("/gg.txt");
        }
    }

    private void movePlayer(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public void handleExp(int experience) {
        exp += experience;
        if (exp >= expToNextLevel) {
            level++;
            maxHealth += 10;
            attack += 2;
            expToNextLevel = 10 + level;
        }
    }

    public String getTileName() {
        if (weapon && !armor) {
            return "playerWithWeapon";
        } else if (!weapon && armor) {
            return "playerWithArmor";
        } else if (weapon && armor) {
            return "playerFullGear";
        } else {
            return "player";
        }
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public String getStatusEffect() {
        return statusEffect;
    }

    public void setMapChanger(MapChanger mapChanger) {
        this.mapChanger = mapChanger;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
