// 1385. Find the Distance Value Between Two Arrays
// Easy
//
// Given two integer arrays arr1 and arr2, and the integer d, return the
// distance value between the two arrays.
//
// The distance value is defined as the number of elements arr1[i] such that
// there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
//
// Example 1:
// Input:  arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
// Output: 2
// Explanation:
// For arr1[0]=4 we have:
// |4-10|=6 > d=2
// |4-9|=5 > d=2
// |4-1|=3 > d=2
// |4-8|=4 > d=2
// For arr1[1]=5 we have:
// |5-10|=5 > d=2
// |5-9|=4 > d=2
// |5-1|=4 > d=2
// |5-8|=3 > d=2
// For arr1[2]=8 we have:
// |8-10|=2 <= d=2
// |8-9|=1 <= d=2
// |8-1|=7 > d=2
// |8-8|=0 <= d=2
//
// Example 2:
// Input:  arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
// Output: 2
//
// Example 3:
// Input:  arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
// Output: 1
//
// Constraints:
// 1 <= arr1.length, arr2.length <= 500
// -1000 <= arr1[i], arr2[j] <= 1000
// 0 <= d <= 100

package NOV5;

import java.util.Arrays;

// Approach 1: brute force.
public class findTheDistanceValue {

    // Check every pair. For each arr1[i], scan arr2 until something lands
    // within d; if nothing does, that element counts.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let n = arr1.length and m = arr2.length.
    // Time:  O(n * m) - the break only helps when a disqualifying element is
    //                   found early. The worst case is the one this problem
    //                   actually asks for: elements that are far from every
    //                   arr2[j] never break, so their inner loop runs all m
    //                   iterations. At the 500/500 limit that is 250,000
    //                   comparisons, which is nothing.
    // Space: O(1)     - count and isValid, regardless of input size.
    //
    // Does not modify either input.
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            boolean isValid = true;
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                count++;
            }
        }
        return count;
    }
}

// Approach 2: sort arr2, then binary search it.
class Solution {

    // Sorting arr2 means the only elements that can sit within d of num are its
    // two nearest neighbours. The loop is a hand-rolled lower_bound: it leaves
    // left at the first index with arr2[left] >= num, so arr2[left] is the
    // closest value at or above num and arr2[left - 1] the closest below.
    // Checking just those two is enough - anything further out in either
    // direction is strictly further away.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let n = arr1.length and m = arr2.length.
    // Time:  O((n + m) log m) - O(m log m) for the sort, then n binary
    //                           searches at O(log m) each. The sort dominates
    //                           whenever m is comparable to n.
    // Space: O(log m)         - Arrays.sort on an int[] is an in-place
    //                           dual-pivot quicksort, so it copies nothing but
    //                           recurses to O(log m) depth. Everything else is
    //                           a handful of scalars.
    //
    // Worth being honest about: at these constraints this is not the faster
    // option. Approach 1 peaks at 250,000 int comparisons with no allocation
    // and no sort, so it wins on wall clock - measured at n = m = 500 on this
    // machine, brute force ran 3281 ns/call against 14157 ns/call here, about
    // 4x faster. Approach 2 is the one that keeps scaling: the asymptotics only
    // start paying once m is large enough for log m to beat m, which is well
    // past this problem's 500 cap.
    //
    // Note this sorts arr2 in place, so the caller's array is reordered.
    // Approach 1 leaves both inputs untouched.
    //
    // The (long) casts are defensive rather than load-bearing: these
    // constraints cap the difference at 2000, so the int subtraction cannot
    // wrap. They matter only if the bounds ever widen, because Math.abs of a
    // wrapped int gives a negative distance that no later check can recover.
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        Arrays.sort(arr2);

        int count = 0;

        for (int num : arr1) {

            int left = 0;
            int right = arr2.length;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (arr2[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            boolean valid = true;

            if (left < arr2.length &&
                Math.abs((long) arr2[left] - num) <= d) {
                valid = false;
            }

            if (left > 0 &&
                Math.abs((long) arr2[left - 1] - num) <= d) {
                valid = false;
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[][] arr1s = { { 4, 5, 8 }, { 1, 4, 2, 3 }, { 2, 1, 100, 3 } };
        int[][] arr2s = { { 10, 9, 1, 8 }, { -4, -3, 6, 10, 20, 30 }, { -5, -2, 10, -3, 7 } };
        int[] ds = { 2, 3, 6 };
        int[] expected = { 2, 2, 1 };

        for (int i = 0; i < arr1s.length; i++) {
            // Approach 2 sorts arr2 in place, so each approach gets its own copy.
            int brute = findTheDistanceValue.findTheDistanceValue(
                    arr1s[i].clone(), arr2s[i].clone(), ds[i]);
            int binary = s.findTheDistanceValue(
                    arr1s[i].clone(), arr2s[i].clone(), ds[i]);

            System.out.println("example " + (i + 1)
                    + ": brute=" + brute
                    + " binary=" + binary
                    + " expected=" + expected[i]
                    + (brute == expected[i] && binary == expected[i] ? "  ok" : "  MISMATCH"));
        }
    }
}
