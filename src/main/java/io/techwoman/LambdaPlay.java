package io.techwoman;

import io.techwoman.decoratorpattern.Apple;

import java.util.Comparator;

public class LambdaPlay {
    public static void main(String[] args) {
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
    }
}
