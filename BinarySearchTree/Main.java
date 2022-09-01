public class Main
{
    public static void main(String[] args)
    {
        Tree tree = new Tree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(6);
        tree.insert(3);
        tree.insert(8);
        tree.insert(30);
        tree.insert(14);
        tree.insert(24);
        tree.insert(26);

        tree.traverseTree(Tree.Traverse.BREADTH_FIRST);
        tree.traverseTree(Tree.Traverse.IN_ORDER);
        tree.traverseTree(Tree.Traverse.PRE_ORDER);
        tree.traverseTree(Tree.Traverse.POST_ORDER);
        tree.validateSearchTree();
    }
}
