import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    // Don't modify this method.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.
    public static String[] toReadFile(String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] data = toReadFile(file);
        for (String content : data) {
            content = content.toLowerCase();
            if (st.contains(content)) {
                st.put(content, st.get(content) + 1);
            } else {
                st.put(content, 1);
            }
        }
        return st;
    }

}

class T9 {

    TST<Integer> tree = null;

    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        tree = new TST<Integer>();
        for (String key : st.keys()) {
            tree.put(key, st.get(key));
        }

    }

    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        Iterable<String> words = tree.keysWithPrefix(prefix);
        return words;
    }

    public Iterable<String> potentialWords(String t9Signature) {
        // your code goes here
        TreeMap<String, ArrayList<String>> binds = new TreeMap<String, ArrayList<String>>();
        ArrayList<String> key2 = new ArrayList<String>();
        key2.add("a");
        key2.add("b");
        key2.add("c");
        binds.put("2", key2);
        ArrayList<String> key3 = new ArrayList<String>();
        key3.add("d");
        key3.add("e");
        key3.add("f");
        binds.put("3", key3);
        ArrayList<String> key4 = new ArrayList<String>();
        key4.add("g");
        key4.add("h");
        key4.add("i");
        binds.put("4", key4);
        ArrayList<String> key5 = new ArrayList<String>();
        key5.add("j");
        key5.add("k");
        key5.add("l");
        binds.put("5", key5);
        ArrayList<String> key6 = new ArrayList<String>();
        key6.add("m");
        key6.add("n");
        key6.add("o");
        binds.put("6", key6);
        ArrayList<String> key7 = new ArrayList<String>();
        key7.add("p");
        key7.add("q");
        key7.add("r");
        key7.add("s");
        binds.put("7", key7);
        ArrayList<String> key8 = new ArrayList<String>();
        key8.add("t");
        key8.add("u");
        key8.add("v");
        binds.put("8", key8);
        ArrayList<String> key9 = new ArrayList<String>();
        key9.add("w");
        key9.add("x");
        key9.add("y");
        key9.add("z");
        binds.put("9", key9);

        
        ArrayList<String> finalresult = new ArrayList<String>();
        finalresult.add("not yet implemented");
        for (int i = 0; i < t9Signature.length()-1; i++) {
            String tmp = "";
            for (int j = i + 1; j < t9Signature.length(); j++) {

            }

            //finalresult.add(tmp);    
        }

        return finalresult;
    }

    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here

        TreeMap<Integer, String> tm = new TreeMap<Integer, String>();
        int count = 0;
        for (String word : words) {
            //System.out.println(word);
            tm.put(tree.get(word), word);
            count++;
        }


        //System.out.println(tm);
        //System.out.println(tm.keySet());

        String[] res = new String[count];
        int i = 0;
        for (Integer key : tm.keySet()) {
            res[i++] = tm.get(key);
        }


        int diff = res.length - k;

        String[] res2 = new String[k];
        int ind = 0;
        for (int t = diff; t < res.length; t++, ind++) {
            //System.out.println(t);
            res2[ind] = res[t];
        }

        Arrays.sort(res2);

        ArrayList<String> result = new ArrayList<String>();

        for (String wrd : res2) {
            result.add(wrd);
        }


        return result;
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
