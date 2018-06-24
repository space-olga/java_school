import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PopularWordTest {
    @Test
    public void getPopularWord() {
        String[] inputArray = new String[] {"a", "aa", "aaa", "aaaa", "a", "aa", "aaa", "bbbb"};
        assertEquals("a\naa\naaa", PopularWord.getPopularWord(inputArray));
    }
}