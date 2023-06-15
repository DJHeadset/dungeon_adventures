package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Set;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        String mapString = "/map01.txt";
        Set<KeyHandler> keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        GameLogic logic = new GameLogic(mapString);
        UI ui = new UI(logic, keyHandlers);
        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
