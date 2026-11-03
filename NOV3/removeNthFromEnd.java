// 19. Remove Nth Node From End of List
// Medium
//
// Given the head of a linked list, remove the nth node from the end of the list
// and return its head.
//
// Example 1:
// Input:  head = [1,2,3,4,5], n = 2
// Output: [1,2,3,5]
//
// Example 2:
// Input:  head = [1], n = 1
// Output: []
//
// Example 3:
// Input:  head = [1,2], n = 1
// Output: [1]

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

    // Two pointers held n + 1 apart. Once fast runs off the end, slow sits on
    // the node just before the target, which is exactly what is needed to
    // unlink it. The dummy node is what makes removing the head require no
    // special case: slow can legitimately stop on the dummy itself.
    //
    // Advancing fast n + 1 times rather than n is what creates that one-node
    // gap. When n equals the list length, fast lands on null straight away,
    // the second loop never runs, and slow is still the dummy - so the head is
    // the node removed, as in example 2.
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let L be the length of the list.
    // Time:  O(L) - fast walks the whole list once; slow walks L - n of it.
    //               A single pass, not two, even though there are two loops.
    // Space: O(1) - the dummy plus two pointers, regardless of L.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
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
        System.out.println(show(s.removeNthFromEnd(build(1, 2, 3, 4, 5), 2))); // [1,2,3,5]
        System.out.println(show(s.removeNthFromEnd(build(1), 1)));             // []
        System.out.println(show(s.removeNthFromEnd(build(1, 2), 1)));          // [1]
    }
}
