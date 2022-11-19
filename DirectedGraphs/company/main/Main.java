package com.company.main;
import com.company.Trees.BinarySearchTree;
import com.company.graphs.DirectedGraph;

import java.util.HashSet;

public class Main
{

    public static void main(String[] args)
    {
    }

    public static DirectedGraph<Integer> graphOne()
    {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);

        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,5);
        graph.addEdge(5,3);
        graph.addEdge(5,6);
        graph.addEdge(6,2);
        graph.addEdge(4,6);
        graph.addEdge(3,4);
        /*
             |---1 > 2  <--------|
             |      \/       |  |
             |-> 3 < 5 > 6---|  |
                 \/             |
                 4 -------------|
         */
        return graph;
    }
    public static DirectedGraph<Integer> graphTwo()
    {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addNode(8);
        graph.addNode(9);


        graph.addEdge(1,2);
        graph.addEdge(1,6);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(5,6);
        graph.addEdge(6,7);
        graph.addEdge(7,8);
        graph.addEdge(7,9);
        graph.addEdge(8,9);
        graph.addEdge(8,4);
        /*
            1 > 2 > 3 > 4 <--------|
            |       \/             |
            |        5 >  6 > 7 > 8  > 9
            |------------/\   |        /\
                              |--------|
         */
        return graph;
    }
}