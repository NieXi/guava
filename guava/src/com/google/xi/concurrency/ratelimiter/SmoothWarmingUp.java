package com.google.xi.concurrency.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SmoothWarmingUp {
    /**
     * 预热 10s 后达到每秒一个令牌
     */
    private static final RateLimiter rateLimiter2 = RateLimiter.create(1.0, 10, TimeUnit.SECONDS);

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        while (true) {
            double acquire = rateLimiter2.acquire();
            Duration elapsed = stopwatch.elapsed();
            System.out.println(elapsed.getSeconds() + ": " + acquire);
            if (elapsed.getSeconds() > 60) {
                break;
            }
        }
    }
}
