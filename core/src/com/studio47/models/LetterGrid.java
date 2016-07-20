package com.studio47.models;

import com.studio47.context.DisplayContext;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kyle on 7/19/2016.
 */
public class LetterGrid {
    public static final int GRID_WIDTH = 5;
    public static final int GRID_HEIGHT = 7;

    private ArrayList<ArrayList<LetterBlock>> grid;
    private int widthOffset;
    private int heightOffset;

    public LetterGrid() {
        this.widthOffset = (DisplayContext.get().getWidth() - (5 * LetterBlock.getWidth())) / 2;
        this.heightOffset = (DisplayContext.get().getHeight() - (5 * LetterBlock.getHeight())) / 2;
        this.grid = new ArrayList<ArrayList<LetterBlock>>();
    }

    public void addBlockToColumn(int col, char value) {
        float x = widthOffset + col * LetterBlock.getWidth();
        float y = DisplayContext.get().getHeight() + LetterBlock.getHeight() * (1 + getColumnHeight(col));
    }

    public int getColumnHeight(int col) {
        return 0;
    }
}
