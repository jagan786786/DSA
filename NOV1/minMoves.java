// Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

// In one move, you can increment n - 1 elements of the array by 1.

// Example 1:

// Input: nums = [1,2,3]
// Output: 3
// Explanation: Only three moves are needed (remember each move increments two elements):
// [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
// Example 2:

// Input: nums = [1,1,1]
// Output: 0

class Solution {
    public int minMoves(int[] nums) {

        int min = Integer.MAX_VALUE;
        long sum = 0;

        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        return (int)(sum - (long) nums.length * min);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minMoves(new int[] { 1, 2, 3 }));
        System.out.println(s.minMoves(new int[] { 1, 1, 1 }));
    }
}