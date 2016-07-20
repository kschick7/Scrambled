package com.studio47.states;

import com.badlogic.gdx.Gdx;
import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;
import com.studio47.models.LetterBlock;
import com.studio47.models.LetterGrid;

/**
 * Created by Kyle on 7/18/2016.
 */
public class PlayState extends GameState {
    private LetterBlock letterBlock;
    private LetterGrid letterGrid;
    private String word;
    private boolean selecting;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    public void init() {
        System.out.println("Game State = PLAY");
        letterGrid = new LetterGrid();
        letterBlock = new LetterBlock(120, 120, 'A');
        word = "";
        selecting = false;

//        for (int i = 0; i < LetterGrid.GRID_HEIGHT; i++) {
//            for (int j = 0; j < LetterGrid.GRID_WIDTH; j++) {
//                letterGrid.addBlockToColumn(j, 'A');
//            }
//        }
    }

    public void update(float dt) {
        if (!selecting && Gdx.input.isTouched()) {
            selecting = true;
        } else if (selecting && !Gdx.input.isTouched()) {
            selecting = false;
            checkWord();
        }

        if (Gdx.input.isTouched()) {
            letterBlock.fallNumRows(1);
        }
        letterBlock.update(dt);
    }

    public void draw() {
        letterBlock.draw();
    }

    public void dispose() {

    }

    public void checkWord() {}
}
