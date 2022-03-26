package com.company;

import java.util.Collection;
import java.util.concurrent.Callable;

public class SumValue implements Callable {

    int sum = 0;
    private final Collection<Integer> collection;

    public SumValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Integer call() throws Exception {
        sum = collection.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return sum;
    }
}
