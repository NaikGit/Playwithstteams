package io.techwoman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



public class SockPair {



        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {

                Scanner in = new Scanner(System.in);
                int n = in.nextInt();
                int[] colors = new int[101];
                for (int i = 0; i < n; ++i) {
                    int c = in.nextInt();
                    System.out.println("Color is :" + c);
                    colors[c]++;
                }
                System.out.println(Arrays.stream(colors).map(count -> count / 2).sum());
        }


}
