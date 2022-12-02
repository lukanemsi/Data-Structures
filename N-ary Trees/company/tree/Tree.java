package company.tree;
import java.util.Stack;


public class Tree<T> {
    private final Node<T> root;

    public Tree (T data) {
        root = new Node<T>(data);
    }
    private Node<T> findT(T value)
    {
        return findT(root,new Node<T>(value));
    }
    private Node<T> findT(Node<T> current, Node<T> value){
        if (!value.equals(current) && current.isLeaf())
            return null;
        if (value.equals(current))
            return current;
        return current.getChildren().stream().map(c->findT(c, value)).filter(value::equals).findFirst().orElse(null);
    }
	
    public void insert(T value, T parent) {
        if (findT(value) != null)
            return;
        Node<T> p = findT(parent);
        if (p == null)
            return;
        p.insert(new Node<T>(value));
    }
    public void remove(T value)
    {
        if(root.getData().equals(value))
            return;
        Node<T> node = findT(value);
        if (node==null)
            return;
        node.remove();
    }


    public T LCA(T a, T b)
    {
        Node<T> nodeA = findT(a);
        Node<T> nodeB =findT(b);
        if (nodeA == null || nodeB == null)
            return null;
        Stack<T> parentsA = parentNodes(nodeA);
        Stack<T> parentsB = parentNodes(nodeB);


        for(var i : parentsA)
        {
            for(var j : parentsB)
            {
                if(i.equals(j))
                    return i;
            }
        }
        return null;
    }
    private Stack<T> parentNodes(Node<T> node)
    {
        Stack<T> parents = new Stack<>();
        parentNodes(node,parents);
        return parents;
    }
    private void parentNodes(Node<T> node,Stack<T> parents)
    {
        if(node == null)
            return;
        parents.push(node.getData());
        parentNodes(node.getParent(),parents);
    }

    public int distanceBetweenNodes(T a, T b)
    {
        T closest = LCA(a,b);
        if(closest == null)
            return -1;
        Stack<T> parentsA = parentNodes(findT(a));
        Stack<T> parentsB = parentNodes(findT(b));

        return (int)( parentsA.stream().takeWhile(x -> !x.equals(closest)).count()
                +parentsB.stream().takeWhile(x -> !x.equals(closest)).count());
    }
	
	 public List<T> toList()
    {
        return toList(root,new LinkedList<T>());
    }
    private List<T> toList(Node<T> current,List<T> list)
    {
        list.add(current.getData());
        current.getChildren().forEach(c -> toList(c,list));
        return list;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean containsKey(T key) {
        return findT(key) != null;
    }

    public void traversal()
    {
        root.traversal(root);
    }
}
