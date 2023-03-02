package deque;

import java.util.Iterator;

/**
 * A deque that uses arrays as the core data structure.
 * APIs followed by their run time are as follows:
 * - add(x)         O(1)
 * - remove         O(1)
 * - get(i)         O(1)
 * - size()         O(1)
 *
 * TODO:
 * [] First, implement a fixed sized deque with circular array trick,
 *    Figuring out how to manipulate left/right pointers
 *    which indicates the head and tail of the deque to create
 *    the facade of sequence
 * [] Redesign with dynamic resizing in mind. figure out how to resize
 *   the deque while keeping the left/right pointer
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    public static final int RESIZE_FACTOR = 2;
    public static final double USAGE_THRESHOLD = 0.25;
    private static final int startSize = 8;
    private T[] elements;

    private int left; // point to the first element of deque
    private int right; // point to the last element of deque


    public ArrayDeque() {
        elements = (T[]) new Object[startSize];
        left = -1;
        right = -1;
    }

    /**
     * Determine if an imaginary removal of an element from the array
     * causes the array to have a usage rate below USAGE_THRESHOLD
     * @returnb boolean, sparse or not
     */
    private boolean isSparse() {
        if (capacity() <= 16) return false; // consider small arrays are always dense
        return ((size() - 1) / (double)capacity()) < USAGE_THRESHOLD;
    }

    public int capacity() {
        return elements.length;
    }
    /**
     * TODO:
     * - Figure out how to handle the resizing
     * We are going to implement this with circular array
     *
     * Resize the array to capacity specified WITHOUT modifying any
     * element in the array.
     * A new array will be created an it starts with the first element,
     * that is, the element `left` points to in the original array
     * @param capacity
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
//        System.out.println(capacity);

        if (left < right) {
            System.arraycopy(elements, left,  a, 0,  size());
        } else {
            int first_half_len = capacity() - left;
            System.arraycopy(elements, left, a, 0, first_half_len);
            System.arraycopy(elements, 0, a, 0, right + 1);
        }
        // before reassigning the temp array back to instance variable array
        // we need to reset the left and right as well

        int _size = size();
        left = 0;
        right = left + _size - 1;

        elements = a;
    }

    /**
     * Add an item to the front of queue
     *
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if(isFull()) {
            resize(capacity() * RESIZE_FACTOR);
        }

        if (left == -1) { // edge case: for newly initialized array
            left = 0;
            right = 0;
        } else {
            left = left == 0 ? capacity() - 1 : left - 1;
        }

        elements[left] = item;
    }

    /**
     * Add an item to the back of deque
     *
     * @param item
     */
    @Override
    public void addLast(T item) {
        if(isFull()) {
            resize(capacity() * RESIZE_FACTOR);
        }

        if (left == -1) {
            left = 0;
            right = 0;
        } else {
            right = (right + 1) == capacity() ? 0 : right + 1;
        }

        elements[right] = item;
    }

    /**
     * @return boolean, true if deque is full, false otherwise
     */
    public boolean isFull() {
        return size() == capacity();
    }

    /**
     * @return int, the number of elements in the deque
     */
    @Override
    public int size() {
//        System.out.printf("%d %d\n", left, right);
        if (left == right && left == -1) return 0;
        if (left <= right) return right - left + 1;

        return right - left + capacity() + 1;
    }

    /**
     * Prints the items in the deque from first to last, separated by a
     * space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        if (size() > 0) {
            int _r = right > left ? right : right + capacity();
            for (int i = left; i <= _r; i++) {
                System.out.print(elements[i % capacity()]);
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    /**
     * Delete first element and return
     *
     * @return - first element of type T, or null if not exists
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        // If the array gets too small after this removal, first downsize it
        if (this.isSparse()) {
            resize(capacity() / 2);
        }

        T removed = elements[left];

        // remove the item, nulling out the deleted item to prevent loitering
        elements[left] = null;
        if (size() == 1) {
            left = -1;
            right = -1;
        } else {
            left = left == capacity() - 1 ? 0 : left + 1;
        }

//        System.out.printf("%d %d\n", left, right);
        return removed;
    }

    /**
     * Delete last element and return
     *
     * @return - last element of type T, or null if not exist
     */
    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        // If the array gets too small after this removal, first downsize it
        if (isSparse()) {
            resize(capacity() / 2);
        }

        T removed = elements[right];
        elements[right] = null;

        if (size() == 1) {
            left = -1;
            right = -1;
        } else {
            right = (right == 0) ? capacity() - 1 : right - 1;
        }

        return removed;
    }

    /**
     *
     * @return double, size : capacity ratio
     */
    public double usageRate() {
        return size() / (double) capacity();
    }

    /**
     * Retrieve an element at index position
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= capacity()) return null;
        return elements[(left + index) % capacity()];
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {
        int offset;

        public ArrayDequeIterator () {
            offset = 0;
        }
        @Override
        public boolean hasNext() {
            return offset < size();
        }

        @Override
        public T next() {
            return (T) get(offset++);
        }
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof ArrayDeque<?>) {
            Deque<?> other_arrdeq = (ArrayDeque<?>) o;
            if (other_arrdeq.size() != this.size()) return false;

            Iterator<T> this_iter = this.iterator();
            Iterator<?> other_iter = other_arrdeq.iterator();

            while (this_iter.hasNext() && other_iter.hasNext()) {
                if (this_iter.next() != other_iter.next()) return false;
            }
        } else {
            return false; // not the right type
        }

        return true; // every each pair equals
    }
}
