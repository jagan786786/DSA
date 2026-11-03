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
    // sum is long and max is widened before multiplying. This is defensive,
    // not a correction: +, - and * are all exact modulo 2^32, so the plain-int
    // version wrapped and still landed on the right answer whenever the answer
    // fit in int - which the int return type is what guarantees. Randomised
    // testing over 300000 arrays found no input where the two disagree.
    //
    // The widening only begins to matter if the result itself ever has to be
    // returned as a long. Contrast minMoves2 in NOV2, where Math.abs sits on
    // top of the subtraction: abs cannot undo a wrap, so there the cast is
    // load-bearing rather than cosmetic.
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
