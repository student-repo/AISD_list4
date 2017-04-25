
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BST {



    public Node root;

    public void setComparisonNumber(int comparisonNumber) {
        this.comparisonNumber = comparisonNumber;
    }

    public int getComparisonNumber() {
        return comparisonNumber;
    }

    public void incrementComparisonNumber() {
        comparisonNumber++;
    }

    private int comparisonNumber = 0;

    public BST(){
        this.root = null;
    }

    public BST(Node root){
        this.root = root;
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
        incrementComparisonNumber();
        if(node == null){
            return null;
        }
        if(node.data == key) {
            incrementComparisonNumber();
            return node;
        }
        else if(node.data > key){
            incrementComparisonNumber();
            incrementComparisonNumber();
            return findN(node.left, key);
        }
        else{
            incrementComparisonNumber();
            incrementComparisonNumber();
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

//        int treeHeight = 2;
//        int[] arr = getTreeValues(treeHeight);
//        int n = arr.length;
//
//        BST balancedBST = new BST(sortedArrayToBalancedBST(arr));
//        BST notBalancedBST = new BST(sortedArrayToNotBalancedBST(arr));
//        BST randomBST = getRandomBSTFromArray(treeHeight);
//
//        for(int i = 0; i < 1000; i++ ){
//            int randNum = ThreadLocalRandom.current().nextInt(0, n);
//            int balancedBSTComparisonNumber;
//            int notBalancedBSTComparisonNumber;
//            int randomBSTComparisonNumber;
//
//            balancedBST.findNode(randNum);
//            balancedBSTComparisonNumber = balancedBST.getComparisonNumber();
//            balancedBST.setComparisonNumber(0);
//
//            notBalancedBST.findNode(randNum);
//            notBalancedBSTComparisonNumber = notBalancedBST.getComparisonNumber();
//            notBalancedBST.setComparisonNumber(0);
//
//            randomBST = getRandomBSTFromArray(treeHeight);
//            randomBSTComparisonNumber = randomBST.getComparisonNumber();
//            randomBST.setComparisonNumber(0);
//
//        }
//
//
//
//
//
//        balancedBST.displayTree();
//        System.out.println(balancedBST.checkBST());
//        System.out.println();
//        System.out.println();
//
//        notBalancedBST.displayTree();
//        System.out.println(notBalancedBST .checkBST());
//        System.out.println();
//        System.out.println();
//
//        randomBST.displayTree();
//        System.out.println(randomBST.checkBST());
//        System.out.println();
//        System.out.println();


        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./src/main/java/filename.txt"), "utf-8"))) {
            writer.write("something");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Node foo = bst.buildBallancedTree(getTreeValues(3), 0, binarySum(3) -1, null);
//        Node foo = bst.sortedArrayToBST(getTreeValues(3));

//        try {
//            try (Stream<String> stream = Files.lines(Paths.get("./src/main/java/config2"))) {
//                stream.forEach(item -> bst.handleIntput(bst, item));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }






    }

    private static int binarySum(int treeHeight){
        int k = 0;
        for(int j = 0; j <= treeHeight; j ++ ){
            k += Math.pow(2, j);
        }
        return k;
    }

    private static int[] getTreeValues(int treeHeight){
        return IntStream.range(0, binarySum(treeHeight)).toArray();
    }



    public static Node sortedArrayToNotBalancedBST(int[] a){
        return sortedArrayToNotBalancedBST(a, 0, null);
    }


    private static BST getRandomBSTFromArray(int treeHeight){
        List<Integer> arr = Arrays.stream(getTreeValues(treeHeight))
                .boxed()
                .collect(Collectors.toList());
        BST bst = new BST();

        while (arr.size() != 0){
            int randNum = ThreadLocalRandom.current().nextInt(0, arr.size());
            bst.insert((Integer) arr.get(randNum));
            arr.remove(randNum);
        }
        return bst;
    }

    private static Node sortedArrayToNotBalancedBST(int[] a, int i, Node parent){
        if(i >= a.length){
            return null;
        }
        Node root = new Node(a[i]);
        root.parent = parent;
        root.left = null;
            root.right = sortedArrayToNotBalancedBST(a, i + 1, root);
        return root;
    }


    public static Node sortedArrayToBalancedBST(int[] num) {
        if (num.length == 0)
            return null;

        return sortedArrayToBalancedBST(num, 0, num.length - 1, null);
    }

    private static Node sortedArrayToBalancedBST(int[] num, int start, int end, Node parent) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        Node root = new Node(num[mid]);
        root.parent = parent;
        root.left = sortedArrayToBalancedBST(num, start, mid - 1, root);
        root.right = sortedArrayToBalancedBST(num, mid + 1, end, root);

        return root;
    }


    void handleIntput(BST bst, String s){
        if(s.equals("max")){
            bst.max();
        }
        else if(s.equals("min")){
            bst.min();
        }
        else if(s.equals("display_tree")){
            bst.displayTree();
        }
        else if(s.equals("inorder")){
            bst.inorder();
        }
        else if(s.substring(0, s.indexOf(" ")).equals("insert")){
            bst.insert(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
        }
        else if(s.substring(0, s.indexOf(" ")).equals("delete")){
            bst.delete(Integer.parseInt(s.substring(s.indexOf(" ")).substring(1)));
        }
        else if(s.substring(0, s.indexOf(" ")).equals("find")){
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