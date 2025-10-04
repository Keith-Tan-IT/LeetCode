# Tip —  * Problem context: Graph traversal problems using adjacency matrices or lists

/**
 * TIP
 * Problem context: Graph traversal problems using adjacency matrices or lists
 *
 * Summary:
 * - `int[][] isConnected` → 2D array; use `isConnected.length` to get number of rows.
 * - `List<List<Integer>> rooms` → list of lists; use `rooms.size()` to get number of rooms.
 * - For 1D array: `int[] array` → `array.length` gives size.
 * - For a list: `List<Integer> list` → `list.size()` gives number of elements.
 *
 * Pitfalls:
 * - `.length` for arrays, `.size()` for lists — don't confuse them.
 * - Accessing out-of-bound indices will throw exceptions.
 * - Initializing 2D arrays vs list-of-lists differs:
 *   - `new int[n][m]` → rows and columns fixed
 *   - `List<List<Integer>> rooms = new ArrayList<>()` → flexible size, must init inner lists
 *
 * Example:
 * int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
 * System.out.println(isConnected.length); // 3
 * List<List<Integer>> rooms = new ArrayList<>();
 * rooms.add(Arrays.asList(1,0));
 * System.out.println(rooms.size()); // 1
 *
 * Key points:
 * - Arrays: fixed size, use `.length`.
 * - Lists: dynamic size, use `.size()`.
 * - Common in graph problems: adjacency matrix (`isConnected`) or adjacency list (`rooms`).
 */

