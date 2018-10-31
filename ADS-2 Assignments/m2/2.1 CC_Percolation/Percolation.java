/**
 * Class for percolation.
 */
class Percolation {
    /**
     * variable to keep count value.
     */
    private int cnt;
    /**
     * to take 2 dimentional array.
     */
    private int[][] grid;
    /**
     * to keep size.
     */
    private int size;
    /**
     * object for weighted addEdge class.
     */
    private Graph cd;
    /**
     * cc of type CC.
     */
    private CC cc;
    /**
     * Constructs the object.
     * @param      n  The size
     */
    Percolation(final int n) {
        grid = new int[n][n];
        cnt = 0;
        cd = new Graph((n * n) + 2);
        cc = new CC(cd);
        this.size = n;
    }
    /**
     * function to check whether the element has to be connected or not.
     * @param      row   The value of row
     * @param      col   The value of column
     * Time complexity  O(1).
     */
    void open(final int row, final int col) {
        grid[row][col] = 1;
        cnt++;
        if (row == 0) {
            cd.addEdge(0, component(row, col));
        }
        if (row == size - 1) {
            cd.addEdge((size * size) + 1, component(row, col));
        }
        if (row + 1 < size && grid[row][col] == 1) {
            cd.addEdge(
                component(row + 1, col), component(row, col));
        }
        if (row - 1 >= 0 && grid[row - 1][col] == 1) {
            cd.addEdge(
                component(row - 1, col), component(row, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
            cd.addEdge(component(row, col - 1), component(row, col));
        }
        if (col + 1 < size && grid[row][col + 1] == 1) {
            cd.addEdge(
                component(row, col + 1), component(row, col));
        }
    }
    /**
     * to get the component at the particular row and column.
     * @param      i     row index is given.
     * @param      j     column index is given
     * @return     return type is int
     * Time complexity  O(1).
     */
    int component(final int i, final int j) {
        return (i) * size + j;
    }
    /**
     * Determines if open.
     * @param      row   The row
     * @param      col   The col
     * @return     True if open, False otherwise.
     * Time complexity for this method is O(1).
     */
    boolean isOpen(final int row, final int col) {
        return grid[row][col] == 1;
    }
    /**
     * Determines if full.
     * @param      row   The row
     * @param      col   The col
     * @return     True if full, False otherwise.
     * Time complexity  O(1).
     */
    boolean isFull(final int row, final int col) {
        return grid[row][col] == 0;
    }
    /**
     * to get the number of open sites.
     * @return     integer is returned.
     * Time complexity  O(1).
     */
    int numberofopensites() {
        return cnt;
    }
    /**
     * function to check if the grid percolates or not.
     * @return     True if percolates, False otherwise.
     * Time complexity for this method is O(1).
     */
    boolean percolates() {
        cc = new CC(cd);
        return cc.connected(0, (size * size) + 1);
    }
}
