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

/**
 * Sorting + median: the sum of absolute distances is minimised at the median,
 * so every element only has to travel to nums[n >> 1]. For an even n any point
 * between the two middle values is equally good, so the upper middle is fine.
 *
 * ─── Complexity ─────────────────────────────────────────────────────────────
 * Time:  O(n log n) - the sort dominates; the distance scan after it is O(n).
 * Space: O(n)       - V8's Array.prototype.sort is TimSort, which allocates a
 *                     temporary buffer. That makes it worse than the Java
 *                     int[] version, which sorts in place at O(log n).
 *
 * Note this sorts nums in place, so the caller's array is reordered.
 *
 * The Java twin needs a (long) cast around num - median because Math.abs there
 * cannot undo an int wrap. Nothing equivalent is needed here: doubles hold
 * every value these inputs can produce.
 *
 * @param {number[]} nums
 * @return {number}
 */
var minMoves2 = function (nums) {
    nums.sort((a, b) => a - b);
    const median = nums[nums.length >> 1];
    let moves = 0;
    for (const n of nums) moves += Math.abs(n - median);
    return moves;
};

/**
 * Identical to minMoves2 - `length >> 1` and `(length / 2) | 0` are the same
 * for a non-negative length, so this is the same algorithm written two ways,
 * kept side by side only to show the two spellings of the midpoint.
 *
 * ─── Complexity ─────────────────────────────────────────────────────────────
 * Time:  O(n log n) - same sort.
 * Space: O(n)       - same TimSort buffer.
 *
 * @param {number[]} nums
 * @return {number}
 */
var minMoves3 = function (nums) {
    nums.sort((a, b) => a - b);
    const median = nums[(nums.length / 2) | 0];
    let moves = 0;
    for (const n of nums) moves += Math.abs(n - median);
    return moves;
};

// ─── Examples from the statement, run only with `node minMoves2.js` ─────────
if (typeof require !== "undefined" && require.main === module) {
    console.log(minMoves2([1, 2, 3]));     // 2
    console.log(minMoves2([1, 10, 2, 9])); // 16
    console.log(minMoves3([1, 2, 3]));     // 2
    console.log(minMoves3([1, 10, 2, 9])); // 16
}
