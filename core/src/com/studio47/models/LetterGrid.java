package com.studio47.models;

import com.studio47.context.DisplayContext;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterGrid extends Entity {
    public static final int GRID_WIDTH = 5;
    public static final int GRID_HEIGHT = 7;

    private LetterBlock[][] grid;
    private int widthOffset;
    private int heightOffset;

    public LetterGrid() {
        super(0, 0);
        this.widthOffset = (DisplayContext.get().getWidth() - (GRID_WIDTH * LetterBlock.getWidth())) / 2;
        this.heightOffset = (DisplayContext.get().getHeight() - (GRID_HEIGHT * LetterBlock.getHeight())) / 2;
        this.grid = new LetterBlock[GRID_HEIGHT][GRID_WIDTH];
    }

    public void addBlockToColumn(int col, char value) {
        int row = getColumnHeight(col);
        float x = widthOffset + col * LetterBlock.getWidth();
        float y = DisplayContext.get().getHeight() + (LetterBlock.getHeight() + 60) * (1 + row)+ 20 * col;
        grid[row][col] = new LetterBlock(x, y, value);
        grid[row][col].fallToCoordinate(heightOffset + LetterBlock.getHeight() * row);
    }

    public int getColumnHeight(int col) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            if (grid[i][col] == null)
                return i;
        }
        return GRID_HEIGHT;
    }

    public void update(float dt) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].update(dt);
            }
        }
    }

    public void draw() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].draw();
            }
        }
    }

    public void dispose() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].dispose();
            }
        }
    }
}
