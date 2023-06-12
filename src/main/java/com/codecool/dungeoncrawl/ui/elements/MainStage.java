package com.codecool.dungeoncrawl.ui.elements;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class MainStage {
    private final Canvas canvas;
    private final Scene scene;
    private final StatusPane statusPane;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }
    public void setAttackLabelText(String text) {
        this.statusPane.setAttackValue(text);
    }
    public void setDefenseLabelText(String text) {
        this.statusPane.setDefenseValue(text);
    }
    public void setGoldLabelText(String text) {
        this.statusPane.setGoldValue(text);
    }
}
