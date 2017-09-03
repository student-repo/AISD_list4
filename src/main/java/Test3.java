import java.util.Arrays;

/**
 * Created by ubuntu-master on 27.04.17.
 */
public class Test3 {

    public static void main(String[] args){

        int a[] = {4, 2, 4 ,6 ,3 ,1};
//
//        int m = (a.length + 1) / 2;
//        int left[] = Arrays.copyOfRange(a, 0, m);
//        int right[] = Arrays.copyOfRange(a, m, a.length);
//
//        for(int i = 0; i < left.length; i++){
//            System.out.print(left[i] + ", ");
//        }
//        System.out.println();
//
//        for(int i = 0; i < right.length; i++){
//            System.out.print(right[i] + ", ");
//        }

        



                System.out.println(invCount(a));
    }


    static long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i++;
            } else {
                arr[i+j] = right[j];
                count += left.length-i;
                j++;
            }
        }
        return count;
    }

    static long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int left[] = Arrays.copyOfRange(arr, 0, m);
        int right[] = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }
}
