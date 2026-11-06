# DSA

A collection of data structures and algorithms solutions, maintained as daily practice for coding
interviews. Most problems are solved twice, once in **Java** and once in **JavaScript**.

## Structure

Solutions are grouped into folders by the day they were solved, with one problem per file:


## File shape

Every file is standalone and runs on its own:

```java
// Problem statement
//
// Example 1:
// Input:  ...
// Output: ...

class Solution {
    // How the approach works, and why.
    //
    // ─── Complexity ─────────────────────────────────────────────
    // Time:  O(...) - why
    // Space: O(...) - why
    public ReturnType methodName(Params params) {
        // solution
    }

    public static void main(String[] args) {
        // the examples from the statement
    }
}
```

## Running a solution

### Java

Compile one file at a time into its own output directory, then run `Solution`:

```bash
javac -d out/minMoves NOV1/minMoves.java
java -cp out/minMoves Solution
```

`NOV5` declares a package, so it is addressed by its qualified name instead:

```bash
javac -d out/distance NOV5/findTheDistanceValue.java
java -cp out/distance NOV5.Solution
```

`NOV6` keeps its examples in a separate `Main` class rather than in `Solution`, so that is the
class to run:

```bash
javac -d out/servers NOV6/countServers.java
java -cp out/servers Main
```

Every file declares its own `class Solution`, which is what LeetCode expects. That means **a
whole folder cannot be compiled at once** — `javac NOV3/*.java` fails with
`duplicate class: Solution`. Compile per file, into separate directories.

### JavaScript

```bash
node NOV4/mergeTwoLists.js
```

Each file prints the examples from its statement when run directly. The examples are wrapped in
`require.main === module`, so nothing runs when the file is pasted into LeetCode.

Requires a JDK 8 or newer and Node 12 or newer.

## Linked-list problems

LeetCode provides `ListNode` in its own runtime, but a local file needs it to compile and run:

- **Java** files declare `class ListNode` directly. Delete that class when pasting into LeetCode,
  or the editor reports a duplicate.
- **JavaScript** files define it behind `if (typeof ListNode === "undefined")`, so they paste into
  LeetCode unchanged.

## Topics

Problems cover the usual interview ground as they are added:

- Arrays and strings
- Two pointers and sliding window
- Hashing
- Sorting and searching
- Recursion and backtracking
- Linked lists, stacks and queues
- Trees and graphs
- Dynamic programming
- Greedy algorithms
- Math and bit manipulation

## Conventions

- One problem per file, named after the method being implemented.
- The problem statement and examples go in a comment block at the top of the file.
- Every solution carries a `─── Complexity ───` block giving time and space with a short reason,
  and states whether the space figure includes the required output.
- Each file runs the examples from the statement: a `main` in Java, a `require.main` guard in JS.
- Prefer the clearest correct solution.
- Guard against overflow where inputs can be large. Note that in Java `+`, `-` and `*` are exact
  modulo 2^32, so int arithmetic still lands on the right answer whenever that answer fits in an
  int; `Math.abs` is where a wrapped int actually becomes a wrong result, so that is where a
  `(long)` cast is load-bearing rather than decorative.

## License

Free to use for learning and reference.
