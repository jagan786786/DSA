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

/**
 * Moves only ever increase a value, so the target has to be the maximum -
 * anything lower is unreachable for the largest element. The cost is then the
 * total shortfall below that maximum: max * n - sum.
 *
 * ─── Complexity ─────────────────────────────────────────────────────────────
 * Time:  O(n) - one scan that tracks the maximum and the sum together.
 * Space: O(1) - two accumulators, independent of n.
 *
 * No overflow guard is needed here, unlike the Java version: JS numbers are
 * doubles and stay exact up to 2^53, well above the ~10^14 this can reach.
 *
 * @param {number[]} nums
 * @return {number}
 */
var minMoves = function (nums) {
    let max = nums[0];
    let sum = 0;

    for (const n of nums) {
        max = Math.max(n, max);
        sum += n;
    }

    return max * nums.length - sum;
};

// ─── Examples from the statement, run only with `node minMoves3.js` ─────────
if (typeof require !== "undefined" && require.main === module) {
    console.log(minMoves([2, 1, 3])); // 3
    console.log(minMoves([4, 4, 5])); // 2
}
