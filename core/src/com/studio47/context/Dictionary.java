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

    public static void init(String filename) {
        FileHandle file = new FileHandle(filename);
        String fileStr = file.readString();
        words = new HashSet<String>(Arrays.asList(fileStr.split("\n")));
    }

    public static boolean contains(String word) {
        return words.contains(word);
    }
}
