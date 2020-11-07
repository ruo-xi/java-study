package top.laonaailifa.jdk.concurrent.example.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Demo7_ParalleComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        MyTask myTask1 = new MyTask(1, 50000);
        MyTask myTask2 = new MyTask(50001, 100000);
        MyTask myTask3 = new MyTask(100001, 150000);
        MyTask myTask4 = new MyTask(150001, 200000);

        Future<List<Integer>> f1 = service.submit(myTask1);
        Future<List<Integer>> f2 = service.submit(myTask2);
        Future<List<Integer>> f3 = service.submit(myTask3);
        Future<List<Integer>> f4 = service.submit(myTask4);

        f1.get();
        f2.get();
        f3.get();
        f4.get();
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

        private List<Integer> getPrime(int startPos, int endPos) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = startPos; i < endPos; i++) {
                if (isPrime(i) && i != 1 && i != 0) {
                    list.add(i);
                }
            }
            return list;
        }

        private boolean isPrime(int i) {
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0){
                    return false;
                }
            }
            return true;
        }
    }
}
