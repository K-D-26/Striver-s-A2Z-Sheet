// recursion:

import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int maxi = (int)(-1e8);

		for (int j = 0; j < m; j++) {
			maxi = Math.max(maxi, recur(n-1, j, matrix));
		}
		
        return maxi;
	}

	private static int recur(int i, int j, int[][] matrix) {
		if (j < 0 || j >= matrix[0].length) return (int)(-1e8);
		
		if (i == 0) return matrix[i][j];

		int u = matrix[i][j] + recur(i-1, j, matrix);
		int ld = matrix[i][j] + recur(i-1, j-1, matrix);
		int rd = matrix[i][j] + recur(i-1, j+1, matrix);

		return Math.max(u, Math.max(ld, rd));
	}
}


// memoization:

import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int maxi = (int)(-1e8);
		int[][] dp = new int[n][m];

		for (int[] a : dp) {
			Arrays.fill(a, -1);
		}
		for (int j = 0; j < m; j++) {
			maxi = Math.max(maxi, recur(n-1, j, matrix, dp));
		}
		
		return maxi;
	}

	private static int recur(int i, int j, int[][] matrix, int[][] dp) {
		if (j < 0 || j >= matrix[0].length) return (int)(-1e8);
		
		if (i == 0) return matrix[i][j];

		if (dp[i][j] != -1) return dp[i][j];

		int u = matrix[i][j] + recur(i-1, j, matrix, dp);
		int ld = matrix[i][j] + recur(i-1, j-1, matrix, dp);
		int rd = matrix[i][j] + recur(i-1, j+1, matrix, dp);

		return dp[i][j] = Math.max(u, Math.max(ld, rd));
	}
}


// tabulation:

import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int maxi = (int)(-1e8);
		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0) {
					dp[i][j] = matrix[i][j];
				}
				else {
					int u = matrix[i][j] + dp[i-1][j];
					
					int ld = matrix[i][j];
					if (j-1 >= 0) {
						ld += dp[i-1][j-1];
					}
					else {
						ld += (int)(-1e8);
					}

					int rd = matrix[i][j];
					if (j+1 < m) {
						rd += dp[i-1][j+1];
					}
					else {
						rd += (int)(-1e8);
					}

					dp[i][j] = Math.max(u, Math.max(ld, rd));
				}
			}
		}

		for (int j = 0; j < m; j++) {
			maxi = Math.max(maxi, dp[n-1][j]);
		}
		
		return maxi;
	}
}


// tabulation - space optimised:

import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int maxi = (int)(-1e8);
		int[] prev = new int[m];

		for (int i = 0; i < n; i++) {
			int[] curr = new int[m];
			for (int j = 0; j < m; j++) {
				if (i == 0) {
					curr[j] = matrix[i][j];
				}
				else {
					int u = matrix[i][j] + prev[j];
					
					int ld = matrix[i][j];
					if (j-1 >= 0) {
						ld += prev[j-1];
					}
					else {
						ld += (int)(-1e8);
					}

					int rd = matrix[i][j];
					if (j+1 < m) {
						rd += prev[j+1];
					}
					else {
						rd += (int)(-1e8);
					}

					curr[j] = Math.max(u, Math.max(ld, rd));
				}
			}
			prev = curr;
		}

		for (int j = 0; j < m; j++) {
			maxi = Math.max(maxi, prev[j]);
		}
		
		return maxi;
	}
}