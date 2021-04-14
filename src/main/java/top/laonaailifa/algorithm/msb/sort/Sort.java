package top.laonaailifa.algorithm.msb.sort;

public class Sort {
    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        Insert.sort(arr);
        printArr(arr);
    }
}
