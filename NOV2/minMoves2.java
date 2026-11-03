// 462. Minimum Moves to Equal Array Elements II
// Medium
//
// Given an integer array nums of size n, return the minimum number of moves
// required to make all array elements equal.
//
// In one move, you can increment or decrement an element of the array by 1.
//
// Test cases are designed so that the answer will fit in a 32-bit integer.
//
// Example 1:
// Input:  nums = [1,2,3]
// Output: 2
// Explanation: [1,2,3] => [2,2,3] => [2,2,2]
//
// Example 2:
// Input:  nums = [1,10,2,9]
// Output: 16

import java.util.Arrays;

class Solution {

    // Sorting + median: the sum of absolute distances is minimised at the
    // median, so every element only has to travel to nums[n / 2]. For an even
    // n any point between the two middle values is equally good, which is why
    // taking the upper middle element is fine.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Time:  O(n log n) - the sort dominates; the distance scan after it is O(n).
    // Space: O(log n)   - Arrays.sort on an int[] is an in-place dual-pivot
    //                     quicksort, so it copies nothing, but it recurses to
    //                     O(log n) depth. Everything else is a few scalars.
    //
    // Note this sorts nums in place, so the caller's array is reordered.
    // moves is long because n up to 10^5 and values up to 10^9 make the
    // intermediate total exceed int even when the final answer fits.
    public int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int median = nums[nums.length >> 1];
        long moves = 0;

        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }

    // Identical to minMoves2 - `length >> 1` and `length / 2` are the same for
    // a non-negative length, so this is the same algorithm written two ways,
    // kept side by side only to show the two spellings of the midpoint.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Time:  O(n log n) - same sort.
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
        System.out.println(s.minMoves2(new int[] { 1, 2, 3 }));     // 2
        System.out.println(s.minMoves2(new int[] { 1, 10, 2, 9 })); // 16
        System.out.println(s.minMoves3(new int[] { 1, 2, 3 }));     // 2
        System.out.println(s.minMoves3(new int[] { 1, 10, 2, 9 })); // 16
    }
}
