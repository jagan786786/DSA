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

/**
 * Definition for singly-linked list.
 *
 * LeetCode defines ListNode in its runtime. The guard below defines it only
 * when it is missing, so this file runs with plain `node addTwoNumbers.js` and
 * still pastes into LeetCode unchanged.
 */
if (typeof ListNode === "undefined") {
    globalThis.ListNode = function (val, next) {
        this.val = val === undefined ? 0 : val;
        this.next = next === undefined ? null : next;
    };
}

/**
 * Schoolbook column addition. Reverse order is what makes this easy: the heads
 * are the least significant digits, so one walk adds the columns in the order a
 * carry naturally propagates.
 *
 * The `carry > 0` term in the loop condition handles a final carry out of the
 * longest list, as in example 3 where 7 digits + 4 digits gives 8 digits.
 *
 * ─── Complexity ─────────────────────────────────────────────────────────────
 * Let n and m be the lengths of l1 and l2.
 * Time:  O(max(n, m)) - one node of each list consumed per iteration, plus at
 *                       most one extra iteration for the trailing carry.
 * Space: O(max(n, m)) - the result list, which is the required output and is at
 *                       most 1 node longer than the longer input. Excluding
 *                       the output the extra space is O(1).
 *
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var addTwoNumbers = function (l1, l2) {
    let dummy = new ListNode(0);
    let current = dummy;
    let carry = 0;

    while (l1 !== null || l2 !== null || carry > 0) {
        let sum = carry;

        if (l1 !== null) {
            sum += l1.val;
            l1 = l1.next;
        }

        if (l2 !== null) {
            sum += l2.val;
            l2 = l2.next;
        }

        carry = Math.floor(sum / 10);

        current.next = new ListNode(sum % 10);
        current = current.next;
    }

    return dummy.next;
};

// ─── Examples from the statement, run only with `node addTwoNumbers.js` ─────
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

    console.log(show(addTwoNumbers(build([2, 4, 3]), build([5, 6, 4]))));
    // [7,0,8]
    console.log(show(addTwoNumbers(build([0]), build([0]))));
    // [0]
    console.log(show(addTwoNumbers(build([9, 9, 9, 9, 9, 9, 9]), build([9, 9, 9, 9]))));
    // [8,9,9,9,0,0,0,1]
}
