// recursion:

import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        return recur(0, 0, triangle, n);
    }

    private static int recur(int i, int j, int[][] triangle, int n) {
        if (i == n-1) return triangle[n-1][j];

        int d = triangle[i][j] + recur(i+1, j, triangle, n);        // down
        int dg = triangle[i][j] + recur(i+1, j+1, triangle, n);     // diagonal
        return Math.min(d, dg);
    }
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return recur(0, 0, triangle, n, dp);
    }

    private static int recur(int i, int j, int[][] triangle, int n, int[][] dp) {
        if (i == n-1) return triangle[n-1][j];

        if (dp[i][j] != -1) return dp[i][j];

        int d = triangle[i][j] + recur(i+1, j, triangle, n, dp);        // down
        int dg = triangle[i][j] + recur(i+1, j+1, triangle, n, dp);     // diagonal
        return dp[i][j] = Math.min(d, dg);
    }
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[n-1][j] = triangle[n-1][j];
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int d = triangle[i][j] + dp[i+1][j];        // down
                int dg = triangle[i][j] + dp[i+1][j+1];     // diagonal
                dp[i][j] = Math.min(d, dg);
            }
        }

        return dp[0][0];
    }
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        int[] front = new int[n];
        for (int j = 0; j < n; j++) {
            front[j] = triangle[n-1][j];
        }

        for (int i = n-2; i >= 0; i--) {
            int[] curr = new int[n];
            for (int j = i; j >= 0; j--) {
                int d = triangle[i][j] + front[j];        // down
                int dg = triangle[i][j] + front[j+1];     // diagonal
                curr[j] = Math.min(d, dg);
            }
            front = curr;
        }

        return front[0];
    }
}