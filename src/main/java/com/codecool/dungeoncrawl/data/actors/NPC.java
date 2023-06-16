package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public abstract class NPC extends Actor{
    public NPC(Cell cell) {
        super(cell);
    }

    protected Player isPlayerNearby() {
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
}
