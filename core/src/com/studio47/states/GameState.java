package com.studio47.states;

import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;

/**
 * Created by Kyle on 7/18/2016.
 */
public abstract class GameState {
    protected GameStateManager gameStateManager;
    protected DisplayContext displayContext;

    protected GameState(GameStateManager gameStateManager, DisplayContext displayContext) {
        this.gameStateManager = gameStateManager;
        this.displayContext = displayContext;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw(DisplayContext displayContext);
    public abstract void dispose();

}
