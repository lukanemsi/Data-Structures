package com.company.Trees;

import com.company.tuple.Tuple;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
public class BinarySearchTree<T extends Comparable<T>>
{
    public Node<T> root;

    private static class Node<T extends Comparable<T>>
    {
        public T value;
        public Node<T> left, right, parent;

        public Node(T value) {
            this.value = value;
        }

        private boolean isLeaf() {
            return this.right == null && this.left == null;
        }
    }

    public void insert(T value) {
        if (root == null) {
            this.root = new Node<>(value);
            root.parent = null;
        }
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(value) < 0) {
                if (current.right == null) {
                    current.right = new Node<T>(value);
                    current.right.parent = current;
                } else
                    current = current.right;
            } else if (current.value.compareTo(value) > 0) {
                if (current.left == null) {
                    current.left = new Node<T>(value);
                    current.left.parent = current;
                } else
                    current = current.left;
            } else break;
        }
    }

    public boolean find(T value) {
        return findT(value).getFirst();
    }

    private Tuple<Boolean, Node<T>> findT(T value) {
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(value) == 0) {
                return new Tuple<>(true, current);
            }
            if (current.value.compareTo(value) < 0)
                current = current.right;
            else
                current = current.left;
        }
        return new Tuple<>(false, null);
    }

    public T succ(T value) {
        Tuple<Boolean, Node<T>> tuple = findT(value);
        if (!tuple.getFirst())
            return null;
        Node<T> subtree = tuple.getSecond();
        if (subtree.right != null)
            return min(subtree.right);
        Node<T> parrent = subtree.parent;
        while (parrent != null) {
            if (parrent.value.compareTo(subtree.value) > 0)
                return parrent.value;
            else
                parrent = parrent.parent;
        }
        return null;
    }

    private T max(Node<T> current) {

        while (current != null) {
            if (current.right == null)
                return current.value;

            current = current.right;
        }
        return null;
    }

    private T min(Node<T> current) {

        while (current != null) {
            if (current.left == null)
                return current.value;

            current = current.left;
        }
        return null;
    }

    public void delete(T value) {
        Tuple<Boolean, Node<T>> tuple = findT(value);
        if (!tuple.getFirst())
            return;
        Node<T> tree = tuple.getSecond();
        if (value.equals(root.value)) {
            if (root.isLeaf())
                root = null;
            else if (root.right == null)
                root = root.left;
            else if (root.left == null)
                root = root.right;
            else {
                T succValue = succ(root.value);
                delete(succValue);
                root.value = succValue;
            }
            return;
        }

        if (tree.isLeaf()) {
            if (tree.equals(tree.parent.left))
                tree.parent.left = null;
            else
                tree.parent.right = null;
        } else if (tree.left == null) {
            if (tree.equals(tree.parent.left))
                tree.parent.left = tree.right;
            else
                tree.parent.right = tree.right;
        } else if (tree.right == null) {
            if (tree.equals(tree.parent.left))
                tree.parent.left = tree.left;
            else
                tree.parent.right = tree.left;
        } else {
            T succesorNodeValue = succ(value);
            delete(succesorNodeValue);
            if (tree.equals(tree.parent.left))
                tree.parent.left.value = succesorNodeValue;
            else
                tree.parent.right.value = succesorNodeValue;

        }
    }


    public T min() {
        return min(root);
    }

    public T max() {
        return max(root);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null || node.isLeaf())
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int depth(T value) {
        Tuple<Boolean, Node<T>> tuple = findT(value);
        if (!tuple.getFirst())
            return -1;
        return depthT(tuple.getSecond().parent);
    }

    private int depthT(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }

    public List<T> getKthNodes(int k) {
        LinkedList<T> nodes = new LinkedList<T>();
        return getKthNodes(k, nodes, root);
    }

    private List<T> getKthNodes(int k, List<T> list, Node<T> node) {
        if (node == null)
            return list;
        if (k == 0)
            list.add(node.value);
        getKthNodes(k - 1, list, node.left);
        getKthNodes(k - 1, list, node.right);
        return list;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public static enum Traverse {
        /**
         * Breadth first search prints node sequence according to their level
         */
        BREADTH_FIRST,
        /**
         * Depth first search prints nodes accordingly (Root,Left,Right)
         */
        PRE_ORDER,
        /**
         * Depth first search prints nodes accordingly (Left,Root,Right)
         */
        IN_ORDER,
        /**
         * Depth first search prints nodes accordingly (Left,Right,Root)
         */
        POST_ORDER;
    }

    public void traverseTree(Traverse traverse) {
        switch (traverse) {
            case IN_ORDER -> {
                inOreder(root);
            }
            case PRE_ORDER -> {
                preOrder(root);
            }
            case POST_ORDER -> {
                postOrder(root);
            }
            case BREADTH_FIRST -> {
                breadthFirst();
            }
        }
        System.out.println("");
    }

    private void breadthFirst() {
        int depth = 0;
        int h = height();
        while (depth <= h) {
            List<T> nodes = getKthNodes(depth);
            nodes.forEach(n -> {
                System.out.print(n + " ");
            });
            depth++;
        }
    }

    private void inOreder(Node<T> node) {
        if (node == null)
            return;
        inOreder(node.left);
        System.out.print(node.value + " ");
        inOreder(node.right);
    }

    private void postOrder(Node<T> node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    private void preOrder(Node<T> node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public T getRoot() {
        return root.value;
    }
}
