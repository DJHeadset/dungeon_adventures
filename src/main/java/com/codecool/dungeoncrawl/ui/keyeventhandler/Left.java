package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Left implements KeyHandler {
    public static final KeyCode code1 = KeyCode.LEFT;
    public static final KeyCode code2 = KeyCode.A;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code1.equals(event.getCode())){
            map.getPlayers().get(0).move(-1,0);
        }
        if(code2.equals(event.getCode())){
            map.getPlayers().get(1).move(-1,0);
        }
    }
}
