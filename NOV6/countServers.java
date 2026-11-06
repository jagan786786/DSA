// 2747. Count Zero Request Servers
// Medium
//
// You are given an integer n denoting the total number of servers and a 2D
// 0-indexed integer array logs, where logs[i] = [server_id, time] denotes that
// the server with id server_id received a request at time time.
//
// You are also given an integer x and a 0-indexed integer array queries.
//
// Return a 0-indexed integer array arr of length queries.length where arr[i]
// represents the number of servers that did not receive any requests during the
// time interval [queries[i] - x, queries[i]].
//
// Note that the time intervals are inclusive.
//
// Example 1:
// Input:  n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
// Output: [1,2]
// Explanation:
// For queries[0]: The servers with ids 1 and 2 get requests in the duration of
// [5, 10]. Hence, only server 3 gets zero requests.
// For queries[1]: Only the server with id 2 gets a request in duration of
// [6,11]. Hence, the servers with ids 1 and 3 are the only servers that do not
// receive any requests during that time period.
//
// Example 2:
// Input:  n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
// Output: [0,1]
// Explanation:
// For queries[0]: All servers get at least one request in the duration of [1, 3].
// For queries[1]: Only server with id 3 gets no request in the duration [2,4].

import java.util.*;

class Solution {

    // Offline sliding window. The queries are answered out of order: sorting
    // them by time means the window [queryTime - x, queryTime] only ever moves
    // forward, so each log enters it once and leaves it once. The original
    // index is carried alongside each query so the answers can be written back
    // into the caller's order.
    //
    // The map holds a request count per server rather than a boolean, because a
    // server can appear several times inside one window - the count is what
    // tells us when the *last* of its logs has slid out. Its size is therefore
    // the number of distinct servers currently active, and n minus that is the
    // number that saw nothing.
    //
    // Both loops use the right comparison for an inclusive interval: right
    // takes in everything with time <= queryTime, left drops everything with
    // time < start, so the window is exactly [start, queryTime].
    //
    // ─── Complexity ─────────────────────────────────────────────────────────
    // Let m = logs.length and q = queries.length.
    // Time:  O(m log m + q log q) - the two sorts dominate. The sweep itself is
    //                               O(m) in total, not O(m) per query: left and
    //                               right each advance at most m times across
    //                               the whole run, and each HashMap operation
    //                               is amortised O(1).
    // Space: O(m + q)             - O(m) for the sorted copy of logs that the
    //                               stream builds, plus the O(m) temporary that
    //                               TimSort needs to sort an array of
    //                               references, O(q) for sortedQueries, and
    //                               O(min(n, m)) for the map, which is bounded
    //                               by the number of distinct servers that
    //                               actually appear. n itself costs nothing.
    //                               Excluding the returned res, still O(m + q).
    //
    // Note the stream leaves the caller's logs array untouched, which an
    // in-place Arrays.sort(logs, ...) would not. That costs one extra O(m)
    // array; sorting in place would drop it but reorder the input.
    public static int[] countServers(int n, int[][] logs, int x, int[] queries) {
        logs = Arrays.stream(logs).sorted((a, b) -> Integer.compare(a[1], b[1])).toArray(int[][]::new);

        int[] res = new int[queries.length];

        int[][] sortedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        HashMap<Integer, Integer> serverRequestCount = new HashMap<>();
        int right = 0;
        int left = 0;

        for (int[] q : sortedQueries) {
            int queryTime = q[0];
            int queryIndex = q[1];

            int start = queryTime - x;

            while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                serverRequestCount.put(serverId, serverRequestCount.getOrDefault(serverId, 0) + 1);
                right++;
            }

            while (left < right && logs[left][1] < start) {
                int serverID = logs[left][0];
                serverRequestCount.put(serverID, serverRequestCount.get(serverID) - 1);
                if (serverRequestCount.get(serverID) == 0) {
                    serverRequestCount.remove(serverID);
                }
                left++;
            }
            res[queryIndex] = n - serverRequestCount.size();
        }

        return res;
    }
}

// Was `Class Main` - a capital C is not the Java keyword, which broke the whole
// file: javac reported "class, interface, enum, or record expected" and then
// misread main as a preview-feature unnamed class.
class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] logs = { { 1, 3 }, { 2, 6 }, { 1, 5 } };
        int x = 5;
        int[] queries = { 10, 11 };

        int[] result = Solution.countServers(n, logs, x, queries);
        System.out.println(Arrays.toString(result)); // [1, 2]

        int[] result2 = Solution.countServers(
                3, new int[][] { { 2, 4 }, { 2, 1 }, { 1, 2 }, { 3, 1 } }, 2, new int[] { 3, 4 });
        System.out.println(Arrays.toString(result2)); // [0, 1]
    }
}
