package diff;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyersDiffTest {

    @Test
    public void testGreedyDiff() {
        MyersDiff myerDiff = new MyersDiff("a", "ab");
        List<Pair<Integer, Integer>> expected = new ArrayList<>();
        expected.add(new Pair<>(1, 2));
        expected.add(new Pair<>(1, 1));
        List<Pair<Integer, Integer>> res = myerDiff.diff();
        assertEquals(expected, res);
    }


}
