package io.techwoman;

import java.util.Arrays;
import java.util.function.Function;

public class MultiplyAll {

    public static Function<Integer, int[]> multiplyAll(int[] array) {
        /* left blank for unlimited creativity :) */
        return (n) -> Arrays.stream(array).map(x -> x*n).toArray();
    }

    public static void main(String[] args) {
        System.out.println(multiplyAll(new int[]{1, 2, 3}));
    }
}
