package com.google.xi.concurrency.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmoothBusrstyDemo {

    private static final ExecutorService ES = Executors.newFixedThreadPool(2);
    private static final RateLimiter rateLimiter = RateLimiter.create(4);

    public static void main(String[] args) {
        ES.execute(() -> {
            Thread.currentThread().setName("线程1");
            Stopwatch stopwatch = Stopwatch.createStarted();
            while (stopwatch.elapsed().getSeconds() < 600) {
                double acquire = rateLimiter.acquire(10);
                Duration elapsed = stopwatch.elapsed();
                System.out.println(Thread.currentThread().getName() + " " + elapsed.getSeconds() + ": " + acquire);
            }
        });
        ES.execute(() -> {
            Thread.currentThread().setName("线程2");
            Stopwatch stopwatch = Stopwatch.createStarted();
            while (stopwatch.elapsed().getSeconds() < 600) {
                double acquire = rateLimiter.acquire(4);
                Duration elapsed = stopwatch.elapsed();
                System.out.println(Thread.currentThread().getName() + " " + elapsed.getSeconds() + ": " + acquire);
            }
        });
    }
}
