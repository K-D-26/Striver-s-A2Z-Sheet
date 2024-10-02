// recursion:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		return recur(0, 0, c-1, r, c, grid);
	}

	private static int recur(int i, int j1, int j2, int r, int c, int[][] grid) {
		if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) return (int)(-1e8);

		if (i == r-1) {
			if (j1 == j2) return grid[i][j1];
			else return grid[i][j1] + grid[i][j2];
		}

		int maxi = (int)(-1e8);
		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				int value = 0;
				if (j1 == j2) value = grid[i][j1];
				else value = grid[i][j1] + grid[i][j2];
				value += recur(i+1, j1+dj1, j2+dj2, r, c, grid);
				maxi = Math.max(maxi, value);
			}
		}

		return maxi;
	}
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		int[][][] dp = new int[r][c][c];
		for (int[][] a : dp) {
			for (int[] b : a) {
				Arrays.fill(b, -1);
			}
		}


		return recur(0, 0, c-1, r, c, grid, dp);
	}

	private static int recur(int i, int j1, int j2, int r, int c, int[][] grid, int[][][] dp) {
		if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) return (int)(-1e8);

		if (i == r-1) {
			if (j1 == j2) return grid[i][j1];
			else return grid[i][j1] + grid[i][j2];
		}

		if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

		int maxi = (int)(-1e8);
		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				int value = 0;
				if (j1 == j2) value = grid[i][j1];
				else value = grid[i][j1] + grid[i][j2];
				value += recur(i+1, j1+dj1, j2+dj2, r, c, grid, dp);
				maxi = Math.max(maxi, value);
			}
		}

		return dp[i][j1][j2] = maxi;
	}
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumChocolates(int n, int m, int[][] grid) {
		int[][][] dp = new int[n][m][m];
		
		for (int i = n-1; i >= 0; i--) {
			for (int j1 = 0; j1 < m; j1++) {
				for (int j2 = 0; j2 < m; j2++) {
					if (i == n-1) {
						if (j1 == j2) dp[i][j1][j2] = grid[i][j1];
						else dp[i][j1][j2] = grid[i][j1] + grid[i][j2];
					}
					else {
						int maxi = (int)(-1e8);
						for (int dj1 = -1; dj1 <= 1; dj1++) {
							for (int dj2 = -1; dj2 <= 1; dj2++) {
								int value = 0;
								if (j1 == j2) value = grid[i][j1];
								else value = grid[i][j1] + grid[i][j2];
								
								if (j1+dj1 >= 0 && j1+dj1 < m && j2+dj2 >= 0 && j2+dj2 < m) {
									value += dp[i+1][j1+dj1][j2+dj2];
								}
								else {
									value += (int)(-1e8);
								}
								maxi = Math.max(maxi, value);
							}
						}

						dp[i][j1][j2] = maxi;
					}
				}
			}
		}

		return dp[0][0][m-1];
	}
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumChocolates(int n, int m, int[][] grid) {
		int[][] front = new int[m][m];
		
		for (int i = n-1; i >= 0; i--) {
			int[][] curr = new int[m][m];
			for (int j1 = 0; j1 < m; j1++) {
				for (int j2 = 0; j2 < m; j2++) {
					if (i == n-1) {
						if (j1 == j2) curr[j1][j2] = grid[i][j1];
						else curr[j1][j2] = grid[i][j1] + grid[i][j2];
					}
					else {
						int maxi = (int)(-1e8);
						for (int dj1 = -1; dj1 <= 1; dj1++) {
							for (int dj2 = -1; dj2 <= 1; dj2++) {
								int value = 0;
								if (j1 == j2) value = grid[i][j1];
								else value = grid[i][j1] + grid[i][j2];
								
								if (j1+dj1 >= 0 && j1+dj1 < m && j2+dj2 >= 0 && j2+dj2 < m) {
									value += front[j1+dj1][j2+dj2];
								}
								else {
									value += (int)(-1e8);
								}
								maxi = Math.max(maxi, value);
							}
						}

						curr[j1][j2] = maxi;
					}
				}
			}
			front = curr;
		}

		return front[0][m-1];
	}
}