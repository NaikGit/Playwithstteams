package io.techwoman.decoratorpattern;

import io.techwoman.decoratorpattern.Apple;
import io.techwoman.AppleFormatter;

public class AppleFancyFormatter  implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristics = apple.getWeight() >150 ? "heavy" :"light";
        return "A"+ characteristics + "" + apple.getColor()+" apple";
    }
}
