package io.neilharvey.bogglebuddy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

@RunWith(AndroidJUnit4.class)
public class PerformanceTests {

    private static void assertItemsEqual(String[] expected, String[] results) {
        Arrays.sort(expected);
        Arrays.sort(results);
        assertArrayEquals(expected, results);
    }

    @Test(timeout = 5000)
    public void findsWordsWithinTargetTime() throws IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        TrieDictionary dictionary = new TrieDictionary();
        dictionary.loadWords(appContext);
        WordFinder wordFinder = new WordFinder(dictionary, 3);
        char[][] board = new char[][]{
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };
        String[] words = wordFinder.findWords(board);
        String[] expected = {"knop", "fin", "ink", "jink", "pol", "fink", "jin", "nim",
                "glop", "kop", "plonk", "knife", "fino", "lop", "fie", "mink"};
        assertItemsEqual(expected, words);
    }
}