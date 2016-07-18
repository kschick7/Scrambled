package com.studio47.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio47.states.GameState;
import com.studio47.states.MenuState;
import com.studio47.states.PlayState;

/**
 * Created by Kyle on 7/18/2016.
 */
public class GameStateManager {
    private GameState currentState;

    // States
    private static final int MENU = 0;
    private static final int PLAY = 1;
    private static final int PAUSED = 2;
    private static final int GAME_OVER = 3;

    public GameStateManager() {
        setState(MENU);
    }

    public void setState(int state) {
        if (currentState != null)
            currentState.dispose();

        switch (state) {
            case MENU:
                currentState = new MenuState();
                break;
            case PLAY:
                currentState = new PlayState();
                break;
            default:
                System.out.println("ERROR: Invalid state specified");
        }
    }

    public void draw(SpriteBatch batch) {
        currentState.draw(batch);
    }

    public void dispose() {
        currentState.dispose();
    }
}
