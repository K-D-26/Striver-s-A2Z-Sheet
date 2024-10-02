// recursion:

import java.util.* ;
import java.io.*;

public class Solution {
    public static int minSumPath(int[][] grid) {
    	int m = grid.length;
        int n = grid[0].length;
        return recur(m-1, n-1, grid);
    }

    private static int recur(int i, int j, int[][] grid) {
        if (i == 0 && j == 0) return grid[i][j];

        if (i < 0 || j < 0) return (int)(1e9);

        int up = grid[i][j] + recur(i-1, j, grid);
        int left = grid[i][j] + recur(i, j-1, grid);
        return Math.min(up, left);
    }
}


// memoization:

import java.util.* ;
import java.io.*;

public class Solution {
    public static int minSumPath(int[][] grid) {
    	int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return recur(m-1, n-1, grid, dp);
    }

    private static int recur(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return grid[i][j];

        if (i < 0 || j < 0) return (int)(1e9);

        if (dp[i][j] != -1) return dp[i][j];

        int up = grid[i][j] + recur(i-1, j, grid, dp);
        int left = grid[i][j] + recur(i, j-1, grid, dp);
        return dp[i][j] = Math.min(up, left);
    }
}


// tabulation:

import java.util.* ;
import java.io.*;

public class Solution {
    public static int minSumPath(int[][] grid) {
    	int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                }
                else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += dp[i-1][j];
                    }
                    else {
                        up += (int)(1e9);
                    }

                    int left = grid[i][j];
                    if (j > 0) {
                        left += dp[i][j-1];
                    }
                    else {
                        left += (int)(1e9);
                    }

                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        
        return dp[m-1][n-1];
    }
}


// tabulation - space optimised:

import java.util.* ;
import java.io.*;

public class Solution {
    public static int minSumPath(int[][] grid) {
    	int m = grid.length;
        int n = grid[0].length;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = grid[i][j];
                }
                else {
                    int up = grid[i][j];
                    if (i > 0) {
                        up += prev[j];
                    }
                    else {
                        up += (int)(1e9);
                    }

                    int left = grid[i][j];
                    if (j > 0) {
                        left += curr[j-1];
                    }
                    else {
                        left += (int)(1e9);
                    }

                    curr[j] = Math.min(up, left);
                }
            }
            prev = curr;
        }
        
        return prev[n-1];
    }
}