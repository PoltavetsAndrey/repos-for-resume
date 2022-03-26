package com.company;

import java.util.Collection;
import java.util.concurrent.Callable;

public class MaxValue implements Callable {

    int max = 0;
    private final Collection<Integer> collection;

    public MaxValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Integer call() throws Exception {
        max = collection.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        return max;
    }
}
