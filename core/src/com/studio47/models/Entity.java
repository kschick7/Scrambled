package com.studio47.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio47.context.DisplayContext;

/**
 * Created by Kyle on 7/19/2016.
 */
public abstract class Entity {
    protected float x;
    protected float y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(float dt);
    public abstract void draw();
    public abstract void dispose();
}
