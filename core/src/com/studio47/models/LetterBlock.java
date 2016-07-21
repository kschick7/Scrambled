package com.studio47.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio47.context.Constants;
import com.studio47.context.DisplayContext;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterBlock extends Entity {
    private char value;
    private boolean selected;
    private float fallDistance;
    private float fallVelocity;
    private Texture blockTexture;
    private Texture coverTexture;

    public LetterBlock(float x, float y, char value) {
        super(x, y, Constants.BLOCK_WIDTH, Constants.BLOCK_HEIGHT);
        this.value = value;
        this.selected = false;
        this.fallDistance = 0;
        this.fallVelocity = 0;
        this.blockTexture = new Texture("A_block.png");
        this.coverTexture = new Texture("block_selected_cover.png");
    }

    public void update(float dt) {
        if (fallDistance > 0) {
            fallVelocity += Constants.GRAVITY * dt;
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

    public void draw() {
        SpriteBatch spriteBatch = DisplayContext.getSpriteBatch();
        spriteBatch.draw(blockTexture, x, y);
        if (selected)
            spriteBatch.draw(coverTexture, x, y);

//        bitmapFont.draw(DisplayContext.getSpriteBatch(), "A", x + 20, y + 20);
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
            fallDistance = getHeight() * rows;
    }

    public void fallToCoordinate(float y_dest) {
        if (fallDistance == 0)
            fallDistance = y - y_dest;
    }

    private void stopFalling() {
        fallDistance = 0;
        fallVelocity = 0;
    }

    public boolean isSelectable() {
        return fallDistance == 0 && !selected;
    }
}
