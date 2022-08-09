package io.techwoman;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

class Result{

    public static long calculateAmount(int[] prices) {
        long min = 0L;
        long discount =0L;

        long[] results = new long[prices.length];

        if(prices.length != 0)
            min = prices[0];
        for(int i =0;i<prices.length;i++){
            if(i == 0){
                results[i] = prices[0];
                continue;
            }
            discount = prices[i] - min;
            if(discount < 0 ){
                results[i] = 0;
            }else{
                results[i] = discount;
            }

            if(prices[i] < min)
                min = prices[i];

        }

        return Arrays.stream(results).sum();
    }
}

public class ItemDiscount {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] prices = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        reader.close();

        System.out.println(" Total Sum : " +Result.calculateAmount(prices));


    }
}
