/**
 * Created by ubuntu-master on 22.04.17.
 */
public class Node {
    int data;
    Node left;
    Node right;
    Node parent;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }

    public Node(int data, Node parent) {
        this.data = data;
        left = null;
        right = null;
        this.parent = parent;
    }
}
