package com.company;

import java.util.Collection;
import java.util.concurrent.Callable;

public class MinValue implements Callable {

    int min = 0;
    private final Collection<Integer> collection;

    public MinValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Integer call() throws Exception {
        min = collection.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
        return min;
    }
}
