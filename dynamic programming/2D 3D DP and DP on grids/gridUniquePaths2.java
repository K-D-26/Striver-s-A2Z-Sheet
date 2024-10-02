// recursion:

import java.util.*;

public class Solution {
    static int mod = (int)Math.pow(10, 9) + 7;
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        return recur(n-1, m-1, mat);
    }

    private static int recur(int i, int j, ArrayList<ArrayList<Integer>> mat) {
        if (i >= 0 && j >= 0 && mat.get(i).get(j) == -1) return 0;

        if (i == 0 && j == 0) return 1;

        if (i < 0 || j < 0) return 0;

        int up = recur(i-1, j, mat);
        int left = recur(i, j-1, mat);
        return (up + left) % mod;
    }
}


// memoization:

import java.util.*;

public class Solution {
    static int mod = (int)Math.pow(10, 9) + 7;
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp = new int[n][m];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return recur(n-1, m-1, mat, dp);
    }

    private static int recur(int i, int j, ArrayList<ArrayList<Integer>> mat, int[][] dp) {
        if (i >= 0 && j >= 0 && mat.get(i).get(j) == -1) return 0;

        if (i == 0 && j == 0) return 1;

        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int up = recur(i-1, j, mat, dp);
        int left = recur(i, j-1, mat, dp);
        return dp[i][j] = (up + left) % mod;
    }
}


// tabulation:

import java.util.*;

public class Solution {
    static int mod = (int)Math.pow(10, 9) + 7;
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat.get(i).get(j) == -1) {
                    dp[i][j] = 0;
                }
                else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                else {
					int up = 0, left = 0;
					if (i > 0) {
						up = dp[i-1][j];
					}
					if (j > 0) {
						left = dp[i][j-1];
					}
					dp[i][j] = (up + left) % mod;
				}
            }
        }

        return dp[n-1][m-1];
    }
}


// tabulation - space optimised:

import java.util.*;

public class Solution {
    static int mod = (int)Math.pow(10, 9) + 7;
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[] prev = new int[m];
        
        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];
            for (int j = 0; j < m; j++) {
                if (mat.get(i).get(j) == -1) {
                    curr[j] = 0;
                }
                else if (i == 0 && j == 0) {
                    curr[j] = 1;
                }
                else {
					int up = 0, left = 0;
					if (i > 0) {
						up = prev[j];
					}
					if (j > 0) {
						left = curr[j-1];
					}
					curr[j] = (up + left) % mod;
				}
            }
            prev = curr;
        }

        return prev[m-1];
    }
}