
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.jar.Pack200;
import java.util.stream.Stream;

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
            System.out.println(0);
            return 0;
        }
        System.out.println(1);
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
        if(toDelete == root){
            handleDeleteRoot();
            return;
        }
        Node temporary;
        if(toDelete.right == null && toDelete.left == null){
                setNewChildToParent(toDelete.parent, null, key);
        }
        else if(toDelete.right == null){
                setNewChildToParent(toDelete.parent, toDelete.left, key);
                toDelete.left.parent = toDelete.parent;
        }
        else if(toDelete.left == null){
                setNewChildToParent(toDelete.parent, toDelete.right, key);
                toDelete.right.parent = toDelete.parent;
        }
        else{
            temporary = getMinN(toDelete.right);
            if (temporary == toDelete.right){
                setNewChildToParent(toDelete.parent, temporary, key);
                temporary.left = toDelete.left;
                toDelete.left.parent = temporary;
                temporary.parent = toDelete.parent;
            }
            else{
                if(temporary.right != null){
                    temporary.right.parent = temporary.parent;
                }

                setNewChildToParent(temporary.parent, temporary.right, temporary.data);

                setNewChildToParent(toDelete.parent, temporary, key);

                temporary.parent = toDelete.parent;
                temporary.left = toDelete.left;
                temporary.right = toDelete.right;

                toDelete.left.parent = temporary;
                toDelete.right.parent = temporary;
            }
        }
    }

    private void handleDeleteRoot() {
        if(root.left == null && root.right == null){
            setRoot(null);
        }
        else if(root.right == null){
            root.left.parent = null;
            setRoot(root.left);
        }
        else{
            Node temporary = getMinN(root.right);
            if(temporary == root.right){
                temporary.left = root.left;
                if(root.left != null){
                    root.left.parent = temporary;
                }
                temporary.parent = null;
                setRoot(temporary);
            }
            else {
                if(temporary.right != null){
                    temporary.right.parent = temporary.parent;
                }
                setNewChildToParent(temporary.parent, temporary.right, temporary.data);
                temporary.parent = null;
                temporary.right = root.right;
                temporary.left = root.left;
                root.left.parent = temporary;
                root.right.parent = temporary;
                setRoot(temporary);
            }
        }
    }
    boolean checkBST(){
        if (root == null){
            return true;
        }
        return checkBST(root);
    }

    private boolean checkBST(Node node){
        if(node.left == null && node.right == null){
            return true;
        }
        if(node.left != null){
            if(node.data <= node.left.data || node != node.left.parent){
                return false;
            }
        }
        if(node.right != null){
            if(node.data > node.right.data || node != node.right.parent){
                return false;
            }
        }
        if(node.left != null && node.right != null){
            return checkBST(node.left) && checkBST(node.right);
        }
        if(node.left != null){
            return checkBST(node.left);
        }
            return checkBST(node.right);

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
        bst.insert(116);
        bst.insert(117);


//        bst.delete(21);
//        bst.delete(23);
//        bst.delete(21);
//        bst.delete(23);
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
//        bst.displayTree();

        System.out.println();
        System.out.println();
        System.out.println(bst.checkBST());
        System.out.println();
        System.out.println();



        try {
            try (Stream<String> stream = Files.lines(Paths.get("./src/main/java/config"))) {
                stream.forEach(item -> bst.handleIntput(bst, item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    void handleIntput(BST bst, String s){
        if(s.equals("max")){
            bst.max();
//            System.out.println("MAX");
        }
        else if(s.equals("min")){
            bst.min();
//            System.out.println("MIN");
        }
        else if(s.equals("inorder")){
            bst.inorder();
//            System.out.println("INORDER");
        }
        else if(s.substring(0, s.indexOf(" ")).equals("insert")){
//            System.out.println("INSERT");
//            System.out.println(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
            bst.insert(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
        }
        else if(s.substring(0, s.indexOf(" ")).equals("delete")){
//            System.out.println("DELETE");
//            System.out.println(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
            bst.delete(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
        }
        else if(s.substring(0, s.indexOf(" ")).equals("find")){
//            System.out.println("FIND");
//            System.out.println(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
            bst.find(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
        }
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


















//    void delete(int key) {
//        Node toDelete = findNode(key);
//        if(toDelete == null){
//            return;
//        }
//
//        Node temporary;
//        if(toDelete.right == null && toDelete.left == null){
//            if(toDelete != root){
//                setNewChildToParent(toDelete.parent, null, key);
//            }
//            else {
//                setRoot(null);
//            }
//        }
//        else if(toDelete.right == null){
//            if(toDelete == root){
//                setRoot(toDelete.left);
//            }
//            else {
//                setNewChildToParent(toDelete.parent, toDelete.left, key);
//                toDelete.left.parent = toDelete.parent;
//            }
//        }
//        else if(toDelete.left == null){
//            if(toDelete == root){
//                setRoot(toDelete.right);
//            }
//            else {
//                setNewChildToParent(toDelete.parent, toDelete.right, key);
//                toDelete.right.parent = toDelete.parent;
//            }
//        }
//        else{
//            temporary = getMinN(toDelete.right);
//            setNewChildToParent(temporary.parent, temporary.right, temporary.data);
//            if(toDelete == root){
//                setRoot(temporary);
//                if(temporary.left != null){
//                    temporary.left.parent = temporary.parent;
//                }
//                if(temporary.right != null){
//                    temporary.right.parent = temporary.parent;
//                }
//
//                if(toDelete.left != null){
//                    toDelete.left.parent = temporary;
//                }
//                if(toDelete.right != null){
//                    toDelete.right.parent = temporary;
//                }
//
//                temporary.parent = null;
//            }
//            else{
//                temporary.parent = toDelete.parent;
//                setNewChildToParent(toDelete.parent, temporary, key);
//                if(toDelete.left != null){
//                    toDelete.left.parent = temporary;
//                }
//                if(toDelete.right != null){
//                    toDelete.right.parent = temporary;
//                }
//            }
//            temporary.right = toDelete.right;
//            temporary.left = toDelete.left;
//        }
//    }