package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label levelTextLabel;
    private Label levelValueLabel;

    private Label expTextLabel;
    private Label expValueLabel;
    private Label attackTextLabel;
    private Label defenseTextLabel;
    private Label goldTextLabel;
    private Label weaponTextLabel;
    private Label healthValueLabel;
    private Label attackValueLabel;
    private Label defenseValueLabel;
    private Label goldValueLabel;
    private Label weaponValueLabel;

    public StatusPane() {
        ui = new GridPane();
        levelTextLabel = new Label();
        levelValueLabel = new Label("Level: ");
        expTextLabel = new Label();
        expValueLabel = new Label("Missing xp: ");
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        attackTextLabel = new Label("Attack: ");
        attackValueLabel = new Label();
        defenseTextLabel = new Label("Defense: ");
        defenseValueLabel = new Label();
        goldTextLabel = new Label("Gold: ");
        goldValueLabel = new Label();
        weaponTextLabel = new Label("Weapon: ");
        weaponValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));
        ui.add(levelTextLabel, 1,0);
        ui.add(levelValueLabel, 0,0);
        ui.add(expTextLabel, 1,1);
        ui.add(expValueLabel, 0,1);
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

    public void setLevelValue(String text) { levelTextLabel.setText(text);}
    public void setExpTextLabel(String text) { expTextLabel.setText(text);}
    public void setAttackValue(String text) {
        attackValueLabel.setText(text);
    }
    public void setDefenseValue(String text) {
        defenseValueLabel.setText(text);
    }
    public void setGoldValue(String text) {
        goldValueLabel.setText(text);
    }
    public void setWeaponValue(String text) {
        weaponValueLabel.setText(text);
    }
}
