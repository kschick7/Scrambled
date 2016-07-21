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
    private String word;
    private boolean selecting;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public void init() {
        System.out.println("Game State = PLAY");
        letterGrid = new LetterGrid();
        word = "";
        selecting = false;

        for (int i = 0; i < LetterGrid.GRID_HEIGHT; i++) {
            for (int j = 0; j < LetterGrid.GRID_WIDTH; j++) {
                letterGrid.addBlockToColumn(j, 'A');
            }
        }
    }

    public void update(float dt) {
        if (!selecting && Gdx.input.isTouched()) {
            selecting = true;
        } else if (selecting && !Gdx.input.isTouched()) {
            selecting = false;
            checkWord();
            letterGrid.deselectAll();
        }

        if (selecting) {
            LetterBlock letterBlock = letterGrid.getTouchedBlock(Gdx.input.getX(), Gdx.input.getY());
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
