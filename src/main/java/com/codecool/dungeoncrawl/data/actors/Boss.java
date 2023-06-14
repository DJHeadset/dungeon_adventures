package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Boss extends Actor {

    public Boss(Cell cell) {
        super(cell);
        this.health = 30;
        this.attack = 10;
        this.defense = 5;
        this.exp = 10;
    }

    public void bossFight(){
        Player player = isPlayerNearby();
        if (player != null) {
            attack(player);
        }
    }

    private Player isPlayerNearby() {
        if (cell.getNeighbor(0, 1).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(0, 1).getActor();
        } else if (cell.getNeighbor(0, -1).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(0, -1).getActor();
        } else if (cell.getNeighbor(1, 0).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(1, 0).getActor();
        } else if (cell.getNeighbor(-1, 0).getActor() instanceof Player) {
            return (Player) cell.getNeighbor(-1, 0).getActor();
        }
        return null;
    }

    private void attack(Player player) {
        System.out.println(health);
        health -= player.attack - defense;
        player.health -= player.health - attack + player.defense;
        if (health <= 0) {
            player.exp += exp;
        }
    }

    @Override
    public String getTileName() {
        return "boss";
    }

}
