// maximum sum of non-adjacent elements (code studio) OR house robber (leetcode)

// recursion:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		int n = nums.size();
		return recur(n-1, nums);
	}
	
	private static int recur(int ind, ArrayList<Integer> nums) {
		if (ind == 0) return nums.get(0);

		if (ind < 0) return 0;

		int pick = nums.get(ind) + recur(ind-2, nums);
		int notPick = 0 + recur(ind-1, nums);

		return Math.max(pick, notPick);
	}
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		return recur(n-1, nums, dp);
	}
	
	private static int recur(int ind, ArrayList<Integer> nums, int[] dp) {
		if (ind == 0) return nums.get(0);

		if (ind < 0) return 0;

		if (dp[ind] != -1) return dp[ind];

		int pick = nums.get(ind) + recur(ind-2, nums, dp);
		int notPick = 0 + recur(ind-1, nums, dp);

		return dp[ind] = Math.max(pick, notPick);
	}
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		int n = nums.size();
		int[] dp = new int[n];
		dp[0] = nums.get(0);

		for (int i = 1; i < n; i++) {
			int notPick = 0 + dp[i - 1];
			int pick = nums.get(i);
			if (i > 1) {
				pick += dp[i - 2];
			}

			dp[i] = Math.max(pick, notPick);
		}

		return dp[n-1];
	}
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		int n = nums.size();
		int prev = nums.get(0), prev2 = 0;

		for (int i = 1; i < n; i++) {
			int notPick = 0 + prev;
			int pick = nums.get(i);
			if (i > 1) {
				pick += prev2;
			}

			int curr = Math.max(pick, notPick);
			prev2 = prev;
			prev = curr;
		}

		return prev;
	}
}