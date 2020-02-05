package diff;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DiffTest {

    String s1 = "GCATGCU";
    String s2 = "GATTACA";

    @Test
    public void testMyersDiff() {
        MyersDiff myerDiff = new MyersDiff(s1, s2);
        List<Pair<Integer, Integer>> expected = new ArrayList<>();
        expected.add(new Pair<>(7, 7));
        expected.add(new Pair<>(7, 6));
        expected.add(new Pair<>(6, 6));
        expected.add(new Pair<>(5, 4));
        expected.add(new Pair<>(5, 3));
        expected.add(new Pair<>(4, 3));
        expected.add(new Pair<>(1, 1));
        List<Pair<Integer, Integer>> res = myerDiff.diff();
        assertEquals(expected, res);
    }

    @Test
    public void testDPDiff() {
        assertEquals(DPDiff.diff(s1, s2), 4);
    }

    @Test
    public void testAStarDiff() {
        AStarDiff aStarDiff = new AStarDiff(s1, s2);
        assertEquals(aStarDiff.diff(), 4);
    }

}
