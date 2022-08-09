package io.techwoman;

import org.apache.commons.lang.time.StopWatch;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        System.out.println("Total processors :" + Runtime.getRuntime().availableProcessors());
        /*StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Sum of numbers :" + sequentialSumUsingStreams(100));
        stopWatch.stop();
        System.out.println("Time elapsed with stream approach :" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        System.out.println("Sum of numbers :" + sequentialSumUsingIteration(100));
        stopWatch.stop();
        System.out.println("Time elapsed with iteration approach :" + stopWatch.getTime());*/

        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreams::sequentialSumUsingStreams, 10_000_000) + " msecs");
        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
        System.out.println("Ranged sum done in: " + measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");
        System.out.println("Parallel Ranged sum done in: " + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }

    public static long sequentialSumUsingStreams(long n){
        return Stream.iterate(1L,i->i+1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }

    public static long sequentialSumUsingIteration(long n){
        long sum = 0;
        for(long i=1L;i<=n;i++){
            sum += i;
        }
        return sum;
    }

    public static long measureSumPerf(Function<Long, Long> added, long n){
        long fastest = Long.MAX_VALUE;
        for(int i=0;i<10;i++){
            long start = System.nanoTime();
            long sum = added.apply(n);
            long duration = (System.nanoTime()- start)/1_000_00;
            System.out.println("Result : "+ sum);
            if(duration <fastest)
                fastest = duration;
        }
        return fastest;
    }
}
