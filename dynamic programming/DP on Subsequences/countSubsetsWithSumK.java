// recursion:

import java.util.*;
import java.io.*;

public class Solution {
    static int zeros;
    static int mod = (int)(1e9 + 7);
    public static int findWays(int nums[], int sum) {
        int n = nums.length;
        for (int i : nums) {
            if (i == 0) zeros++;
        }
        return recur(n-1, sum, nums);
    }

    private static int recur(int i, int sum, int[] nums) {
        if (sum == 0 && nums[i] == 0) return (int)Math.pow(2, zeros);
        if (sum == 0) return 1;
        if (i == 0) return (nums[0] == sum) ? 1 : 0;

        int notTake = recur(i-1, sum, nums);
        int take = 0;
        if (nums[i] <= sum) take = recur(i-1, sum - nums[i], nums);

        return (take + notTake) % mod;
    }
}


// memoization:

import java.util.*;
import java.io.*;

public class Solution {
    static int zeros;
    static int mod = (int)(1e9 + 7);
    public static int findWays(int nums[], int sum) {
        int n = nums.length;
        int[][] dp = new int[n][sum+1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        for (int i : nums) {
            if (i == 0) zeros++;
        }
        return recur(n-1, sum, nums, dp);
    }

    private static int recur(int i, int sum, int[] nums, int[][] dp) {
        if (sum == 0 && nums[i] == 0) return (int)Math.pow(2, zeros);
        if (sum == 0) return 1;
        if (i == 0) return (nums[0] == sum) ? 1 : 0;

        if (dp[i][sum] != -1) return dp[i][sum];

        int notTake = recur(i-1, sum, nums, dp);
        int take = 0;
        if (nums[i] <= sum) take = recur(i-1, sum - nums[i], nums, dp);

        return dp[i][sum] = (take + notTake) % mod;
    }
}


// tabulation:

import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int nums[], int tar) {
        int n = nums.length;
        int mod = (int)(1e9 + 7);
        int zeros = 0;
        int[][] dp = new int[n][tar+1];
        for (int i : nums) {
            if (i == 0) zeros++;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) dp[i][0] = (int)Math.pow(2, zeros);
            else dp[i][0] = 1;
        }
        if (nums[0] <= tar) dp[0][nums[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int sum = 0; sum <= tar; sum++) {
                int notTake = dp[i-1][sum];
                int take = 0;
                if (nums[i] <= sum) take = dp[i-1][sum - nums[i]];

                dp[i][sum] = (take + notTake) % mod;
            }
        }

        return dp[n-1][tar];
    }
}


// tabulation - space optimised:

import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int nums[], int tar) {
        int n = nums.length;
        int mod = (int)(1e9 + 7);
        int zeros = 0;
        int[] prev = new int[tar+1];
        for (int i : nums) {
            if (i == 0) zeros++;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) prev[0] = (int)Math.pow(2, zeros);
            else prev[0] = 1;
        }
        if (nums[0] <= tar) prev[nums[0]] = 1;

        for (int i = 1; i < n; i++) {
            int[] curr = new int[tar+1];
            for (int sum = 0; sum <= tar; sum++) {
                int notTake = prev[sum];
                int take = 0;
                if (nums[i] <= sum) take = prev[sum - nums[i]];

                curr[sum] = (take + notTake) % mod;
            }
            prev = curr;
        }

        return prev[tar];
    }
}