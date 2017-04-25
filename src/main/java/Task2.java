import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2 {
    public static void main(String[] args) throws IOException {
        String report = "Balanced BST comparison number, Random BST comparison number, Not balanced BST comparison number\n";
        int treeHeight = 10;
        int[] arr = getTreeValues(treeHeight);
        int n = arr.length;
        int testNumber = 100;


        int balancedBSTComparisonSum = 0;
        int notbalancedBSTComparisonSum = 0;
        int randomBSTComparisonSum = 0;

        BST balancedBST = new BST(sortedArrayToBalancedBST(arr));
        BST notBalancedBST = new BST(sortedArrayToNotBalancedBST(arr));
        BST randomBST = getRandomBSTFromArray(treeHeight);

        for(int i = 0; i < testNumber; i++ ){
            int randNum = ThreadLocalRandom.current().nextInt(0, n);
            int balancedBSTComparisonNumber;
            int notBalancedBSTComparisonNumber;
            int randomBSTComparisonNumber;

            balancedBST.findNode(randNum);
            balancedBSTComparisonNumber = balancedBST.getComparisonNumber();
            balancedBSTComparisonSum += balancedBST.getComparisonNumber();
            balancedBST.setComparisonNumber(0);

            randomBST = getRandomBSTFromArray(treeHeight);
            randomBST.findNode(randNum);
            randomBSTComparisonNumber = randomBST.getComparisonNumber();
            randomBSTComparisonSum += randomBST.getComparisonNumber();
            randomBST.setComparisonNumber(0);

            notBalancedBST.findNode(randNum);
            notBalancedBSTComparisonNumber = notBalancedBST.getComparisonNumber();
            notbalancedBSTComparisonSum += notBalancedBST.getComparisonNumber();
            notBalancedBST.setComparisonNumber(0);

            report += balancedBSTComparisonNumber + ", "  + randomBSTComparisonNumber + ", " + notBalancedBSTComparisonNumber + "\n";

        }

        System.out.println("Node number: " + n);
        System.out.println("log(node number): " + Math.log(n)/Math.log(2));
        System.out.println("Balanced BST average comparison number = " + balancedBSTComparisonSum / testNumber );
        System.out.println("Not balanced BST average comparison number = " + notbalancedBSTComparisonSum / testNumber );
        System.out.println("Random BST average comparison number = " + randomBSTComparisonSum / testNumber );


        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("./src/main/java/output.csv"), "utf-8"))) {
            writer.write(report);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime runTime = Runtime.getRuntime();
        Process process = runTime.exec("libreoffice --calc ./src/main/java/output.csv &");
    }

    private static int[] getTreeValues(int treeHeight){
        return IntStream.range(0, binarySum(treeHeight)).toArray();
    }

    private static int binarySum(int treeHeight){
        int k = 0;
        for(int j = 0; j <= treeHeight; j ++ ){
            k += Math.pow(2, j);
        }
        return k;
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

    public static Node sortedArrayToNotBalancedBST(int[] a){
        return sortedArrayToNotBalancedBST(a, 0, null);
    }

}
