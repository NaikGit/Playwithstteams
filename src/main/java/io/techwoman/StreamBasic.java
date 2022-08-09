package io.techwoman;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {
    public static void main(String[] args) {
       // getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
       // getDishesByType().entrySet().forEach(System.out::println);
       // printUniqueElements();
      //  getOnlyFirstTwoMeatDishes(Dish.menu).forEach(System.out::println);
       // countWordLength();
      //  countDishNameLength(Dish.menu);
      //  printUniqueCharacters();
      //  printSquareOfNumbers();
       // printNumbersPair();
       // printFindAnyNumber();
      //  countDishes(Dish.menu);
        fibonacci();
      //  generateRandomNumbers();
    }

    private static void generateRandomNumbers() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void fibonacci() {
        //Stateful
        IntSupplier fib = new IntSupplier() {
            private int previous =0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.current + this.previous;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
        //Stateless
        /*Stream.iterate(new int[]{0,1},
                t->new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .map(t->t[0])
                .forEach(System.out::println);
              //  .forEach(t->System.out.println("(" + t[0] +","+ t[1] + ")"));*/
    }

    public static  void countDishes(List<Dish> dishes){
        int count = dishes.stream()
                .map(d->1)
                .reduce(0,(a,b) ->a +b);
        System.out.println("Total Dishes : " + count);

    }
    public static void printFindAnyNumber(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                numbers.stream()
                .map(x -> x* x)
                .filter(x->x % 3 ==0)
                .findFirst();
        System.out.println(" printFindAnyNumber :" + firstSquareDivisibleByThree.toString());
    }
    public static void printNumbersPair(){
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream()
                                .flatMap(i->numbers2.stream()
                                                    .filter(j->(i+j)%3 ==0)
                                                    .map(j->new int[]{i,j})
                                )
                                .collect(Collectors.toList());
    }

    public static void printSquareOfNumbers(){
        List<Integer> numbers = Arrays.asList(2,3,4,5,6);
        numbers.stream()
                .map(a->a*a)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
    public static void printUniqueCharacters(){
        List<String> strList = Arrays.asList("Hello","World");

        String[] arrayOfWords = {"Hello","World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

        strList.stream()
        .map(word->word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList())
        .forEach(System.out::println);
    }


    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(d->d.getCalories() <400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    public static Map<Dish.Type , List<Dish>> getDishesByType(){
        Map<Dish.Type , List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        return dishesByType;
    }

    public static void printUniqueElements(){
        List<Integer> numbers = Arrays.asList(1,2,2,1,3,4,5,6,7);
        numbers.stream()
                .filter(i->i%2 ==0)
                .distinct()
                .forEach(System.out::println);
    }

    public static  List<Dish> getOnlyFirstTwoMeatDishes(List<Dish> dishes){
        return dishes.stream()
                .filter(d->d.getType()== Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
    }

    public static void countWordLength(){
        List<String> words = Arrays.asList("Java8" ,"Lambda","In","Action");
        List<Integer> wordLength = words.stream()
                                    .map(String::length)
                                    .collect(Collectors.toList());
        wordLength.forEach(System.out::println);
    }

    public static void countDishNameLength(List<Dish> dishes){
        List<Integer> dishNameLength = dishes.stream()
                                        .map(Dish::getName)
                                        .map(String::length)
                                        .collect(Collectors.toList());
        dishNameLength.forEach(System.out::println);

    }
}
