package com.google.xi.concurrency.future;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.xi.concurrency.future.base.MyFutureCallback;
import com.google.xi.concurrency.future.base.MyTask;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ListenableFutureDemo4 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ListeningExecutorService ES =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(8));

        ListenableFuture<String> future = Futures.submit(new MyTask("a", 3, false), ES);
        // 将 future 和 function 封装成一个任务
        ListenableFuture<String> future1 = Futures.transform(future, input -> input, ES);
        System.out.println(future1.get());
    }

}
