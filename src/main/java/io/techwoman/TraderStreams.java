package io.techwoman;

import io.techwoman.decoratorpattern.Trader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TraderStreams {
    public static void main(String[] args) {
       Trader raoul = new Trader("Raoul" ,"Cambridge");
       Trader mario = new Trader("Mario","Milan");
       Trader alan = new Trader("Alan","Cambridge");
       Trader brian = new Trader("Brian" ,"Cambridge");

       List<Transaction> transactions = Arrays.asList(
               new Transaction(brian , 2011, 300),
               new Transaction(raoul, 2012,1000),
               new Transaction(raoul,2011,400),
               new Transaction(mario,2012,710),
               new Transaction(mario,2012,700),
               new Transaction(alan,2012,950)
       );

        printCambridgeTrader(transactions);
        //System.out.println("Any Milan Traders ? " + findMilanTraders(transactions));
    }

    public void find2011TransactionsAndSortByValue(List<Transaction> transactions){
        List<Transaction> tr2011 = transactions.stream()
                .filter(e->e.getYear() ==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    public void findUniqueWorkCitiesOfTraders(List<Trader> traders){
        List<String> cities = traders.stream()
                .map(e->e.getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    public void findCambridgeTraders(List<Transaction> transactions){
        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    public void findSortedTraders(List<Transaction> transactions){
        String traders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
              //  .collect(Collectors.joining());
                .reduce("", (n1,n2) -> n1 + n2);

    }

    public static boolean findMilanTraders(List<Transaction> transactions){
      long count = transactions.stream()
                .map(e->e.getTrader())
                .filter(e->e.getCity().equals("Milan"))
                .count();

        return count > 0L;
        /*return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));*/
    }

    public static void printCambridgeTrader(List<Transaction> transactions){
        transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getValue())
                .forEach(System.out::println);
    }

    public void findHighestValue(List<Transaction> transactions){
        Optional<Integer> highestValue = transactions.stream()
                .map(t->t.getValue())
                .reduce(Integer::max);
    }

    public void finalSmallestValue(List<Transaction> transactions){
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }
}
