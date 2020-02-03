package diff;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyersDiff {

    private String s1;
    private String s2;

    private List<Map<Integer, Integer>> snapshots = new ArrayList<>();

    public MyersDiff(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public List<Pair<Integer, Integer>> diff() {
        int N = s1.length();
        int M = s2.length();
        int MAX = N + M;
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        Map<Integer, Integer> V = new HashMap(2*MAX);

        V.put(1, 0);

        for (int d = 0; d <= MAX; d ++) {
            for (int k = -d; k <= d; k += 2) {
                // down or right?
                 boolean down = (k == -d || (k != d && V.get(k - 1) < V.get(k + 1)));

                int kPrev = down? k + 1 : k - 1;

                // start point
                int xStart = V.get(kPrev);

                // end point
                int xEnd = down? xStart : xStart + 1;
                int yEnd = xEnd - k;

                // follow diagonal
                while (xEnd < N && yEnd < M && s1.charAt(xEnd) == s2.charAt(yEnd)) {
                    xEnd++;
                    yEnd++;
                }

                // save end point
                V.put(k, xEnd);

                // check for solution
                if (xEnd >= N && yEnd >= M) {
                    snapshots.add(V);
                    recursivelyFindOptimalPath(k, d, res);
                    return res;
                }
            }
            snapshots.add(new HashMap<>(V));
        }
        return res;
    }

    private void recursivelyFindOptimalPath(int k, int d, List<Pair<Integer, Integer>> res) {
        Map<Integer, Integer> V = snapshots.get(d);
        int xEnd = V.get(k);
        int yEnd = xEnd - k;
        res.add(new Pair<>(xEnd, yEnd));
        Map<Integer, Integer> prevV = snapshots.get(d);
        int prevK;
        if(prevV.containsKey(k + 1) && prevV.containsKey(k - 1)) {
            prevK = prevV.get(k + 1) > prevV.get(k - 1) ? k + 1 : k - 1;
        } else if(prevV.containsKey(k + 1)) {
            prevK = k + 1;
        } else {
            prevK = k - 1;
        }
        if(d > 0) {
            recursivelyFindOptimalPath(prevK, d - 1, res);
        }
    }
}