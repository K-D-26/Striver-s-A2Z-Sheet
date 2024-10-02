// recursion:

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(recur(n));
	}

	private static int recur(int n) {
		if (n <= 1) {
			return n;
		}

		return recur(n-1) + recur(n-2);
	}
}


// memoization (top-down):

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);
		System.out.println(recur(n, dp));
	}

	private static int recur(int n, int[] dp) {
		if (n <= 1) {
			return n;
		}

		if (dp[n] != -1) return dp[n];

		return dp[n] = recur(n-1, dp) + recur(n-2, dp);
	}
}


// tabulation (bottom-up):

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		Arrays.fill(dp, -1);
		
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}

		System.out.println(dp[n]);
	}
}


// tabulation - space optimised:

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		if (n <= 1) {
			System.out.println(n);
			return;
		}
		
		int prev2 = 0;
		int prev = 1;
		int curr = 0;
		for (int i = 2; i <= n; i++) {
			curr = prev + prev2;
			prev2 = prev;
			prev = curr;
		}

		System.out.println(curr);
	}
}