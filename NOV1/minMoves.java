// 453. Minimum Moves to Equal Array Elements
// Easy
//
// Given an integer array nums of size n, return the minimum number of moves
// required to make all array elements equal.
//
// In one move, you can increment n - 1 elements of the array by 1.
//
// Example 1:
// Input:  nums = [1,2,3]
// Output: 3
// Explanation: [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
//
// Example 2:
// Input:  nums = [1,1,1]
// Output: 0

class Solution {

    // Incrementing n - 1 elements is the same as decrementing the remaining
    // one, so the target is the minimum and the answer is the total distance
    // every element has to fall: sum - n * min.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Time:  O(n) - one scan that tracks the minimum and the sum together.
    // Space: O(1) - two accumulators, independent of n.
    //
    // sum is long and nums.length * min is widened before multiplying. Like
    // NOV3's minMoves3 this is defensive rather than a fix: with only +, - and
    // *, int arithmetic wraps modulo 2^32 and still produces the right answer
    // whenever that answer fits in int. The widening documents the intent and
    // keeps the method honest if the return type is ever widened to long.
    public int minMoves(int[] nums) {

        int min = Integer.MAX_VALUE;
        long sum = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        return (int) (sum - (long) nums.length * min);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minMoves(new int[] { 1, 2, 3 })); // 3
        System.out.println(s.minMoves(new int[] { 1, 1, 1 })); // 0
    }
}
