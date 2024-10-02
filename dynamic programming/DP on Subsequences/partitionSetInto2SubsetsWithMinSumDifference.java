// tabulation:

public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        int totSum = 0;
        for (int i : arr) totSum += i;
        int k = totSum;

        // tabulation method of subset sum equal to k
        boolean[][] dp = new boolean[n][k+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[i-1][target];
                boolean take = false;
                if (arr[i] <= target) take = dp[i-1][target-arr[i]];

                dp[i][target] = take || notTake;
            }
        }

        for (int s1 = totSum/2; s1 >= 0; s1--) {
            if (dp[n-1][s1] == true) {
                return Math.abs((totSum - s1) - s1);
            }
        }

        return -1;
    }
}


// tabulation - space optimised:

public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        int totSum = 0;
        for (int i : arr) totSum += i;
        int k = totSum;

        // tabulation - space optimised method of subset sum equal to k
        boolean prev[] = new boolean[k+1];
        prev[0] = true;
        
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            boolean curr[] = new boolean[k+1];
            curr[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                if (arr[i] <= target) {
                    take = prev[target - arr[i]];
                }

                curr[target] = take || notTake;
            }
            prev = curr;
        }

        for (int s1 = totSum/2; s1 >= 0; s1--) {
            if (prev[s1] == true) {
                return Math.abs((totSum - s1) - s1);
            }
        }

        return -1;
    }
}