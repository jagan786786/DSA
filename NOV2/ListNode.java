// 2. Add Two Numbers
// Medium
//
// You are given two non-empty linked lists representing two non-negative
// integers. The digits are stored in reverse order, and each of their nodes
// contains a single digit. Add the two numbers and return the sum as a linked
// list.
//
// You may assume the two numbers do not contain any leading zero, except the
// number 0 itself.
//
// Example 1:
// Input:  l1 = [2,4,3], l2 = [5,6,4]
// Output: [7,0,8]
// Explanation: 342 + 465 = 807.
//
// Example 2:
// Input:  l1 = [0], l2 = [0]
// Output: [0]
//
// Example 3:
// Input:  l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
// Output: [8,9,9,9,0,0,0,1]

// LeetCode supplies ListNode itself. It is declared here so the file compiles
// and runs on its own; delete this class when pasting into the LeetCode editor.
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    // Schoolbook column addition. Reverse order is what makes this easy: the
    // heads are the least significant digits, so one walk adds the columns in
    // the order a carry naturally propagates.
    //
    // The carry > 0 term in the loop condition is what handles a final carry
    // out of the longest list, as in example 3 where 7 digits + 4 digits
    // produces an 8-digit result.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let n and m be the lengths of l1 and l2.
    // Time:  O(max(n, m)) - one node of each list consumed per iteration, plus
    //                       at most one extra iteration for the trailing carry.
    // Space: O(max(n, m)) - the result list, which is the required output and
    //                       is 1 node longer than the longer input at most.
    //                       Excluding the output the extra space is O(1):
    //                       dummy, current, carry and sum.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    // ─── Test helpers ───────────────────────────────────────────────────────

    private static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static String show(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        for (ListNode n = head; n != null; n = n.next) {
            if (n != head) {
                sb.append(",");
            }
            sb.append(n.val);
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(show(s.addTwoNumbers(build(2, 4, 3), build(5, 6, 4))));
        // [7,0,8]
        System.out.println(show(s.addTwoNumbers(build(0), build(0))));
        // [0]
        System.out.println(show(s.addTwoNumbers(build(9, 9, 9, 9, 9, 9, 9), build(9, 9, 9, 9))));
        // [8,9,9,9,0,0,0,1]
    }
}
