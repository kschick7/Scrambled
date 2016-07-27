package com.studio47.managers;

import com.badlogic.gdx.files.FileHandle;

import java.util.*;

/**
 * Created by Kyle on 7/24/2016.
 */
public class LetterManager {
    private Map<Character, Integer> weights;
    private List<Character> weightedList;
    private Random random;

    public LetterManager(String path) {
        this.weights = new HashMap<Character, Integer>();
        this.weightedList = new ArrayList<Character>();

        FileHandle file = new FileHandle(path);
        String[] inputs = file.readString().split("\n");
        for (String input : inputs) {
            Character c = input.split(" ")[0].charAt(0);
            int weight = Integer.valueOf(input.split(" ")[1]);
            this.weights.put(c, weight);
            for (int i = 0; i < weight; i++) {
                this.weightedList.add(c);
            }
        }

        this.random = new Random();
    }

    public Character next() {
        return weightedList.get(random.nextInt(weightedList.size()));
    }

    public int getWeight(Character c) {
        return weights.get(c);
    }
}
