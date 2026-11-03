// 21. Merge Two Sorted Lists
// Easy
//
// You are given the heads of two sorted linked lists list1 and list2.
//
// Merge the two lists into one sorted list. The list should be made by splicing
// together the nodes of the first two lists.
//
// Return the head of the merged linked list.
//
// Example 1:
// Input:  list1 = [1,2,4], list2 = [1,3,4]
// Output: [1,1,2,3,4,4]
//
// Example 2:
// Input:  list1 = [], list2 = []
// Output: []
//
// Example 3:
// Input:  list1 = [], list2 = [0]
// Output: [0]

// LeetCode supplies ListNode itself. It is declared here so the file compiles
// and runs on its own; delete this class when pasting into the LeetCode editor.
// It cannot be public, because a public class has to match the file name.
class ListNode {

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

    // The merge step of merge sort: repeatedly take the smaller head. Nodes are
    // spliced, not copied, so no new node is allocated except the dummy, which
    // is what removes the "which list starts the result" special case.
    //
    // Using <= rather than < keeps equal values in list1-then-list2 order,
    // making the merge stable - visible in example 1, where the two 1s and the
    // two 4s keep their original lists' order.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let n and m be the lengths of list1 and list2.
    // Time:  O(n + m) - every node is visited and spliced exactly once. The
    //                   loop ends as soon as one list empties, and the leftover
    //                   tail is attached in O(1) rather than walked.
    // Space: O(1)     - the dummy and one cursor. The result reuses the input
    //                   nodes, so no space scales with the input.
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        // The loop only exits when at least one list is exhausted, so at most
        // one tail is left. A single assignment covers it - the previous two
        // separate ifs could never both fire.
        current.next = (list1 != null) ? list1 : list2;

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
        System.out.println(show(s.mergeTwoLists(build(1, 2, 4), build(1, 3, 4)))); // [1,1,2,3,4,4]
        System.out.println(show(s.mergeTwoLists(build(), build())));               // []
        System.out.println(show(s.mergeTwoLists(build(), build(0))));              // [0]
    }
}
