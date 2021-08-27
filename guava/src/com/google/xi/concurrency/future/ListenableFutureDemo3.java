package com.google.xi.concurrency.future;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.xi.concurrency.future.base.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ListenableFutureDemo3 {

    static ListeningExecutorService ES = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Stopwatch stopwatch = Stopwatch.createStarted();
        ListenableFuture<String> future = ES.submit(new MyTask("guava_future", 1, false));

        ListenableFuture<String> future1 =
                Futures.transformAsync(future,
                        input -> ES.submit(new MyTask(input + " future1", 2, true)), ES);

        ListenableFuture<String> future2 =
                Futures.transform(future1, input -> input + " future2", ES);

        System.out.println(future2.get() + " " + stopwatch.elapsed().getSeconds());
    }
}
