package io.techwoman;

import io.techwoman.decoratorpattern.Trader;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private Trader trader;
    private int year;
    private int value;

}
