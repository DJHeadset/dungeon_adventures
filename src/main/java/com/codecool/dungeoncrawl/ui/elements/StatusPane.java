package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private final GridPane ui;
    private final Label healthTextLabel;
    private final Label levelTextLabel;
    private final Label levelValueLabel;

    private final Label expTextLabel;
    private final Label expValueLabel;
    private final Label attackTextLabel;
    private final Label defenseTextLabel;
    private final Label goldTextLabel;
    private final Label healthValueLabel;
    private final Label attackValueLabel;
    private final Label defenseValueLabel;
    private final Label goldValueLabel;

    public StatusPane() {
        ui = new GridPane();
        levelTextLabel = new Label("Level: ");
        levelValueLabel = new Label();
        expTextLabel = new Label("XP: ");
        expValueLabel = new Label();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        attackTextLabel = new Label("Attack: ");
        attackValueLabel = new Label();
        defenseTextLabel = new Label("Defense: ");
        defenseValueLabel = new Label();
        goldTextLabel = new Label("Gold: ");
        goldValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));
        ui.add(levelTextLabel, 0, 0);
        ui.add(levelValueLabel, 1, 0);
        ui.add(expTextLabel, 0, 1);
        ui.add(expValueLabel, 1, 1);
        ui.add(healthTextLabel, 0, 2);
        ui.add(healthValueLabel, 1, 2);
        ui.add(attackTextLabel, 0, 3);
        ui.add(attackValueLabel, 1, 3);
        ui.add(defenseTextLabel, 0, 4);
        ui.add(defenseValueLabel, 1, 4);
        ui.add(goldTextLabel, 0, 5);
        ui.add(goldValueLabel, 1, 5);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setLevelValue(String text) {
        levelValueLabel.setText(text);
    }

    public void setExpTextLabel(String text) {
        expValueLabel.setText(text);
    }

    public void setAttackValue(String text) {
        attackValueLabel.setText(text);
    }

    public void setDefenseValue(String text) {
        defenseValueLabel.setText(text);
    }

    public void setGoldValue(String text) {
        goldValueLabel.setText(text);
    }
}
