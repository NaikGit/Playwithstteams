package io.techwoman;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import static java.util.stream.Collectors.*;

public class MoreStreams {
    public static void main(String[] args) {
       //maxCalories();
        //totalCalories();
        //calculateStats();
       // joinNames();
       // groupDishTypes();
       // groupByCaloricLevel();
      //  groupingMultiLevel();
       // countDishesTypes();
       // availableCaloriesLevel();
       // dishType();
       // maxCaloriesByType();
        partitionUseCases();
    }

    private static void partitionUseCases() {
        Map<Boolean, Map<Boolean, List<Dish>>> menu = Dish.menu.stream().
                collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d->d.getCalories()>500)));

        System.out.println(" case 1 :" + menu );
    }

    private static void maxCaloriesByType() {

        Map<Boolean,Dish> menu = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                            maxBy(Comparator.comparingInt(Dish::getCalories)),
                                            Optional::get)));
        System.out.println(" Max calories by type :" + menu);
    }

    private static void dishType() {
        Map<Boolean, List<Dish>> partitionMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionMenu.get(true);
        System.out.println("Vegetarian dishes :" + vegetarianDishes);
    }

    private static void availableCaloriesLevel() {
        /*Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelByDishType =
                Dish.menu.stream().collect(groupingBy(Dish::getType,
                                            mapping(dish -> {
                                                if(dish.getCalories() <400) return Dish.CaloricLevel.DIET;
                                                else if (dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
                                                else return Dish.CaloricLevel.FAT;
                                            },toSet())));*/

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelByDishType =
                Dish.menu.stream().collect(groupingBy(Dish::getType,
                        mapping(dish->{
                            if(dish.getCalories()<400) return Dish.CaloricLevel.DIET;
                            else if(dish.getCalories()<700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        },toCollection(HashSet::new))));

        System.out.println("Caloric level by type :" + caloricLevelByDishType);
    }

    private static void groupingMultiLevel() {
        Map<Dish.Type ,Map<Dish.CaloricLevel,List<Dish>>> dishByTypeCaloricLevel =
                Dish.menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish ->{
                                    if(dish.getCalories()<=400) return Dish.CaloricLevel.DIET;
                                    else if(dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                }))
                );
        System.out.println(dishByTypeCaloricLevel);
    }

    private static void countDishesTypes(){
        Map<Dish.Type,Long> typesCount = Dish.menu.stream().collect(groupingBy(Dish::getType,counting()));
        System.out.println(" Dishes Types Count :" + typesCount);
    }
    private static void groupByCaloricLevel() {
        Map<Dish.CaloricLevel,List<Dish>> dishByCaloricLevel = Dish.menu.stream().collect(groupingBy(dish ->{
            if(dish.getCalories()<=400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.FAT;
        }));
        System.out.println(" Dish Caloric level :" + dishByCaloricLevel);
    }

    private static void groupDishTypes() {
        Map<Dish.Type, List<Dish>> dishByTypes =  Dish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("Dish types :" + dishByTypes);
    }

    private static void joinNames() {
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(" All Dishes name :" + shortMenu);
    }

    public static void calculateStats(){
        IntSummaryStatistics statistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("Stats :" + statistics );
    }
    public static void maxCalories(){
       // Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories)));
       // Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(reducing((d1,d2) ->d1.getCalories()>d2.getCalories() ? d1:d2));
       /* Map<Dish.Type,Optional<Dish>> mostCalories = Dish.menu.stream().collect
                (groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories))));*/
        Map<Dish.Type , Dish> mostCalories = Dish.menu.stream().collect(
                groupingBy(Dish::getType,collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                )));
        System.out.println("Most calorie :"+ mostCalories.toString());
    }

    public static void totalCalories(){
        //int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        //int totalCalories = Dish.menu.stream().collect(reducing(0,Dish::getCalories,(i,j) ->i+j));
        //int totalCalories = Dish.menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));
        //int totalCalories = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int totalCalories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Total Calories :" +totalCalories);
    }
}
