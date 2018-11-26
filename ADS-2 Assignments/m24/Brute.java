public class Brute extends SearchAlgo{
    String pat = null;

    Brute(String pattern) {
        this.pat = pattern;
    }


    // return offset of first match or N if no match
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == m) return i - m;    // found
        else        return n;        // not found
    }
    
}