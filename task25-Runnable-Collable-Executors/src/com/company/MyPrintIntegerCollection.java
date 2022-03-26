package com.company;

import java.util.Collection;
import java.util.Collections;

public class MyPrintIntegerCollection implements Runnable {
    private final Collection<Integer> collection;

    public MyPrintIntegerCollection(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        System.out.println("+++++++MyPrintIntegerCollectionTask::run");
        for (Integer value : collection) {
            System.out.println(value);
        }
        // throw new RuntimeException
        System.out.println("-------MyPrintIntegerCollection::run");
    }
}
