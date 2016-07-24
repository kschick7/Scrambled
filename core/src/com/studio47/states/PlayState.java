package com.studio47.states;

import com.badlogic.gdx.Gdx;
import com.studio47.managers.GameStateManager;
import com.studio47.models.LetterBlock;
import com.studio47.models.LetterGrid;

/**
 * Created by Kyle on 7/18/2016.
 */
public class PlayState extends GameState {
    private LetterGrid letterGrid;
    private LetterBlock lastSelected;
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
        lastSelected = null;
    }

    public void update(float dt) {
        if (!selecting && !letterGrid.isAdjusting() && Gdx.input.isTouched()) {
            selecting = true;
        } else if (selecting) {
            if (!Gdx.input.isTouched() || !letterGrid.isTouched()) {
                selecting = false;
                lastSelected = null;
                checkWord();
                letterGrid.removeSelectedAndReplace();
            } else {
                LetterBlock letterBlock = letterGrid.getTouchedBlock();
                if (letterBlock != null && !letterBlock.isSelected()
                        && (lastSelected == null || lastSelected.isAdjacentTo(letterBlock))) {
                    letterBlock.setSelected(true);
                    lastSelected = letterBlock;
                    word += letterBlock.getValue();
                }
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
