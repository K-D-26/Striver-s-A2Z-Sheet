// recursion:

import java.util.*;
import java.io.*;

public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        return recur(n-1, k, arr);
    }

    private static boolean recur(int i, int k, int[] arr) {
        if (k == 0) return true;
        if (i == 0) return (arr[0] == k);

        boolean notTake = recur(i-1, k, arr);
        boolean take = false;
        if (arr[i] <= k) take = recur(i-1, k-arr[i], arr);

        return take || notTake;
    }
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp = new int[n][k+1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return recur(n-1, k, arr, dp);
    }

    private static boolean recur(int i, int k, int[] arr, int[][] dp) {
        if (k == 0) return true;
        if (i == 0) return (arr[0] == k);

        if (dp[i][k] != -1) return (dp[i][k] != 0);

        boolean notTake = recur(i-1, k, arr, dp);
        boolean take = false;
        if (arr[i] <= k) take = recur(i-1, k-arr[i], arr, dp);

        dp[i][k] = (take || notTake) ? 1 : 0;
        return (dp[i][k] == 1);
    }
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
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

        return dp[n-1][k];
    }
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
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

        return prev[k];
    }
}