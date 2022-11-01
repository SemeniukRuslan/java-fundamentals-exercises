package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private static class Node<T> {
        T element;
        Node<T> right;
        Node<T> left;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> root;
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tre = new RecursiveBinarySearchTree<>();
        for (T ele : elements) {
            tre.insert(ele);
        }
        return tre;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            return true;
        } else {
            return insert(root, element);
        }
    }

    private boolean insert(Node<T> current, T element) {
        if (element.compareTo(current.element) < 0) {
            if (current.left == null) {
                current.left = new RecursiveBinarySearchTree.Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.left, element);
            }
        } else if (element.compareTo(current.element) > 0) {
            if (current.right == null) {
                current.right = new RecursiveBinarySearchTree.Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.right, element);
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> current, T element) {
        if (root == null) {
            return false;
        } else if (current.element.compareTo(element) > 0) {
            return contains(current.right, element);
        } else if (current.element.compareTo(element) < 0) {
            return contains(current.left, element);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? (depth(root) - 1) : 0;
    }

    private int depth(RecursiveBinarySearchTree.Node<T> current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(current.left), depth(current.right));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> current, Consumer<T> consumer) {
        if (current != null) {
            inOrderTraversal(current.left, consumer);
            consumer.accept(current.element);
            inOrderTraversal(current.right, consumer);
        }
    }
}
