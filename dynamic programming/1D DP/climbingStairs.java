// This question is similar to fibonacci

// tabulation (from 0 to n):

class Solution {
    public int climbStairs(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            if (i < 2) {
                arr[i] = i + 1;
            }
            else {
                arr[i] = arr[i-1] + arr[i-2];
            }
        }

        return arr[n-1];
    }
}


// tabulation (from n to 0):

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        
        for (int i = n-1; i >= 0; i--) {
            if (i + 2 <= n) {
                dp[i] = dp[i+1] + dp[i+2];
            }
            else {
                dp[i] = dp[i+1];
            }
        }
        
        return dp[0];
    }
}