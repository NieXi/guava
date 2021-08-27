package com.google.xi.concurrency.future;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.xi.concurrency.future.base.MyFutureCallback;
import com.google.xi.concurrency.future.base.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ListenableFutureDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ListeningExecutorService ES =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(8));

        ListenableFuture<String> future =
                ES.submit(new MyTask("guava_future", 1, false));

        Futures.addCallback(future, new MyFutureCallback(), ES);
        future.addListener(() -> System.out.println("done"), ES);
    }

}
