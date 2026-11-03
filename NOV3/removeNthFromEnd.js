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

/**
 * Definition for singly-linked list.
 *
 * LeetCode defines ListNode in its runtime. The guard below defines it only
 * when it is missing, so this file runs with plain `node removeNthFromEnd.js`
 * and still pastes into LeetCode unchanged.
 */
if (typeof ListNode === "undefined") {
    globalThis.ListNode = function (val, next) {
        this.val = val === undefined ? 0 : val;
        this.next = next === undefined ? null : next;
    };
}

/**
 * Two pointers held n + 1 apart. Once fast runs off the end, slow sits on the
 * node just before the target, which is exactly what is needed to unlink it.
 * The dummy node is what makes removing the head require no special case: slow
 * can legitimately stop on the dummy itself.
 *
 * Advancing fast n + 1 times rather than n is what creates that one-node gap.
 * When n equals the list length, fast lands on null straight away, the second
 * loop never runs, and slow is still the dummy - so the head is the node
 * removed, as in example 2.
 *
 * ─── Complexity ─────────────────────────────────────────────────────────────
 * Let L be the length of the list.
 * Time:  O(L) - fast walks the whole list once; slow walks L - n of it. A
 *               single pass, not two, even though there are two loops.
 * Space: O(1) - the dummy plus two pointers, regardless of L.
 *
 * @param {ListNode} head
 * @param {number} n
 * @return {ListNode}
 */
var removeNthFromEnd = function (head, n) {
    let dummy = new ListNode(0);
    dummy.next = head;

    let fast = dummy;
    let slow = dummy;

    for (let i = 0; i <= n; i++) {
        fast = fast.next;
    }

    while (fast !== null) {
        fast = fast.next;
        slow = slow.next;
    }

    slow.next = slow.next.next;
    return dummy.next;
};

// ─── Examples from the statement, run only with `node removeNthFromEnd.js` ──
if (typeof require !== "undefined" && require.main === module) {
    const build = (vals) => {
        const dummy = new ListNode(0);
        let cur = dummy;
        for (const v of vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    };
    const show = (head) => {
        const out = [];
        for (let n = head; n !== null; n = n.next) out.push(n.val);
        return JSON.stringify(out);
    };

    console.log(show(removeNthFromEnd(build([1, 2, 3, 4, 5]), 2))); // [1,2,3,5]
    console.log(show(removeNthFromEnd(build([1]), 1)));             // []
    console.log(show(removeNthFromEnd(build([1, 2]), 1)));          // [1]
}
