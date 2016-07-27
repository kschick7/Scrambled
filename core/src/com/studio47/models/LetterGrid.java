package com.studio47.models;

import com.studio47.context.Constants;
import com.studio47.context.DisplayContext;
import com.studio47.managers.LetterManager;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterGrid extends Entity {
    private LetterBlock[][] grid;
    private int widthOffset;
    private int heightOffset;
    private boolean adjusting;
    private LetterManager letterManager;

    public LetterGrid() {
        super(0, 0, Constants.GRID_WIDTH, Constants.GRID_HEIGHT);
        this.widthOffset = (DisplayContext.getScreenWidth() - (Constants.GRID_ROW_LENGTH * Constants.BLOCK_WIDTH)) / 2;
        this.heightOffset = (DisplayContext.getScreenHeight() - (Constants.GRID_COLUMN_LENGTH * Constants.BLOCK_HEIGHT)) / 2;
        this.grid = new LetterBlock[Constants.GRID_COLUMN_LENGTH][Constants.GRID_ROW_LENGTH];
        this.x = widthOffset;
        this.y = heightOffset;
        this.adjusting = true;
        this.letterManager = new LetterManager(Constants.LETTER_WEIGHTS_PATH);
        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < Constants.GRID_ROW_LENGTH; i++) {
            addBlocksToColumn(i, Constants.GRID_COLUMN_LENGTH);
        }
    }

    public void addBlocksToColumn(int col, int numBlocks) {
        for (int i = 0; i < numBlocks; i++) {
            int row = getColumnHeight(col);
            float x = widthOffset + col * Constants.BLOCK_WIDTH;
            float y = DisplayContext.getScreenHeight() + (Constants.BLOCK_HEIGHT + 60) * i + 20 * col;
            grid[row][col] = new LetterBlock(x, y, letterManager.next());
            grid[row][col].fallToCoordinate(heightOffset + Constants.BLOCK_HEIGHT * row);
        }
        adjusting = true;
    }

    public int getColumnHeight(int col) {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            if (grid[i][col] == null)
                return i;
        }
        return Constants.GRID_COLUMN_LENGTH;
    }

    public void update(float dt) {
        if (adjusting && !areBlocksFalling()) {
            adjusting = false;
        }

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
                if (grid[i][j] != null && grid[i][j].isTouched()) {
                    return grid[i][j];
                }
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

    public void removeSelectedAndReplace() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null && grid[i][j].isSelected()) {
                    grid[i][j] = null;
                }
            }
        }
        normalizeGrid();
    }

    /**
     * Adjust blocks so that there are no empty spaces in between blocks in each column
     * (No floating blocks, move all to lowest possible position). Add new blocks to columns
     * to fill the grid
     */
    private void normalizeGrid() {
        for (int col = 0; col < Constants.GRID_ROW_LENGTH; col++) {
            int rowsToDescend = 0;
            Integer missingRow = null;
            for (int row = 0; row < Constants.GRID_COLUMN_LENGTH; row++) {
                if (grid[row][col] == null) {
                    rowsToDescend++;
                    if (missingRow == null) {
                        missingRow = row;
                    }
                } else if (rowsToDescend > 0) {
                    grid[row][col].fallNumRows(rowsToDescend);
                    grid[missingRow][col] = grid[row][col];
                    grid[row][col] = null;
                    missingRow++;
                }
            }
            addBlocksToColumn(col, rowsToDescend);
        }
    }

    private boolean areBlocksFalling() {
        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                if (grid[i][j] != null && grid[i][j].isFalling()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAdjusting() {
        return adjusting;
    }

    public int getWidthOffset() {
        return widthOffset;
    }

    public int getHeightOffset() {
        return heightOffset;
    }
}
