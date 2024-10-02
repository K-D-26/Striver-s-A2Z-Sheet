// recursion:

public class Solution {
    public static int minimizeCost(int n, int k, int[] heights){
        return recur(n-1, k, heights);
    }

    private static int recur(int ind, int k, int[] heights) {
        if (ind == 0) return 0;
        
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (ind-j >= 0) {
                int jump = recur(ind-j, k, heights) + Math.abs(heights[ind] - heights[ind-j]);
                minSteps = Math.min(minSteps, jump);
            }        
        }

        return minSteps;
    }
}


// memoization:

import java.util.*;

public class Solution {
    public static int minimizeCost(int n, int k, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return recur(n-1, k, heights, dp);
    }

    private static int recur(int ind, int k, int[] heights, int[] dp) {
        if (ind == 0) return 0;

        if (dp[ind] != -1) return dp[ind];
        
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (ind-j >= 0) {
                int jump = recur(ind-j, k, heights, dp) + Math.abs(heights[ind] - heights[ind-j]);
                minSteps = Math.min(minSteps, jump);
            }        
        }

        return dp[ind] = minSteps;
    }
}


// tabulation:

public class Solution {
    public static int minimizeCost(int n, int k, int[] heights){
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }

            dp[i] = minSteps;
        }

        return dp[n - 1];
    }
}