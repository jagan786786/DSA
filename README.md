# DSA

A collection of data structures and algorithms solutions written in Java, maintained as daily
practice for coding interviews.

## Structure

Solutions are grouped into folders by the day they were solved, with one file per problem:

```
DSA/
├── NOV1/
│   └── minMoves.java
├── NOV2/
│   └── ...
└── README.md
```

Every file is standalone and follows the same shape:

```java
// Problem statement
//
// Example 1:
// Input:  ...
// Output: ...

class Solution {
    public ReturnType methodName(Params params) {
        // solution
    }

    public static void main(String[] args) {
        // sample runs
    }
}
```

## Getting started

Clone the repository:

```bash
git clone https://github.com/jagan786786/DSA.git
cd DSA
```

Compile and run any solution:

```bash
javac NOV1/minMoves.java
java -cp NOV1 Solution
```

Note that the class inside each file is named `Solution`, so `javac` takes the file path while
`java` takes the class name.

Requires a JDK 8 or newer.

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
- Each file includes a `main` method that runs the examples from the statement.
- Prefer the clearest correct solution, then note the time and space complexity in a comment.
- Guard against overflow where inputs can be large.

## License

Free to use for learning and reference.
