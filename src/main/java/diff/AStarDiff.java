package diff;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarDiff {

    // insert or delete
    private final static int INORDEL = 1;
    // same character
    private final static int MATCH = 0;
    // change a -> b
    private final static int UNMATCH = 1;

    private int[] goal;

    private String s1;
    private String s2;

    public AStarDiff(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        this.goal = new int[]{m, n};
    }

    class State {
        int[] pos;
        int cost;
        int heuristic;

        public State(int[] pos) {
            this.pos = pos;
            this.cost = 0;
            this.heuristic = getHeuristic();
        }

        public State(int[] pos, int cost) {
            this.pos = pos;
            this.cost = cost;
            this.heuristic = cost + getHeuristic();
        }

        // Use minimum cost estimate as heuristic
        public int getHeuristic() {
            int goal_diag = getDiag(goal);
            int cur_diag = getDiag(pos);
            return  Math.abs(goal_diag - cur_diag);
        }

        private int getDiag(int[] pos) {
            return pos[0] - pos[1];
        }
    }

    public int diff() {
        int m = s1.length();
        int n = s2.length();

        if(m*n == 0) {
            return m == 0 ? n : m;
        }

        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.heuristic));

        int[] start = new int[]{0, 0};
        queue.offer(new State(start));

        while(!queue.isEmpty()) {
            State cur_state = queue.poll();
            int[] cur_pos = cur_state.pos;
            if(Arrays.equals(cur_pos, goal)) {
                return cur_state.cost;
            }
            if(cur_pos[0] < m) {
                queue.offer(new State(new int[]{cur_pos[0] + 1, cur_pos[1]}, cur_state.cost + INORDEL));
            }
            if(cur_pos[1] < n) {
                queue.offer(new State(new int[]{cur_pos[0], cur_pos[1] + 1}, cur_state.cost + INORDEL));
            }
            if(cur_pos[0] < m && cur_pos[1] < n) {
                if(s1.charAt(cur_pos[0]) == s2.charAt(cur_pos[1])) {
                    queue.offer(new State(new int[]{cur_pos[0] + 1, cur_pos[1] + 1}, cur_state.cost + MATCH));
                } else {
                    queue.offer(new State(new int[]{cur_pos[0] + 1, cur_pos[1] + 1}, cur_state.cost + UNMATCH));
                }
            }
        }

        return -1;
    }
}
