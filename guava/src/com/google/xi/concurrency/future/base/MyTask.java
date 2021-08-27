package com.google.xi.concurrency.future.base;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyTask implements Callable<String> {
    private final String init;
    private final int sleep;
    private final boolean fail;

    public MyTask(String init, int sleep, boolean fail) {
        this.init = init;
        this.sleep = sleep;
        this.fail = fail;
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(sleep);
        if (fail) {
            throw new RuntimeException("fail");
        }
        return init.toUpperCase();
    }
}
