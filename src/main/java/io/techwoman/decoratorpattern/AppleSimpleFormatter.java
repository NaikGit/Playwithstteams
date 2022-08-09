package io.techwoman.decoratorpattern;

import io.techwoman.AppleFormatter;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of weight " + apple.getWeight() + "g";
    }
}
