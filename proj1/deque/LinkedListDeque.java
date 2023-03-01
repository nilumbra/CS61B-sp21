package deque;

import java.lang.Iterable;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node<T> head;
    private int size;
    private class Node<T> {
        public T ele;
        public Node prev;
        public Node next;
        Node () {}
        Node (T o) {
            ele = o;
        }
    }
    public LinkedListDeque () {
        head = new Node<>();
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
        Node<T> first = head.next;
        Node<T> new_node = new Node<>(item);

        // 2 way link btw. the sentinel and new node
        head.next = new_node;
        new_node.prev = head;

        // 2 way link btw. first and new node
        new_node.next = first;
        first.prev = new_node;

        size++;
    }

    /**
     * Add an item to the back of deque
     *
     * @param item
     */
    @Override
    public void addLast(T item) {
        Node<T> last = head.prev;
        Node<T> new_node = new Node<>(item);

        // 2 way link btw. the sentinel and new node
        head.prev = new_node;
        new_node.next = head;

        // 2 way link btw. last and new node
        new_node.prev = last;
        last.next = new_node;

        size++;
    }

    /**
     * @return boolean, true if deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
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
        Node<T> ptr = head;
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
        Node<T> first = head.next;
        if (first == head) return null;

        Node<T> second = first.next;

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
        Node<T> last = head.prev;
        if (last == head) return null;

        Node<T> penultimate = last.prev;

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
        if (index < 0 || index >= size) return null;

        Node<T> ptr = head;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.ele;
    }

    /**
     * @return
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
