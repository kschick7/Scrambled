package com.studio47.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.studio47.context.Dictionary;
import com.studio47.context.DisplayContext;
import com.studio47.managers.GameStateManager;
import com.studio47.models.LetterBlock;
import com.studio47.models.LetterGrid;
import com.studio47.models.Score;

/**
 * Created by Kyle on 7/18/2016.
 */
public class PlayState extends GameState {
    private LetterGrid letterGrid;
    private LetterBlock lastSelected;
    private String word;
    private boolean selecting;
    private BitmapFont bitmapFont;
    private Score score;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.selecting = false;
        System.out.println("Game State = PLAY");
        this.letterGrid = new LetterGrid();
        this.word = "";
        this.lastSelected = null;
        this.bitmapFont = new BitmapFont();
        this.score = new Score(0, DisplayContext.getScreenHeight() - (letterGrid.getHeightOffset() / 2));
    }

    public void update(float dt) {
        if (!selecting && !letterGrid.isAdjusting() && Gdx.input.isTouched()) {
            selecting = true;
        } else if (selecting) {
            if (!Gdx.input.isTouched() || !letterGrid.isTouched()) {
                selecting = false;
                lastSelected = null;
                if (checkWord()) {
                    letterGrid.removeSelectedAndReplace();
                    score.updateScore(word);
                } else {
                    letterGrid.deselectAll();
                }
                word = "";
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
        score.update(dt);
    }

    public void draw() {
        letterGrid.draw();
        drawWord();
        score.draw();
    }

    public void dispose() {

    }

    public boolean checkWord() {
        System.out.println(word);
        return (word.length() > 1) ? Dictionary.contains(word) : false;
    }

    public void drawWord() {
        float y = letterGrid.getHeightOffset() / 2 + 5;
        float x = DisplayContext.getScreenWidth() / 2 - word.length() * 5;
        bitmapFont.draw(DisplayContext.getSpriteBatch(), word, x, y);
    }
}
