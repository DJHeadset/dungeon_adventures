package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Up implements KeyHandler {
    public static final KeyCode code1 = KeyCode.UP;
    public static final KeyCode code2 = KeyCode.W;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code1.equals(event.getCode()) || code2.equals(event.getCode()))
            map.getPlayer().move(0, -1);
    }
}
