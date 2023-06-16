package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Boss extends NPC {

    public Boss(Cell cell) {
        super(cell);
        this.health = 80;
        this.attack = 25;
        this.defense = 15;
        this.exp = 10;
    }

    public void bossFight() {
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        }
    }



    private void attack(Player player) {
        health -= player.attack - defense;
        int damage = attack - player.defense;
        if (damage > 0) {
            player.health -= attack - player.defense;
        }
        if (health <= 0) {
            player.exp += exp;
        }
    }

    @Override
    public String getTileName() {
        return "boss";
    }

}
