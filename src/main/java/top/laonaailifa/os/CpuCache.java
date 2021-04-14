package top.laonaailifa.os;

public class CpuCache {
    static final int MATRIX_BIG_LENGTH = 1024;
    static final int MATRIX_BIG_CYCLE_NUM = 1000;

    static final int MATRIX_SMALL_LENGTH = 4;
    static final int MATRIX_SMALL_CYCLE_NUM = 50000000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        cycleSumByLine(intArrayInit(MATRIX_SMALL_LENGTH), MATRIX_SMALL_CYCLE_NUM, MATRIX_SMALL_LENGTH);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        cycleSumByColumn(intArrayInit(MATRIX_SMALL_LENGTH), MATRIX_SMALL_CYCLE_NUM, MATRIX_SMALL_LENGTH);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        cycleSumByLine(intArrayInit(MATRIX_BIG_LENGTH), MATRIX_BIG_CYCLE_NUM, MATRIX_BIG_LENGTH);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        cycleSumByColumn(intArrayInit(MATRIX_BIG_LENGTH), MATRIX_BIG_CYCLE_NUM, MATRIX_BIG_LENGTH);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int[][] intArrayInit(int matrixBigLength) {
        int a[][] = new int[matrixBigLength][matrixBigLength];
        for (int i = 0; i < matrixBigLength; i++) {
            for (int j = 0; j < matrixBigLength; j++) {
                a[i][j] = i * matrixBigLength + j;
            }
        }
        return a;
    }


    private static void cycleSumByLine(int[][] a, int cycleNum, int matrixLength) {
        int sum = 0;
        for (int b = 0; b < cycleNum; b++) {
            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < matrixLength; j++) {
                    sum += a[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    private static void cycleSumByColumn(int[][] a, int cycleNum, int matrixLength) {
        int sum = 0;
        for (int b = 0; b < cycleNum; b++) {
            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < matrixLength; j++) {
                    sum += a[j][i];
                }
            }
        }
        System.out.println(sum);
    }
}
