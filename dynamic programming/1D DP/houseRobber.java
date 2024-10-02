// It is very similar to the previous question, max sum of non-adjacent elements

// tabulation - space optimised

import java.util.*;
import java.io.*;

public class Solution {
	public static long houseRobber(int[] houses) {
		int n = houses.length;
		if (n == 1) return houses[0];

		int[] temp1 = Arrays.copyOfRange(houses, 1, n);			// excluding first element
		int[] temp2 = Arrays.copyOfRange(houses, 0, n-1);		// excluding last element
		
		return Math.max(recur(temp1), recur(temp2));	
	}

	static long recur(int[] nums) {
		long prev = nums[0];
		long prev2 = 0;

		for (int i = 1; i < nums.length; i++) {
			long notPick = prev;
			long pick = nums[i];
			if (i > 1) {
				pick += prev2;
			}

			long curr = Math.max(pick, notPick);
			prev2 = prev;
			prev = curr;
		}
		
		return prev;	
	}	
}