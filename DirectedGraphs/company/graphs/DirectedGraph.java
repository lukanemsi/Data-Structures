package com.company.graphs;
import java.util.*;

public class DirectedGraph<T>
{
    Map<T,Node<T>> nodeGraph = new HashMap<>();
    Map<Node<T>,Set<Node<T>>> connections = new HashMap<>();

    private static class Node<T>
    {
        public T label;
        public Node(T label)
        {
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return label.equals(node.label);
        }
        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public String toString() {
            return label.toString();
        }
    }
    public void addNode(T value)
    {
        Node<T> node = new Node<>(value);
        nodeGraph.putIfAbsent(value,node);
        connections.putIfAbsent(node,new HashSet<>());
    }
    public void removeNode(T value)
    {
        Node<T> node = nodeGraph.get(value);
        if(node == null)
            return;
        nodeGraph.remove(value);
        connections.remove(node);
        for(var i : connections.entrySet())
        {
            i.getValue().remove(node);
        }
    }
    public void addEdge(T from,T to)
    {
        Node<T> fromNode = nodeGraph.get(from);
        Node<T> toNode = nodeGraph.get(to);
        if(fromNode == null || toNode == null)
            throw new IllegalArgumentException("no such values found");
        connections.get(fromNode).add(toNode);
    }
    public void removeEdge(T from,T to)
    {
        Node<T> fromNode = nodeGraph.get(from);
        Node<T> toNode = nodeGraph.get(to);
        if(fromNode == null || toNode == null)
            return;
        connections.get(fromNode).remove(toNode);
    }
    public Node<T> nodeWithMaxEdges()
    {
        Node<T> max = null;
        int neighbors = 0;
        for(var i : connections.entrySet())
        {
            if(i.getValue().size() > neighbors)
            {
                neighbors = i.getValue().size();
                max = i.getKey();
            }
        }
        return max;
    }
    public enum Traverse
    {
        BREADTH_FIRST,DEPTH_FIRST;
    }
    public void traverseGraph(Traverse traverse)
    {
        Node<T> starting = nodeWithMaxEdges();
        switch (traverse)
        {
            case DEPTH_FIRST -> {depthFirst(starting,new HashSet<>());}
            case BREADTH_FIRST -> {breadthFirst(starting,new HashSet<>());}
        }
        System.out.println("");
    }
    private void depthFirst(Node<T> current,HashSet<Node<T>> visited)
    {
        if(connections.get(current).isEmpty())
            return;
        if(nodeGraph.size() == visited.size())
            return;
        if(visited.contains(current))
            return;
        visited.add(current);
        System.out.print(current + " ");
        for(var i : connections.get(current))
        {
            depthFirst(i,visited);
        }
    }
    private void breadthFirst(Node<T> current,HashSet<Node<T>> visited)
    {
        if(connections.get(current).isEmpty())
            return;
        if(current == null)
            return;
        if(visited.size() == nodeGraph.size())
            return;
        if(!visited.contains(current)){
            System.out.print(current + " ");
            visited.add(current);
        }
        for (var i : connections.get(current))
        {
            if(!visited.contains(i)){
            System.out.print(i + " ");
            visited.add(i);
            }
        }
        breadthFirst(connections.get(current).stream().toList().stream().findFirst().orElse(null),visited);
    }

    public boolean isCyclic()
    {
        Set<Node<T>> allNodes = new HashSet<>(nodeGraph.values());
        Set<Node<T>> visiting = new HashSet<>();
        Set<Node<T>> visited = new HashSet<>();
        while (!allNodes.isEmpty())
        {
            Node<T> current = allNodes.iterator().next();
            if(isCyclic(current,allNodes,visiting,visited))
                return true;
        }
        return false;
    }
    private boolean isCyclic(Node<T> node,Set<Node<T>> allNodes,Set<Node<T>> visiting, Set<Node<T>> visited)
    {
        allNodes.remove(node);
        visiting.add(node);
        for (var i : connections.get(node))
        {
            if(visited.contains(i))
                continue;
            if(visiting.contains(i))
                return true;
            if(isCyclic(i,allNodes,visiting,visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }

    @Override
    public String toString()
    {
        StringJoiner joiner = new StringJoiner("\n");
        for(var i : connections.entrySet())
        {
            if(!i.getValue().isEmpty())
                joiner.add(i.getKey() + " is connected to: " + i.getValue());
        }
        return joiner.toString();
    }
}
