// 462. Minimum Moves to Equal Array Elements II
// Medium

// Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

// In one move, you can increment or decrement an element of the array by 1.

// Test cases are designed so that the answer will fit in a 32-bit integer.

// Example 1:

// Input: nums = [1,2,3]
// Output: 2
// Explanation:
// Only two moves are needed (remember each move increments or decrements one element):
// [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
// Example 2:

// Input: nums = [1,10,2,9]
// Output: 16

import java.util.Arrays;

class Solution {
    // Sorting + median: total distance is minimised at the median, so every
    // element only has to travel to nums[n / 2].
    // Time:  O(n log n) - the sort dominates; the distance scan is O(n).
    // Space: O(log n)   - Arrays.sort on an int[] is an in-place dual-pivot
    //                     quicksort, so it copies nothing, but it recurses.
    //                     Everything else is a couple of scalars: O(1).
    public int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int median = nums[nums.length >> 1];
        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }

    // Identical to minMoves2 - `length >> 1` and `length / 2` are the same for a
    // non-negative length, so this is the same algorithm written two ways.
    // Time:  O(n log n)
    // Space: O(log n)   - same in-place sort, same recursion stack.
    public int minMoves3(int[] nums) {

        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Solution s2 = new Solution();
        System.out.println(s.minMoves2(new int[] { 1, 2, 3 }));
        System.out.println(s.minMoves2(new int[] { 1, 10, 2, 9 }));
        System.out.println(s2.minMoves3(new int[] { 1, 2, 3 }));
        System.out.println(s2.minMoves3(new int[] { 1, 10, 2, 9 }));
    }
}
