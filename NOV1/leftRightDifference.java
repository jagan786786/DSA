// 2574. Left and Right Sum Differences
// Easy
//
// You are given a 0-indexed integer array nums of size n.
// Define two arrays leftSum and rightSum where:
// leftSum[i] is the sum of elements to the left of the index i in the array nums.
// If there is no such element, leftSum[i] = 0.
// rightSum[i] is the sum of elements to the right of the index i in the array nums.
// If there is no such element, rightSum[i] = 0.
//
// Return an integer array answer of size n where answer[i] = |leftSum[i] - rightSum[i]|.
//
// Example 1:
// Input:  nums = [10,4,8,3]
// Output: [15,1,11,22]
// Explanation: leftSum is [0,10,14,22] and rightSum is [15,11,3,0].
// answer = [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
//
// Example 2:
// Input:  nums = [1]
// Output: [0]
// Explanation: leftSum is [0] and rightSum is [0], so answer = [|0 - 0|] = [0].

import java.util.Arrays;

class Solution {

    // Running prefix sum: rightSum[i] is whatever is left once the prefix and
    // nums[i] itself are taken off the total, so no second array is needed.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Time:  O(n) - two sequential passes (one to total, one to fill answer).
    //               2n steps, which is linear.
    // Space: O(n) - the answer array that has to be returned. Excluding the
    //               required output it is O(1): just totalSum and leftSum.
    //
    // Sums are accumulated in long because n can be 1000 and each value up to
    // 10^5, so a total of 10^8 is fine in int, but the long costs nothing and
    // keeps the method safe if the bounds grow.
    public int[] leftRightDifference(int[] nums) {

        long totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        long leftSum = 0;
        int[] answer = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            long rightSum = totalSum - leftSum - nums[i];

            answer[i] = (int) Math.abs(leftSum - rightSum);

            leftSum += nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.leftRightDifference(new int[] { 10, 4, 8, 3 }))); // [15, 1, 11, 22]
        System.out.println(Arrays.toString(s.leftRightDifference(new int[] { 1 })));           // [0]
    }
}
