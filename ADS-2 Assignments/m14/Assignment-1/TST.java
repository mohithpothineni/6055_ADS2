/**
 * Ternary search tries.
 *
 * @param    <Value>  The value
 */
class TST<Value> {
    /**
     * The size of the tries.
     */
    private int size;
    /**
     * Root Node of the trie.
     */
    private Node<Value> root;
    /**
     * Node class.
     *
     * @param      <Value>  The value
     */
    private class Node<Value> {
        /**
         * Character variable.
         */
        private char c;
        /**
         * Right, left and mid Node.
         */
        private Node<Value> left, mid, right;
        /**
         * The value to the corresponding Node.
         */
        private Value val;
    }
    /**
     * Default empty constructor.
     */
    TST() {
    }
    /**
     * method that Returns the size of tries.
     *
     * @return returns the size of trie.
     */
    public int size() {
        return size;
    }
    /**
     * The method returns whether the key is present or not.
     * @param      key   The key
     *
     * @return true or false.
     */
    public boolean contains(final String key) {
        return get(key) != null;
    }
    /**
     * Time complexity is O(L + logN).
     * The method to get the value of given Key.
     *
     * @param key The key.
     * L is the length of string and N is the size of tries.
     * @return the value.
     */
    public Value get(final String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }
    /**
     * The private method for get method.
     * Time complexity is O(L + logN)
     * L is the length of string and N is the size of tries.
     *
     * @param x    node
     * @param key   The key
     * @param d index of string
     *
     * @return  the value of string.
     */
    private Node<Value> get(final Node<Value> x,
                            final String key, final int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }
    /**
     * This method is to insert the string.
     * Time complexity is O(L + logN)
     * L is the length of string and N is the size of tries.
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final String key,
                    final Value val) {
        if (!contains(key)) {
            size++;
        }
        root = put(root, key, val, 0);
    }
    /**
     * The method is to insert the string.
     * Time complexity is O(L + logN)
     * L is the length of string and N is the size of tries.
     *
     * @param      temp   node
     * @param      key   The key
     * @param      val   The value
     * @param      d index of the string
     *
     * @return root  node.
     */
    private Node<Value> put(final Node<Value> temp,
                            final String key, final Value val, final int d) {
        Node<Value> x = temp;
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) {
            x.left  = put(x.left,  key, val, d);
        } else if (c > x.c)   {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid   = put(x.mid,   key, val, d + 1);
        } else   {
            x.val   = val;
        }
        return x;
    }
    /**
     * This method returns keys with prefix.
     * Time complexity is O(L + logN).
     * L is the length of string and N is the size of tries.
     *
     * @param      prefix  The prefix.
     *
     * @return queue.
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }
    /**
     * collects all the elements with.
     * prefix and store in a queue
     * Time complexity is O(L + logN)
     * L is the length of string and N is the size of tries.
     * @param      x  node
     * @param      prefix  The prefix
     * @param      queue   The queue
     */
    private void collect(final Node<Value> x,
                         final StringBuilder prefix,
                          final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
}
