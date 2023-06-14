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

import java.util.*;

public class UI {
    private final Canvas canvas;
    private final GraphicsContext context;

    private final MainStage mainStage;
    private final GameLogic logic;
    private final Set<KeyHandler> keyHandlers;

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
        getSkeletonActions();
        getGhostAction();
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
        mainStage.setLevelLabelText(logic.getPlayerLevel());
        mainStage.setExpLabelText(Integer.toString(10 - logic.getPlayerExp()));
        mainStage.setHealthLabelText(logic.getPlayerHealth());
        mainStage.setAttackLabelText(logic.getPlayerAttack());
        mainStage.setDefenseLabelText(logic.getPlayerDefense());
        mainStage.setGoldLabelText(logic.getPlayerGold());
    }

    private void getSkeletonActions() {
        List<Skeleton> skeletons = logic.getMap().getSkeletons();
        List<Skeleton> activeSkeletons = new ArrayList<>(skeletons);

        Iterator<Skeleton> iterator = activeSkeletons.iterator();
        while (iterator.hasNext()) {
            Skeleton skeleton = iterator.next();
            skeleton.act();
            if (skeleton.getHealth() <= 0) {
                skeleton.getCell().setActor(null);
                iterator.remove();
            }
        }
        System.out.println("------");
        logic.getMap().setSkeletons(activeSkeletons);
    }

    private void getGhostAction() {
        if(logic.getMap().getGhost() != null) {
            if (!logic.getMap().getPlayer().getStatusEffect().equals("holy")) {
                logic.getMap().getGhost().act(logic.getMap().getPlayer());
            } else if (logic.getMap().getPlayer().getStatusEffect().equals("holy")) {
                logic.getMap().removeGhost();
            }
        }

    }
}

