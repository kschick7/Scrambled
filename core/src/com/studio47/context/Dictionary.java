package com.studio47.context;

import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kyle on 7/24/2016.
 */
public class Dictionary {
    private static Set<String> words;

    private Dictionary() {}

    public static void init() {
        FileHandle file = new FileHandle(Constants.DICTIONARY_PATH);
        String fileStr = file.readString();
        words = new HashSet<String>(Arrays.asList(fileStr.split("\n")));
    }

    public static boolean contains(String word) {
        if (words == null)
            init();
        return words.contains(word.toLowerCase());
    }
}
