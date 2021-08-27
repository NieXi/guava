package com.google.xi.concurrency.future;

import com.google.xi.concurrency.future.base.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JdkFutureDemo {

    private static final ExecutorService ES = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Future<String> future = ES.submit(new MyTask("jdk_future", 5, false));

        String result1 = future.get(3, TimeUnit.SECONDS);
        String result = future.get();

        future.isCancelled();
        future.cancel(true);
        future.isDone();
    }

}
