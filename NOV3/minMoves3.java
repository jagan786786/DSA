// 3736. Minimum Moves to Equal Array Elements III
// Easy
//
// You are given an integer array nums.
//
// In one move, you may increase the value of any single element nums[i] by 1.
//
// Return the minimum total number of moves required so that all elements in
// nums become equal.
//
// Example 1:
// Input:  nums = [2,1,3]
// Output: 3
// Explanation:
// Increase nums[0] = 2 by 1 to make it 3.
// Increase nums[1] = 1 by 1 to make it 2.
// Increase nums[1] = 2 by 1 to make it 3.
// All elements are now 3, so the minimum total is 3 moves.
//
// Example 2:
// Input:  nums = [4,4,5]
// Output: 2
// Explanation:
// Increase nums[0] = 4 by 1 to make it 5.
// Increase nums[1] = 4 by 1 to make it 5.
// All elements are now 5, so the minimum total is 2 moves.

class Solution {

    // Moves only ever increase a value, so the target has to be the maximum -
    // anything lower is unreachable for the largest element. The cost is then
    // the total shortfall below that maximum: max * n - sum.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Time:  O(n) - one scan that tracks the maximum and the sum together.
    // Space: O(1) - two accumulators, independent of n.
    //
    // sum is long and max is widened before multiplying. With int arithmetic
    // `max * nums.length` overflows: for 100000 elements of [1000000, 1, 1, ...]
    // it returned 1214652193 instead of the correct 99998900001.
    public int minMoves(int[] nums) {

        int max = nums[0];
        long sum = 0;

        for (int n : nums) {
            max = Math.max(n, max);
            sum += n;
        }

        return (int) ((long) max * nums.length - sum);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minMoves(new int[] { 2, 1, 3 })); // 3
        System.out.println(s.minMoves(new int[] { 4, 4, 5 })); // 2
    }
}
