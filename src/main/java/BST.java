
import java.util.Stack;

public class BST {



    public Node root;

    public BST(){
        this.root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        insert(root, value);

    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(Node node, int value) {
        if(node.data > value) {
            if(node.left == null) {
                node.left = new Node(value, node);
            } else {
                insert(node.left, value);
            }
        }
        else {
            if(node.right == null){
                node.right = new Node(value, node);
            }
            else {
                insert(node.right, value);
            }
        }
    }

    public Node findNode(int key){
        return findN(root, key);
    }


    public Node getMinNode(){
        if(root != null){
            return getMinN(root);
        }
        return null;
    }

    public void min(){
        if(getMinNode() == null){
            System.out.println();
        }
        else{
            System.out.println(getMinNode().data);
        }
    }

    public void max(){
        if(getMaxNode() == null){
            System.out.println();
        }
        else{
            System.out.println(getMaxNode().data);
        }
    }


    private Node getMinN(Node node){
        if(node.left == null){
            return node;
        }
        return getMinN(node.left);
    }

    public Node getMaxNode(){
        if(root != null){
            return getMaxN(root);
        }
        return null;
    }

    private Node getMaxN(Node node){
        if(node.right == null){
            return node;
        }
        return getMaxN(node.right);
    }


    private Node findN(Node node, int key){
        if(node == null){
            return null;
        }
        if(node.data == key) {
            return node;
        }
        else if(node.data > key){
            return findN(node.left, key);
        }
        else{
            return findN(node.right, key);
        }
    }

    public int find(int key){
        if(findNode(key) == null) {
            return 0;
        }
        return 1;
    }

    public void inorder(){
        inord(root);
        System.out.println();
    }

    private void inord(Node node){
        if(node != null) {
            inord(node.right);
            System.out.print(node.data + ", ");
            inord(node.left);
        }
    }

    private void setNewChildToParent(Node parentNode, Node newChild, int childValue){
        if(parentNode.right != null && parentNode.right.data == childValue){
            parentNode.right = newChild;
        } else {
            parentNode.left = newChild;
        }
    }

    void delete(int key) {
        Node toDelete = findNode(key);
        if(toDelete == null){
            return;
        }

        Node temporary;
        if(toDelete.right == null && toDelete.left == null){
            if(toDelete != root){
                setNewChildToParent(toDelete.parent, null, key);
            }
            else {
                setRoot(null);
            }
        }
        else if(toDelete.right == null){
            if(toDelete == root){
                setRoot(toDelete.left);
            }
            else {
                setNewChildToParent(toDelete.parent, toDelete.left, key);
                toDelete.left.parent = toDelete.parent;
            }
        }
        else if(toDelete.left == null){
            if(toDelete == root){
                setRoot(toDelete.right);
            }
            else {
                setNewChildToParent(toDelete.parent, toDelete.right, key);
                toDelete.right.parent = toDelete.parent;
            }
        }
        else{
            temporary = getMinN(toDelete.right);
            setNewChildToParent(temporary.parent, temporary.right, temporary.data);
            if(toDelete == root){
                setRoot(temporary);
                if(temporary.left != null){
                    temporary.left.parent = temporary.parent;
                }
                if(temporary.right != null){
                    temporary.right.parent = temporary.parent;
                }

                if(toDelete.left != null){
                    toDelete.left.parent = temporary;
                }
                if(toDelete.right != null){
                    toDelete.right.parent = temporary;
                }

                temporary.parent = null;
            }
            else{
                temporary.parent = toDelete.parent;
                setNewChildToParent(toDelete.parent, temporary, key);
                if(toDelete.left != null){
                    toDelete.left.parent = temporary;
                }
                if(toDelete.right != null){
                    toDelete.right.parent = temporary;
                }
            }
            temporary.right = toDelete.right;
            temporary.left = toDelete.left;
        }
    }



    public static void main(String[] args){
        BST bst = new BST();

        bst.insert(21);
        bst.insert(13);
        bst.insert(64);
        bst.insert(23);
        bst.insert(2);
        bst.insert(3);
        bst.insert(13);
        bst.insert(1);
        bst.insert(25);
        bst.insert(-10);
        bst.insert(-5);
        bst.insert(-20);
        bst.insert(24);
        bst.insert(13);
        bst.insert(100);
        bst.insert(120);
        bst.insert(130);
        bst.insert(115);
        bst.insert(75);
        bst.insert(67);
        bst.insert(8);


        bst.delete(21);
        bst.delete(23);
//        bst.delete(13);
//        System.out.println(root.data);
//        System.out.println(root.right.data);
//        System.out.println(bst.findNode(4).data);

        bst.displayTree();
        System.out.println(bst.getRoot().data); // = 21
        System.out.println(bst.getRoot().left); // = 21

        System.out.println(bst.getMaxNode().data);
        bst.inorder();

        System.out.println(bst.findNode(64).parent.data);
//        bst.insert(1);
//        bst.insert(2);
//        bst.insert(3);
//        bst.min();
//        bst.max();
//        bst.inorder();
//        bst.delete(3);
//        bst.insert(4);
//        bst.max();
//        System.out.println(bst.find(2));
//        bst.delete(1);
//        bst.delete(2);
//        bst.delete(3);
//        bst.delete(4);
//        bst.min();
//        bst.insert(1000);
//        bst.max();

    }

















    public void displayTree()
    {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int emptyLeaf = 64;
        boolean isRowEmpty = false;
        System.out.println("****............................................................................................................****");
        while(isRowEmpty==false)
        {

            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Node temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****............................................................................................................****");
    }


}
