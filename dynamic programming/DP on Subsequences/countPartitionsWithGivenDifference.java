// recursion:




// memoization:

import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)(Math.pow(10, 9) + 7);
	
    static int recur(int i, int s, int[] num, int[][] dp) {
        if (i == 0) {
            if (s == 0 && num[0] == 0) return 2;
            if (s == 0 || s == num[0]) return 1;
            else return 0;
        }

        if (dp[i][s] != -1) return dp[i][s];

        int notTake = recur(i-1, s, num, dp);
        int take = 0;
        if (num[i] <= s) take = recur(i-1, s-num[i], num, dp);
        
        return dp[i][s] = (take + notTake) % mod;
    }

	public static int countPartitions(int n, int d, int[] arr) {
		int tot = 0;
		for(int i : arr) tot += i;

		if ((tot-d < 0) || ((tot-d)%2 == 1)) return 0;
        
        int s2 = (tot-d)/2;
		int[][] dp = new int[n][s2+1];

        for (int[] a : dp) Arrays.fill(a, -1);
            
		return recur(n-1, s2, arr, dp);
	}
}


// tabulation:

import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)(Math.pow(10, 9) + 7);
	
    static int recur(int d, int[] num) {
        int n = num.length;
        int[][] dp = new int[n][d+1];

        if (num[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;
        
        if (num[0] != 0 && num[0] <= d) dp[0][num[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= d; j++) {
                int notTake = dp[i-1][j];
                int take = 0;
                if (num[i] <= j) take = dp[i-1][j-num[i]];
                
                dp[i][j] = (take + notTake) % mod;
            }
        }

        return dp[n-1][d];
    }

	public static int countPartitions(int n, int d, int[] arr) {
		int tot = 0;
		for(int i : arr) tot += i;

		if ((tot-d < 0) || ((tot-d)%2 == 1)) return 0;
        
        int s2 = (tot-d)/2;
		return recur(s2, arr);
	}
}


// tabulation - space optimised:

import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)(Math.pow(10, 9) + 7);
	
    static int recur(int d, int[] num) {
        int n = num.length;
        int[] prev = new int[d+1];

        if (num[0] == 0) prev[0] = 2;
        else prev[0] = 1;
        
        if (num[0] != 0 && num[0] <= d) prev[num[0]] = 1;

        for (int i = 1; i < n; i++) {
            int[] curr = new int[d+1];
            for (int j = 0; j <= d; j++) {
                int notTake = prev[j];
                int take = 0;
                if (num[i] <= j) take = prev[j-num[i]];
                
                curr[j] = (take + notTake) % mod;
            }
            prev = curr;
        }

        return prev[d];
    }   

	public static int countPartitions(int n, int d, int[] arr) {
        // Tabulation (space optimised)
		int tot = 0;
		for (int i : arr) tot += i;

		if ((tot-d < 0) || ((tot-d)%2 == 1)) return 0;
        
        int s2 = (tot-d)/2;
		return recur(s2, arr);
	}
}