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
    private DisplayContext displayContext;
    private int widthOffset;
    private int heightOffset;

    public LetterGrid(DisplayContext displayContext) {
        this.displayContext = displayContext;
        this.widthOffset = (displayContext.getWidth() - (5 * LetterBlock.getWidth())) / 2;
        this.heightOffset = (displayContext.getHeight() - (5 * LetterBlock.getHeight())) / 2;
        this.grid = new ArrayList<ArrayList<LetterBlock>>();
    }

    public void addBlockToColumn(int col, char value) {
        float x = widthOffset + col * LetterBlock.getWidth();
        float y = displayContext.getHeight() + LetterBlock.getHeight() * (1 + getColumnHeight(col));
    }

    public int getColumnHeight(int col) {
        return 0;
    }
}
