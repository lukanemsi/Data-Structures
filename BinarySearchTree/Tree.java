import java.util.*;

public class Tree
{
    private Node root;
    private StringJoiner joiner;
    private class Node
    {
        int value;
        Node left;
        Node right;
        public Node(int value)
        {
            this.value = value;
        }
        public boolean isLeaf()
        {
            return left == null && right == null;
        }
        @Override
        public String toString() {
            return "Node = " + value;
        }
    }
    /**
     * Tree traverse Algorithms
     * prints Node value in sequence
     * */
    public enum Traverse
    {
        /**
         * Breadth first search prints node sequence according to their level
         * */
        BREADTH_FIRST,
        /**
         * Depth first search prints nodes accordingly (Root,Left,Right)
         * */
        PRE_ORDER,
        /**
         * Depth first search prints nodes accordingly (Left,Root,Right)
         * */
        IN_ORDER,
        /**
         * Depth first search prints nodes accordingly (Left,Right,Root)
         * */
        POST_ORDER;
    }

    /**
     * Adds value to tree using
     * Binary search tree algorithm
     * */
    public void insert(int value)
    {
        if(root == null)
        {
            root = new Node(value);
            return;
        }
        Node current = root;

        while(true)
        {
            if(value >= current.value)
            {
                if(current.right == null) {
                    current.right = new Node(value);
                    return;
                }
                current = current.right;
            }
            if(value < current.value)
            {
                if(current.left == null) {
                    current.left = new Node(value);
                    return;
                }
                current = current.left;
            }
        }

    }

    /**
     * @return true if value was found in tree false otherwise
     * */
    public boolean find(int value)
    {
        Node current = root;
        while(true)
        {
            if(current == null)
                return false;
            if(current.value == value)
                return true;
            if(value > current.value)
                current = current.right;
            else
                current = current.left;
        }
    }
    /**
     * @return int value of the longest path from root to leaf
     * */
    public int height()
    {
        if(root == null)
            return -1;
        return height(root) - 1;
    }

    private int height(Node root)
    {
        if(root == null || root.isLeaf())
            return 0;
        return 1 + Math.max(height(root.left),height(root.right));
    }
    public void traverseTree(Traverse traverse)
    {
        joiner = new StringJoiner(", ","[","]");
        if(traverse == Traverse.BREADTH_FIRST)
        {
            traverseBreadthFirst();
            return;
        }
       else if(traverse == Traverse.IN_ORDER)
            traverseInOrder(root);
        else if(traverse == Traverse.POST_ORDER)
            traversePostOrder(root);
        else
            traversePreOrder(root);
        System.out.println(joiner);
    }
    private void traverseBreadthFirst()
    {
        List<Integer> list = new ArrayList<Integer>();
        traverseBreadthFirst(height(),0,list);
        System.out.println(list);
    }
    private void traverseBreadthFirst(int height,int level,List<Integer> list)
    {
        list.addAll(getKthNodes(level));
        if(height == level-1)
            return;
        traverseBreadthFirst(height,level+1,list);
    }
    private void traversePostOrder(Node root)
    {
        if(root == null)
            return;
        traversePostOrder(root.left);
        traversePostOrder(root.right);
        joiner.add(String.valueOf(root.value));

    }
    private void traverseInOrder(Node root)
    {
        if(root == null)
            return;
        traverseInOrder(root.left);
        joiner.add(String.valueOf(root.value));
        traverseInOrder(root.right);

    }
    private void traversePreOrder(Node root)
    {
        if(root == null)
            return;
        joiner.add(String.valueOf(root.value));
        traversePreOrder(root.left);
        traversePreOrder(root.right);
    }
    /**
     * @return true if tree is Binary search tree, false otherwise
     * */
    public boolean validateSearchTree()
    {
        return validateSearchTree(root,new Integer[]{Integer.MIN_VALUE,Integer.MAX_VALUE});
    }
    private boolean validateSearchTree(Node root,Integer[] range)
    {
        if(root == null)
            return true;
        if(root.value < range[0] || root.value > range[1])
            return false;
        if(!root.isLeaf())
            return validateSearchTree(root.left,new Integer[]{range[0],root.value}) &&
                    validateSearchTree(root.right,new Integer[]{root.value,range[1]});
        if(root.right == null)
            return validateSearchTree(root.left,new Integer[]{range[0], root.value});
        return validateSearchTree(root.right,new Integer[]{root.value,range[1]});
    }
    /**
     * @return values to the tree's Kth nodes
     * */
    public List<Integer> getKthNodes(int k)
    {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        return getKthNodes(root,k,list);
    }
    private List<Integer> getKthNodes(Node root,int k,List<Integer> list)
    {
        if(root != null && k == 0)
            list.add(root.value);
        if(root.right != null)
            getKthNodes(root.right,k-1,list);
        if(root.left != null)
            getKthNodes(root.left,k-1,list);
        return list;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree other = (Tree) o;
        return equals(root,other.root);
    }
    private boolean equals(Node root,Node otherRoot)
    {
        if(root == null && otherRoot == null)
            return true;
        if((root == null && otherRoot != null ) || (otherRoot == null && root != null ))
            return false;
        if(root.value == otherRoot.value)
        {
            return equals(root.left, otherRoot.left) && equals(root.right,otherRoot.right);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(root, joiner);
    }
}
