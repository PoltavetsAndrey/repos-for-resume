package com.company;

import java.util.concurrent.Callable;

public class Sleep implements Callable {

    public Sleep() {
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(10000);
        return null;
    }
}
