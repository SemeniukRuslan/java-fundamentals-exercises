package com.bobocode.cs;

import java.util.Objects;
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
        Node<T> left;
        Node<T> right;

        private Node(T element) {
            this.element = element;
        }

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> root;
    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> bst = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(bst::insert);
        return bst;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        boolean isInserted = insertElement(element);
        if (isInserted) {
            size++;
        }
        return isInserted;
    }

    boolean insertElement(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            return true;
        } else {
            return insertIntoSubTree(root, element);
        }
    }

    private boolean insertIntoSubTree(Node<T> subTreeRoot, T element) {
        if (subTreeRoot.element.compareTo(element) > 0) {
            return insertIntoLeftSubtree(subTreeRoot, element);
        } else if (subTreeRoot.element.compareTo(element) < 0) {
            return insertIntoRightSubtree(subTreeRoot, element);
        } else {
            return false;
        }
    }

    private boolean insertIntoLeftSubtree(Node<T> node, T element) {
        if (node.left != null) {
            return insertIntoSubTree(node.left, element);
        } else {
            node.left = Node.valueOf(element);
            return true;
        }
    }

    private boolean insertIntoRightSubtree(Node<T> node, T element) {
        if (node.right != null) {
            return insertIntoSubTree(node.right, element);
        } else {
            node.right = Node.valueOf(element);
            return true;
        }
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return findChildNodeByElement(root, element) != null;
    }

    private Node<T> findChildNodeByElement(Node<T> node, T element) {
        if (node == null) {
            return null;
        } else if (node.element.compareTo(element) > 0) {
            return findChildNodeByElement(node.left, element);
        } else if (node.element.compareTo(element) < 0) {
            return findChildNodeByElement(node.right, element);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(node.left), depth(node.right));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.element);
            inOrderTraversal(node.right, consumer);
        }
    }
}
