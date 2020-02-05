package diff;

public class DPDiff {

    public static int diff(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if(m*n == 0) {
            return m == 0? n : m;
        }

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i ++) {
            dp[i][0] = i;
        }

        for(int i = 0; i <= n; i ++) {
            dp[0][i] = i;
        }

        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j ++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
