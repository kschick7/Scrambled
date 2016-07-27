package com.studio47.managers;

import com.badlogic.gdx.files.FileHandle;
import com.studio47.context.Constants;

import java.util.*;

/**
 * Created by Kyle on 7/24/2016.
 */
public class LetterManager {
    private static Map<Character, Integer> weights;
    private static List<Character> weightedList;
    private static Random random;

    private LetterManager() {}

    public static void init() {
        weights = new HashMap<Character, Integer>();
        weightedList = new ArrayList<Character>();

        FileHandle file = new FileHandle(Constants.LETTER_WEIGHTS_PATH);
        String[] inputs = file.readString().split("\n");
        for (String input : inputs) {
            Character c = input.split(" ")[0].charAt(0);
            int weight = Integer.valueOf(input.split(" ")[1]);
            weights.put(c, weight);
            for (int i = 0; i < weight; i++) {
                weightedList.add(c);
            }
        }

        random = new Random();
    }

    public static Character next() {
        if (weightedList == null)
            init();
        return weightedList.get(random.nextInt(weightedList.size()));
    }

    public static int getWeight(Character c) {
        if (weights == null)
            init();
        return weights.get(c);
    }
}
