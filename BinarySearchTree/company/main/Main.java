package com.company.main;
import com.company.Trees.BinarySearchTree;

public class Main {
    public static void main(String[] args) {}
    public static BinarySearchTree<Integer> treeOne()
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(1);
        tree.insert(150);
        tree.insert(10);
        tree.insert(3);
        tree.insert(15);
        tree.insert(9);

        //              1
        //              \
        //              150
        //             /
        //          10
        //         /  \
        //      3     15
        //       \
        //        9
        return tree;

    }
    public static BinarySearchTree<Integer> treeTwo()
    {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(12);
        tree.insert(4);
        tree.insert(10);
        tree.insert(14);
        tree.insert(2);
        tree.insert(6);
        tree.insert(13);
        tree.insert(15);
        tree.insert(9);
        tree.insert(11);
        tree.insert(5);
        tree.insert(7);
        tree.insert(1);
        tree.insert(3);
        //               8
        //             /    \
        //          4         12
        //         /  \       /  \
        //      2     6      10     14
        //     / \    / \    / \     /  \
        //     1  3   5  7   9  11     13  15
        //
        return tree;
    }
}