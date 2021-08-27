package com.google.xi.concurrency.future;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.xi.concurrency.future.base.MyTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class ListenableFutureDemo2 {

    static ListeningExecutorService ES = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ListenableFuture<String> futureA = ES.submit(new MyTask("a", 1, true));
        ListenableFuture<String> futureB = ES.submit(new MyTask("b", 3, false));

        ListenableFuture<List<String>> all =
                Futures.allAsList(futureA, futureB);
        ListenableFuture<List<String>> success =
                Futures.successfulAsList(futureA, futureB);

        List<String> result2 = success.get();
        // List<String> result = all.get(6, TimeUnit.SECONDS);


        ListenableFuture<Integer> immediateFuture = Futures.immediateFuture(1);
        System.out.println(immediateFuture.get());
    }

}
