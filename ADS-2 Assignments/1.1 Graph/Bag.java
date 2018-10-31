import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     *number of elements in bag.
     */
    private int n;
    /**
     *beginning of bag.
     */
    private Node first;
    /**
     *helper linked list class.
    */
    private class Node {
        /**
         *the variable to store.
         *item value
         */
        private Item item;
        /**
         *the element to reference the.
         *next item.
         */
        private Node next;
    }
    /**
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        n = 0;
    }
    /**
      * Is the BAG empty?
      * @return true if it is empty.
      */
    public boolean isEmpty() {
        return first == null;
    }
    /**
      * Return the number of items in the bag.
      * @return size of bag.
      */
    public int size() {
        return n;
    }
    /**
      * Add the item to the bag.
      *time complexity is O(1)
      * @param item to be added to bag.
      */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**
      * Return an iterator that iterates over the.
      *items in the bag.
      * @return iterator.
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
    *an iterator, doesn't implement remove().
     * since it's optional.
     *
    */
    private class ListIterator implements Iterator<Item> {
        /**
         *the temporory node.
         */
        private Node current = first;
        /**
         *the method is whether there is.
         *next element or not.
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**
         *the remove operation.
         */
        public void remove() {
            throw new UnsupportedOperationException();
         }
         /**
          *the next method returns an item.
          * @return item in bag.
          */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
