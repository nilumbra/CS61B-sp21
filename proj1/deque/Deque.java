package deque;

import java.util.Iterator;

/**
 * A sequence container with dynamic sizes that can be expanded or contracted on both ends
 * @param <T>
 */
public interface Deque<T> {
    /**
     * Add an item to the front of queue
     * @param item
     */
    void addFirst(T item);

    /**
     * Add an item to the back of deque
     * @param item
     */
    void addLast(T item);

    /**
     * @return boolean, true if deque is empty, false otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return int, the number of elements in the deque
     */
    int size();

    /**
     *  Prints the items in the deque from first to last, separated by a
     *  space.
     *  Once all the items have been printed, print out a new line.
     */
    void printDeque();

    /**
     * Delete first element and return
     * @return - first element of type T, or null if not exists
     */
    T removeFirst();

    /**
     * Delete last element and return
     * @return - last element of type T, or null if not exist
     */
    T removeLast();

    /**
     * Retrieve an element at index position
     * @param index
     * @return
     */
    T get(int index);

    Iterator<T> iterator();

    /**
     * Returns whether the parameter o is equal to the Deque. o is
     * considered equal if it is a Deque and if it contains the same contents
     * (as governed by the generic T’s equals method) in the same order.
     *
     * @param o
     * @return true if o is equal to the Deque. O.W., false
     */
    boolean equals(Object o);
}
