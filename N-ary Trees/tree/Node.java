package tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class Node<T>{

    private List<Node<T>> children;
    private Node<T> parent = null;
    private  T data;
    public Node(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    public void insert(Node<T> value)
    {
        value.parent = this;
        children.add(value);
    }

    public boolean isLeaf() {
        return children.stream().noneMatch(Objects::nonNull);
    }

    public int size()
    {
        return size(this) + 1;
    }
    private int size(Node<T> current)
    {
        if(current == null || current.isLeaf())
            return 0;
        return  current.children.stream().map(n -> n.size(n)).reduce(0,Integer::sum)
                + current.children.size();
    }
    public void remove()
    {
        this.children.forEach(c -> this.parent.insert(c));
        this.parent.children.remove(this);
        this.children = new ArrayList<>();
    }
    public List<Node<T>> getChildren() {
        return children;
    }
    public Node<T> getParent() {
        return parent;
    }
    public T getData() {
        return data;
    }

    private boolean isRoot(Node<T> node)
    {
        return node.parent == null;
    }
    public void traversal(Node<T> current)
    {
        if(current == null)
            return;
        System.out.print(current.data+ " ");
        current.children.forEach(this::traversal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return node.data.equals(this.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, parent, data);
    }
}
