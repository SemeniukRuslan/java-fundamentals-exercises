package com.bobocode.cs;

import java.util.Objects;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    // 0 or 1 elements in the queue
    public void add(T element) {
        Objects.checkIndex(0, size + 1);
        Node<T> node = new Node<>(element);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++; // todo: implement this method
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    // 0 or 1 or 2 elements in the queue
    public T poll() {
        if (first == null) {
            return null;
        }
        T elem = first.element;
        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
        }
        size--;
        return elem;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return first == null;
    }
}
