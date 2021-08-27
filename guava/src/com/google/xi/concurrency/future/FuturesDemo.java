package com.google.xi.concurrency.future;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.xi.concurrency.future.base.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class FuturesDemo {

    static ListeningExecutorService ES = MoreExecutors.listeningDecorator(
            Executors.newFixedThreadPool(10));
    static ScheduledExecutorService SCHEDULED_ES = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ListenableFuture<String> a = Futures.submit(new MyTask("a", 5, false), ES);
        ListenableFuture<String> b = Futures.submit(new MyTask("b", 10, false), ES);


        // Futures.addCallback(a, new MyFutureCallback(), ES);
        // System.out.println(a.get() + " " + stopwatch.elapsed().getSeconds());
        // ListenableFuture<String> future1 = Futures.catching(a, Exception.class, e -> "error: " + e.getMessage(), ES);
        // System.out.println(future1.get() + " " + stopwatch.elapsed().getSeconds());

        //
        // try {
        //     String result = Futures.getChecked(a, Exception.class, Duration.ofSeconds(3));
        //     System.out.println(result + " " + stopwatch.elapsed().getSeconds());
        // } catch (Exception e) {
        //     System.out.println(stopwatch.elapsed().getSeconds());
        //     System.out.println(e.getMessage());
        // }


        // String unchecked = Futures.getUnchecked(a);
        // System.out.println(unchecked + " " + stopwatch.elapsed().getSeconds());

        // 立即获取 a 结果，未完成则抛出异常
        // String done = Futures.getDone(a);
        // System.out.println(done);

        // 根据完成顺序返回结果
        // ImmutableList<ListenableFuture<String>> futures = Futures.inCompletionOrder(Lists.newArrayList(b, a));
        // for (ListenableFuture<String> stringListenableFuture : futures) {
        //     System.out.println(stringListenableFuture.get());
        // }

        //  取消 no，不会影响 a
        // ListenableFuture<String> no = Futures.nonCancellationPropagating(a);
        // boolean success = no.cancel(true);
        // System.out.println(success);
        // System.out.println(no.isCancelled());
        // System.out.println(a.isCancelled());
        // System.out.println(a.get());
        // System.out.println(no.get());

        // transform 在任务执行完后立即执行转换
        // lazyTransform 则是在 get 的时候才执行转换，(所有不需要 指定线程池)
        Future<String> lazy = Futures.lazyTransform(a, input -> input);
        Futures.transform(a, input -> input, ES);


    }

}
