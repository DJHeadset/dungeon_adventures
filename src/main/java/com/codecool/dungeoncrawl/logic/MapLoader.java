package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Boss;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapString) {
        InputStream is = MapLoader.class.getResourceAsStream(mapString);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addSkeleton(new Skeleton(cell));
                            break;
                        case 'g' :
                            cell.setType(CellType.EMPTY);
                            map.setGhost(new Ghost(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.PRINCESS);
                            break;
                        case 'w':
                            cell.setType(CellType.WEAPON);
                            break;
                        case '6':
                            cell.setType(CellType.CURSED);
                            break;
                        case '+' :
                            cell.setType(CellType.ALTAIR);
                            break;
                        case 'd':
                            cell.setType(CellType.DOORMAN);
                            break;
                        case 'c':
                            cell.setType(CellType.GOLD);
                            break;
                        case 'a':
                            cell.setType(CellType.ARMOR);
                            break;
                        case 'b':
                            cell.setType(CellType.BOSS);
                            map.setBoss(new Boss(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.HEALTH_POTION);
                            break;
                        case 'x':
                            cell.setType(CellType.DOOR);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
