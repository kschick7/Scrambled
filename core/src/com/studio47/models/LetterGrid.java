package com.studio47.models;

import com.studio47.context.Constants;
import com.studio47.context.DisplayContext;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterGrid extends Entity {
    private LetterBlock[][] grid;
    private int widthOffset;
    private int heightOffset;

    public LetterGrid() {
        super(0, 0, Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
        this.widthOffset = (DisplayContext.getScreenWidth() - (Constants.GRID_ROW_LENGTH * Constants.BLOCK_WIDTH)) / 2;
        this.heightOffset = (DisplayContext.getScreenHeight() - (Constants.GRID_COLUMN_LENGTH * Constants.BLOCK_HEIGHT)) / 2;
        this.grid = new LetterBlock[Constants.GRID_COLUMN_LENGTH][Constants.GRID_ROW_LENGTH];
        this.x = widthOffset;
        this.y = heightOffset;
    }

    public void addBlockToColumn(int col, char value) {
        int row = getColumnHeight(col);
        float x = widthOffset + col * Constants.BLOCK_WIDTH;
        float y = DisplayContext.getScreenHeight() + (Constants.BLOCK_HEIGHT + 60) * (1 + row)+ 20 * col;
        grid[row][col] = new LetterBlock(x, y, value);
        grid[row][col].fallToCoordinate(heightOffset + Constants.BLOCK_HEIGHT * row);
    }

    public int getColumnHeight(int col) {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            if (grid[i][col] == null)
                return i;
        }
        return Constants.GRID_COLUMN_LENGTH;
    }

    public void update(float dt) {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].update(dt);
            }
        }
    }

    public void draw() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].draw();
            }
        }
    }

    public void dispose() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].dispose();
            }
        }
    }

    /**
     * Returns reference to LetterBlock at given coordinate, or null if the block is not
     * selectable or the coordinate does not point to a block.
     */
    public LetterBlock getTouchedBlock() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null && grid[i][j].isTouched())
                    return grid[i][j];
            }
        }
        return null;
    }

    public void deselectAll() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null)
                    grid[i][j].setSelected(false);
            }
        }
    }
}
