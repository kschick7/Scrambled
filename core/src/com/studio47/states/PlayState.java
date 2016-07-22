package com.studio47.states;

import com.badlogic.gdx.Gdx;
import com.studio47.context.Constants;
import com.studio47.managers.GameStateManager;
import com.studio47.models.LetterBlock;
import com.studio47.models.LetterGrid;

/**
 * Created by Kyle on 7/18/2016.
 */
public class PlayState extends GameState {
    private LetterGrid letterGrid;
    private String word;
    private boolean selecting;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public void init() {
        selecting = false;
        System.out.println("Game State = PLAY");
        letterGrid = new LetterGrid();
        word = "";

        for (int i = 0; i < Constants.GRID_COLUMN_LENGTH; i++) {
            for (int j = 0; j < Constants.GRID_ROW_LENGTH; j++) {
                letterGrid.addBlockToColumn(j, 'A');
            }
        }
    }

    public void update(float dt) {
        if (!selecting && !letterGrid.isAdjusting() && Gdx.input.isTouched()) {
            selecting = true;
        } else if (selecting && !Gdx.input.isTouched()) {
            selecting = false;
            checkWord();
            letterGrid.removeSelectedAndReplace();
        }

        if (selecting) {
            LetterBlock letterBlock = letterGrid.getTouchedBlock();
            if (letterBlock != null) {
                letterBlock.setSelected(true);
                word += letterBlock.getValue();
            }
        }
        letterGrid.update(dt);
    }

    public void draw() {
        letterGrid.draw();
    }

    public void dispose() {

    }

    public void checkWord() {}
}
