package com.google.xi.concurrency.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER =
            (r, executor) -> System.err.println("reject");

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().setDaemon(true).build(), REJECTED_EXECUTION_HANDLER);

    public static void main(String[] args) {
        Future<Void> future = THREAD_POOL.submit(() -> {
            return null;
        });
        future.cancel(true);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t2.start();
        t.interrupt();
    }


    public void tstExecute() {
        THREAD_POOL.execute(() ->
        {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        THREAD_POOL.execute(() ->
        {
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException("hehe");
        });
        THREAD_POOL.execute(() ->
        {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(1);

    }
}
