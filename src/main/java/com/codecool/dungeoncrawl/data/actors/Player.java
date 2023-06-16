package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.logic.MapChanger;


public class Player extends Actor {
    private int maxHealth;
    private int gold;
    private boolean armor;
    private boolean weapon;

    protected MapChanger mapChanger;

    private int level;

    private String statusEffect;

    private static int number = 1;

    public int id;

    public Player(Cell cell) {
        super(cell);
        id = number++;
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

    @Override
    public void move(int dx, int dy) {
        if (health <= 0) {
            mapChanger.changeMap("/game-over.txt");
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getActor() != null){
            attack(nextCell.getActor());
        }
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

    public void handleExp(int exp) {
        this.exp += exp;
        if (this.exp >= 10) {
            level++;
            health += 10;
            maxHealth += 10;
            attack += 2;
            this.exp = this.exp - 10;
        }
    }

    public String getTileName() {
        if(id % 2 == 0){
            return "player2";
        }
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
