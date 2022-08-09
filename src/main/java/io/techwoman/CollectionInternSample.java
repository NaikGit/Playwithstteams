package io.techwoman;

public class CollectionInternSample {
    public static void main(String[] args) {
        Integer obj1 = new Integer(1);
        Integer obj2 = new Integer(1);

        String str1 = new String("hello").intern();
        String str2 = new String("hello").intern();
        boolean isEqual = str1 == str2;
        System.out.println("Is equal :" + isEqual);

    }
}
