package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    private Random random;


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers, Random random) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
        this.random = random;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }
        refresh();
    }

    public void refresh() {
        moveSkeletons();
        moveGhost();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());
    }

    private void moveSkeletons(){
        List<Skeleton> skeletons = logic.getMap().getSkeletons();
        for(Skeleton skeleton : skeletons){
            int movements = 1;
            int moveX = random.nextInt(3)-1;
            int moveY = random.nextInt(3)-1;
            skeleton.move(moveX, moveY);
            if(random.nextBoolean()){
                skeleton.move(moveX, 0);
            }
            else {
                skeleton.move(0, moveY);
            }
        }
    }
    private void moveGhost(){
        Cell playerLocation = logic.getMap().getPlayer().getCell();
        Cell ghostLocation = logic.getMap().getGhost().getCell();
        int moveX = playerLocation.getX() > ghostLocation.getX()? 1 :
                playerLocation.getX() == ghostLocation.getX()? 0: -1;
        int moveY = playerLocation.getY() > ghostLocation.getY()? 1 :
                playerLocation.getY() == ghostLocation.getY()? 0: -1;
        if(random.nextBoolean()){
            if(moveX != 0) {
                logic.getMap().getGhost().move(moveX, 0);
            }
            else {
                logic.getMap().getGhost().move(moveX, moveY);
            }
        }
        else {
            if(moveY != 0){
                logic.getMap().getGhost().move(0, moveY);
            }
            else {
                logic.getMap().getGhost().move(moveX, moveY);
            }
        }



    }
}
