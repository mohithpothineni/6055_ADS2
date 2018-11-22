/**
 * Class for quick 3 string.
 */
class Quick3string {
    /**
     * cutoff for insertion sort.
     */
    private static final int CUTOFF =  15;

    /**
     * Rearranges the array of strings in ascending order.
     * The time complexity is O(W*NlogN).
     *
     * @param arr String array which is going to be sort.
     */
    public void sort(final String[] arr) {
        sort(arr, 0, arr.length - 1, 0);
    }

    /**
     * return the dth character of s, -1 if d = length of s.
     * The time complexity is O(1).
     *
     * @param str String.
     * @param index index.
     *
     * @return ascii value of that character.
     */
    private int charAt(final String str, final int index) {
        assert index >= 0 && index <= str.length();
        if (index == str.length()) {
            return -1;
        }
        return str.charAt(index);
    }

    /**
     * 3-way string quicksort a[low..high].
     * starting at dth character.
     * The time complexity is O(W*NlogN).
     *
     * @param arr String array.
     * @param low The lower.
     * @param high The higher.
     * @param d index.
     */
    private void sort(final String[] arr, final int low,
        final int high, final int d) {

        // cutoff to insertion sort for small subarrays
        if (high <= low + CUTOFF) {
            insertion(arr, low, high, d);
            return;
        }

        int lt = low, gt = high;
        int v = charAt(arr[low], d);
        int i = low + 1;
        while (i <= gt) {
            int t = charAt(arr[i], d);
            if (t < v)  {
                exch(arr, lt++, i++);
            } else if (t > v) {
                exch(arr, i, gt--);
            } else {
                i++;
            }
        }

        // arr[low..lt-1] < v = arr[lt..gt] < arr[gt+1..high].
        sort(arr, low, lt - 1, d);
        if (v >= 0) {
            sort(arr, lt, gt, d + 1);
        }
        sort(arr, gt + 1, high, d);
    }

    /**
     * sort from a[low] to a[high], starting at the dth character.
     * The time complexity is O(N^2).
     *
     *
     * @param a String array.
     * @param low The lower.
     * @param high The higher.
     * @param d index.
     */
    private void insertion(final String[] a,
        final int low, final int high, final int d) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * exchange a[i] and a[j].
     * The time complexity is O(1).
     *
     * @param arr String array.
     * @param i index1.
     * @param j index2.
     */
    private void exch(final String[] arr, final int i, final int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * less function is used to compare two strings
     * and check if str1 is less than str2.
     * The time complexity is O(W).
     *
     * @param str1 String str1.
     * @param str2 String str2
     * @param d index.
     *
     * @return boolean true or flase.
     */
    private boolean less(final String str1,
        final String str2, final int d) {
        //assert str1.substring(0, d).equals(str2.substring(0, d));
        for (int i = d; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) < str2.charAt(i))  {
                return true;
            }
            if (str1.charAt(i) > str2.charAt(i)) {
                return false;
            }
        }
        return str1.length() < str2.length();
    }
}
