package com.studio47.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.studio47.context.DisplayContext;
import com.studio47.managers.LetterManager;

/**
 * Created by Kyle on 7/26/2016.
 */
public class Score extends Entity {
    private int value;
    private BitmapFont bitmapFont;

    public Score(float x, float y) {
        super(x, y, 100, 10);
        this.value = 0;
        this.bitmapFont = new BitmapFont();
    }

    public void update(float dt) {
        x = DisplayContext.getScreenWidth() - 100 - 5 * Integer.toString(value).length();
    }

    public void draw() {
        bitmapFont.draw(DisplayContext.getSpriteBatch(), Integer.toString(value), x, y);
    }

    public void dispose() {

    }

    /**
     * Calculate the gained score from 'word', add it to the total score, and return it.
     */
    public int updateScore(String word) {
        int wordScore = calculateScoreForWord(word);
        value += wordScore;
        return wordScore;
    }

    private int calculateScoreForWord(String word) {
        double multiplier;
        switch (word.length()) {
            case 1:
                System.out.println("ERROR: One-letter words should not be accepted");
                throw new IllegalArgumentException();
            case 2:
                multiplier = .5;
                break;
            case 3:
                multiplier = 1;
                break;
            case 4:
                multiplier = 1.25;
                break;
            default:
                multiplier = .5 * word.length() - 1;
        }
        float score = 0;
        for (int i = 0; i < word.length(); i++) {
            int weight = LetterManager.getWeight(word.charAt(i));
            score += 100.0 / weight * multiplier;
        }
        return Math.round(score);
    }
}
