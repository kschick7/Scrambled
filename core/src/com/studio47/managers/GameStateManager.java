package com.studio47.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio47.context.DisplayContext;
import com.studio47.states.GameState;
import com.studio47.states.MenuState;
import com.studio47.states.PlayState;

/**
 * Created by Kyle on 7/18/2016.
 */
public class GameStateManager {
    private GameState currentState;

    public static class State {
        public static final int MENU = 0;
        public static final int PLAY = 1;
        public static final int PAUSED = 2;
        public static final int GAME_OVER = 3;
    }

    public GameStateManager() {
        setState(State.MENU);
    }

    public void setState(int state) {
        if (currentState != null)
            currentState.dispose();

        switch (state) {
            case State.MENU:
                currentState = new MenuState(this, display);
                break;
            case State.PLAY:
                currentState = new PlayState(this);
                break;
            default:
                System.out.println("ERROR: Invalid state specified");
        }
    }

    public void update(float dt) {
        currentState.update(dt);
    }

    public void draw(DisplayContext displayContext) {
        currentState.draw(displayContext);
    }

    public void dispose() {
        currentState.dispose();
    }
}
