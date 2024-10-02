// recursion:

import java.util.*;
import java.io.*;

public class Solution {
	public static int uniquePaths(int m, int n) {
		return recur(m-1, n-1);
	}

	private static int recur(int i, int j) {
		if (i == 0 && j == 0) return 1;

		if (i < 0 || j < 0) return 0;

		int up = recur(i - 1, j);
		int left = recur(i, j - 1);

		return up + left;
	}
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
	public static int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for (int[] a : dp) {
			Arrays.fill(a, -1);
		}
		return recur(m-1, n-1, dp);
	}

	private static int recur(int i, int j, int[][] dp) {
		if (i == 0 && j == 0) return 1;

		if (i < 0 || j < 0) return 0;

		if (dp[i][j] != -1) return dp[i][j];

		int up = recur(i - 1, j, dp);
		int left = recur(i, j - 1, dp);

		return dp[i][j] = up + left;
	}
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
	public static int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
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
					dp[i][j] = up + left;
				}
			}
		}

		return dp[m-1][n-1];
	}
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
	public static int uniquePaths(int m, int n) {
		int[] prev = new int[n];

		for (int i = 0; i < m; i++) {
			int[] curr = new int[n];
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
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
					curr[j] = up + left;
				}
			}
			prev = curr;
		}

		return prev[n-1];
	}
}


// more optimised approach:
// refer this for better understanding - https://www.youtube.com/watch?v=t_f0nwwdg5o

import java.util.* ;
import java.io.*; 

public class Solution {
	public static int uniquePaths(int m, int n) {
		int N = m + n - 2;
		int r = m - 1;
		int ans = 1;

		for (int i = 1; i <= r; i++) {
			ans = ans * (N - r + i) / i;
		}

		return ans;
	}
}