package io.techwoman;

import java.util.*;

public class WordOrder {

    public static String orderWithStreams(String words) {

        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparing(s->Integer.valueOf(s.replaceAll("\\D", ""))))
                .reduce((a,b)->a + " " +b).get();

    }
    public static String orderRefactored(String words) {
        if(words.length() == 0)
            return words;
        StringTokenizer st = new StringTokenizer(words);
        String[] stringWords = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            String currentWord = st.nextToken();
            char[] chars = currentWord.toCharArray();
            for(char c : chars){
                if(Character.isDigit(c)) {
                    stringWords[Character.getNumericValue(c)-1] = currentWord;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s: stringWords) {
            sb.append(s + ' ');
        }
        return sb.toString().trim();

    }

    public static String order(String words) {
        if(words.length() == 0)
            return words;
        List<Character> numbers = new ArrayList<Character>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
        StringTokenizer st = new StringTokenizer(words);
        String[] stringWords = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            String currentWord = st.nextToken();
            for (int i = 0; i < currentWord.length(); i++) {
                if (numbers.contains(currentWord.charAt(i))) {
                    stringWords[numbers.indexOf(currentWord.charAt(i))] = currentWord;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s: stringWords) {
            sb.append(s + ' ');
        }
        return sb.toString().trim();

    }

    public static void main(String[] args) {
        System.out.println(orderWithStreams("is2 Thi1s T4est 3a"));
    }
}
