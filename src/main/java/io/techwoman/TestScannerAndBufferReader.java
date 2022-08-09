package io.techwoman;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestScannerAndBufferReader {
    public static void main(String[] args) throws IOException {
        final int ARRAY_SIZE = 200000;

        int[] array = new int[ARRAY_SIZE];

        String filePath = "./a.txt";
        File file = new File(filePath);

        System.out.println(" ******Traditional Scanner class *******");
        Scanner scanner = new Scanner(file);
        for(int i=0;i<ARRAY_SIZE;i++){
            array[i]= scanner.nextInt();
        }
        scanner.close();

        System.out.println("********Fast Scanner class *******");
        FastScanner fastScanner = new FastScanner(file);
        for(int i=0;i<ARRAY_SIZE;i++){
            array[i]= fastScanner.ni();
        }
        scanner.close();

    }

    static  class FastScanner{
        BufferedReader bufferedReader;
        StringTokenizer stringTokenizer;

        public FastScanner(File file) throws IOException{
            bufferedReader = new BufferedReader(new FileReader(file));
        }

        public FastScanner(){
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(stringTokenizer == null || !stringTokenizer.hasMoreTokens()){
                try{
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringTokenizer.nextToken();
        }

        int ni(){
            return Integer.parseInt(next());
        }

        long nl(){
            return Long.parseLong(next());
        }

        double nd(){
            return Double.parseDouble(next());
        }

        char[] nc(){
            return next().toCharArray();
        }

        public void close(){
            try{
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
