// recursion:

import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        return recur(n-1, maxWeight, weight, value);
    }

    private static int recur(int i, int W, int[] wt, int[] val) {
        if (i == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        int notTake = 0 + recur(i-1, W, wt, val);
        int take = Integer.MIN_VALUE;
        if (wt[i] <= W) take = val[i] + recur(i-1, W - wt[i], wt, val);

        return Math.max(take, notTake);
    }
}


// memoization:

import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight+1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return recur(n-1, maxWeight, weight, value, dp);
    }

    private static int recur(int i, int W, int[] wt, int[] val, int[][] dp) {
        if (i == 0) {
            if (wt[0] <= W) return val[0];
            return 0;
        }

        if (dp[i][W] != -1) return dp[i][W];

        int notTake = 0 + recur(i-1, W, wt, val, dp);
        int take = Integer.MIN_VALUE;
        if (wt[i] <= W) take = val[i] + recur(i-1, W - wt[i], wt, val, dp);

        return dp[i][W] = Math.max(take, notTake);
    }
}


// tabulation:

import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] wt, int[] val, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight+1];
        
        for (int i = 0; i < n; i++) {
            for (int W = 0; W <= maxWeight; W++) {
                if (i == 0) {
                    if (wt[0] <= W) dp[i][W] = val[0];
                }
                else {
                    int notTake = 0 + dp[i-1][W];
                    int take = Integer.MIN_VALUE;
                    if (wt[i] <= W) take = val[i] + dp[i-1][W - wt[i]];

                    dp[i][W] = Math.max(take, notTake);
                }
            }
        }
        
        return dp[n-1][maxWeight];
    }
}


// tabulation - space optimised:

import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] wt, int[] val, int n, int maxWeight) {
        int[] prev = new int[maxWeight+1];
        
        for (int i = 0; i < n; i++) {
            int[] curr = new int[maxWeight+1];
            for (int W = 0; W <= maxWeight; W++) {
                if (i == 0) {
                    if (wt[0] <= W) curr[W] = val[0];
                }
                else {
                    int notTake = 0 + prev[W];
                    int take = Integer.MIN_VALUE;
                    if (wt[i] <= W) take = val[i] + prev[W - wt[i]];

                    curr[W] = Math.max(take, notTake);
                }
            }
            prev = curr;
        }
        
        return prev[maxWeight];
    }
}


// tabulation - even more space optimised

import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] wt, int[] val, int n, int maxWeight) {
        int[] prev = new int[maxWeight+1];
        
        for (int i = 0; i < n; i++) {
            for (int W = maxWeight; W >= 0; W--) {
                if (i == 0) {
                    if (wt[0] <= W) prev[W] = val[0];
                }
                else {
                    int notTake = 0 + prev[W];
                    int take = Integer.MIN_VALUE;
                    if (wt[i] <= W) take = val[i] + prev[W - wt[i]];

                    prev[W] = Math.max(take, notTake);
                }
            }
        }
        
        return prev[maxWeight];
    }
}