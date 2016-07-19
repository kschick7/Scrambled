package com.studio47.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterBlock extends Entity {
    private static final float GRAVITY = -20;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private char value;
    private boolean selected;
    private boolean falling;
    private float fallDistance;
    private float fallVelocity;

    public LetterBlock(float x, float y, char value) {
        super(x, y);
        this.value = value;
        this.selected = false;
        this.falling = false;
        this.fallDistance = 0;
        this.fallVelocity = 0;
    }

    public void update(float dt) {
        if (falling) {
            fallVelocity += GRAVITY * dt;
            float distance = fallVelocity * dt;
            if (fallDistance + distance > HEIGHT) {
                stopFalling();
                y += (HEIGHT - fallDistance);
            } else {
                y += distance;
            }
        }
    }

    public void draw(SpriteBatch batch) {

    }

    public void dispose() {}

    public char getValue() {
        return value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void startFalling() {
        this.falling = true;
    }

    private void stopFalling() {
        this.falling = false;
        fallDistance = 0;
        fallVelocity = 0;
    }

}
