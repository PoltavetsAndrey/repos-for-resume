package com.company;

import java.util.Collection;
import java.util.concurrent.Callable;

public class AverageValue implements Callable {

    double average = 0;
    private final Collection<Integer> collection;

    public AverageValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Double call() throws Exception {
        average = collection.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        return average;
    }
}
