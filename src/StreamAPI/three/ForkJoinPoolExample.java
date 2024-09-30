package StreamAPI.three;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 8; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }

    private static class FactorialTask extends RecursiveTask<Long> {
        private long n;
        @Override
        protected Long compute() {
            if (n <=1 ) {
                return 1l;
            }
//            return IntStream.rangeClosed(2, n).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get().longValue();
            FactorialTask factorialTask = new FactorialTask(n-1);
            factorialTask.fork();
            return factorialTask.join()*n;
        }

        public FactorialTask(long n) {
            this.n = n;
        }
    }
}