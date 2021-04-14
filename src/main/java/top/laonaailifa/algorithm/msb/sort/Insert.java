package top.laonaailifa.algorithm.msb.sort;

public class Insert extends Sort {
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
                swap(array, j, j - 1);
                Sort.printArr(array);
            }
        }
    }
}
