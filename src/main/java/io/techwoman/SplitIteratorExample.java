package io.techwoman;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SplitIteratorExample {
    public static void main(String[] args) {

        List<String> quote = Arrays.asList("This","above","all","to","thine","own","self",
                "be","true");

        Spliterator<String> secondHalf = quote.spliterator();
        Spliterator<String> firstHalf = secondHalf.trySplit();

        System.out.println("*****First Half*********");
        firstHalf.forEachRemaining(System.out::println);
        System.out.println("*****Second Half*********");
        secondHalf.forEachRemaining(System.out::println);
    }
}
