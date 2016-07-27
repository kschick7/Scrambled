package com.studio47.states;

import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;

/**
 * Created by Kyle on 7/18/2016.
 */
public abstract class GameState {
    protected GameStateManager gameStateManager;

    protected GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update(float dt);
    public abstract void draw();
    public abstract void dispose();

}
