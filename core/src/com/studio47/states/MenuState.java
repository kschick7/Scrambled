package com.studio47.states;

import com.badlogic.gdx.Gdx;
import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;

/**
 * Created by Kyle on 7/18/2016.
 */
public class MenuState extends GameState {

    public MenuState(GameStateManager gameStateManager, DisplayContext displayContext) {
        super(gameStateManager, displayContext);
    }

    public void init() {
        System.out.println("Hello World");
    }

    public void update(float dt) {

    }

    public void draw(DisplayContext displayContext) {
        if (Gdx.input.isTouched()) {
            gameStateManager.setState(GameStateManager.State.PLAY);
        }
    }

    public void dispose() {

    }
}
