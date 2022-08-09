package io.techwoman;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SpinWordsTest {

    public String spinWords(String sentence) {
        String[] tokens = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(String str : tokens){
            if(str.length()>=5 ){
                result.append(reverse(str));
            }else{
                result.append(str);
            }
        }
        return result.toString().trim();
    }

    public String spinWordsRefactored(String words) {
        return Arrays.stream(words.split(" "))
                .map(i->i.length() >4 ? new StringBuilder(i).reverse().toString() : i)
                .collect(Collectors.joining());
    }

    public static String reverse(String input) {
        if (input == null) {
            return input;
        }
        String output = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            output = output + input.charAt(i);
        }
        return output;
    }
    public static void main(String[] args) {
        SpinWordsTest spinWordsTest = new SpinWordsTest();
        //System.out.println(spinWordsTest.spinWords("Welcome"));
        System.out.println(spinWordsTest.spinWordsRefactored("Welcome"));
    }
}
