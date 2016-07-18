package com.studio47.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kyle on 7/18/2016.
 */
public abstract class GameState {

    protected GameState() {
        init();
    }

    public abstract void init();
    public abstract void update();
    public abstract void draw(SpriteBatch batch);
    public abstract void dispose();

}
