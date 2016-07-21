package com.studio47.models;

import com.badlogic.gdx.Gdx;
import com.studio47.context.DisplayContext;

/**
 * Created by Kyle on 7/19/2016.
 */
public abstract class Entity {
    protected float x;
    protected float y;
    protected final int width;
    protected final int height;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(float dt);
    public abstract void draw();
    public abstract void dispose();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isTouched() {
        int x_input = Gdx.input.getX();
        int y_input = DisplayContext.getScreenHeight() - Gdx.input.getY();
        return (x_input >= x && x_input < x + width && y_input >= y && y_input < y + height);
    }
}
