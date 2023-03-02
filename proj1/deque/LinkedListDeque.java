package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node head;
    private int size;
    private class Node {
        private T ele;
        private Node prev;
        private Node next;
        Node() {}
        Node(T o) {
            ele = o;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) { return false; }
            if (o == this) { return true; }
            if (o instanceof LinkedListDeque<?>.Node) {
                return ((LinkedListDeque<?>.Node) o).ele == this.ele;
            }
            return false;
        }

        /**
         * Help function for outer class method of the same name
         * @param index
         * @return
         */
        public T getRecursive(int index) {
            if (index < 0 || index >= size) { return null; }
            else if (index == 0) { return this.ele; }
            else if (this.next == head) { return null; }
            else { return this.next.getRecursive(index - 1); }
        }
    }
    public LinkedListDeque() {
        head = new Node();
        head.next = head; // set up a sentinal for circular DLList
        head.prev = head;
        size = 0;
    }

    /**
     * Add an item to the front of queue
     *
     * @param item
     */
    @Override
    public void addFirst(T item) {
        // Get reference to the curr first node in the list
        Node first = head.next;
        Node newNode = new Node(item);

        // 2 way link btw. the sentinel and new node
        head.next = newNode;
        newNode.prev = head;

        // 2 way link btw. first and new node
        newNode.next = first;
        first.prev = newNode;

        size++;
    }

    /**
     * Add an item to the back of deque
     *
     * @param item
     */
    @Override
    public void addLast(T item) {
        Node last = head.prev;
        Node newNode = new Node(item);

        // 2 way link btw. the sentinel and new node
        head.prev = newNode;
        newNode.next = head;

        // 2 way link btw. last and new node
        newNode.prev = last;
        last.next = newNode;

        size++;
    }


    /**
     * @return int, the number of elements in the deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a
     * space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        Node ptr = head;
        for (int i = 0; i < size; i++) {
            head = head.next;
            System.out.print(head.ele);
            System.out.print(' ');
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
        Node first = head.next;
        if (first == head) { return null; }

        Node second = first.next;

        // 2-way link between second and head
        second.prev = head;
        head.next = second;

        size--;
        return first.ele;
    }

    /**
     * Delete last element and return
     *
     * @return - last element of type T, or null if not exist
     */
    @Override
    public T removeLast() {
        Node last = head.prev;
        if (last == head) { return null; }

        Node penultimate = last.prev;

        // 2-way link between second and head
        penultimate.next = head;
        head.prev = penultimate;

        size--;
        return last.ele;
    }

    /**
     * Retrieve an element at index position
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { return null; }

        Node ptr = head;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.ele;
    }

    /**
     * Same as get(), but use recursion
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        return head.next.getRecursive(index);
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {
        LinkedListDeque<T>.Node ptr;
        public LinkedListDequeIterator() {
            ptr =  (LinkedListDeque<T>.Node) head;
        }

        public boolean hasNext() {
            return ptr.next != head;
        }
        public T next() {
            //T returnEle = ptr.ele;
            ptr = ptr.next;
            return ptr.ele;
        }
    }

    /**
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof LinkedListDeque<?>) {
            LinkedListDeque<?> other_list = (LinkedListDeque<?>) o;
            if (other_list.size() != this.size()) return false;
            Iterator<?> this_iter = this.iterator();
            Iterator<?> other_iter = other_list.iterator();
            while (other_iter.hasNext() && this_iter.hasNext()) {
                if (other_iter.next() != this_iter.next()) {
                    return false;
                }
            }
        } else {
            return false; // not the right type
        }

        return true; // if every pair of node equals
    }
}
