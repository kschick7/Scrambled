package com.studio47.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.studio47.context.DisplayContext;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterBlock extends Entity {
    private static final float GRAVITY = 125;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    private char value;
    private boolean selected;
    private float fallDistance;
    private float fallVelocity;

    public LetterBlock(float x, float y, char value) {
        super(x, y);
        this.value = value;
        this.selected = false;
        this.fallDistance = 0;
        this.fallVelocity = 0;
    }

    public void update(float dt) {
        if (fallDistance > 0) {
            fallVelocity += GRAVITY * dt;
            float distance = fallVelocity * dt;
            if (fallDistance - distance <= 0) {
                y -= fallDistance;
                stopFalling();
            } else {
                fallDistance -= distance;
                y -= distance;
            }
        }
    }

    public void draw(DisplayContext displayContext) {
        ShapeRenderer shapeRenderer = displayContext.getShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(x, y, WIDTH, HEIGHT);
        shapeRenderer.end();
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

    public void fallNumRows(int rows) {
        if (fallDistance == 0)
            fallDistance = HEIGHT * rows;
    }

    public void fallToCoordinate(float y_dest) {
        if (fallDistance == 0)
            fallDistance = y - y_dest;
    }

    private void stopFalling() {
        fallDistance = 0;
        fallVelocity = 0;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

}