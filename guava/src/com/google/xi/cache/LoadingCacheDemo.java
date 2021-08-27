package com.google.xi.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoadingCacheDemo {


    public static void main(String[] args) {

        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase();
            }
        };

        Weigher<String, String> weightByLength =
                new Weigher<String, String>() {
                    @Override
                    public int weigh(String key, String value) {
                        return value.length();
                    }
                };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .removalListener(RemovalListeners.asynchronous((RemovalListener<String, String>)
                        notification -> {
                            // do something
                        }, Executors.newFixedThreadPool(1)))
                .build(loader);

        RemovalListener<String, String> listener =
                new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(
                            RemovalNotification<String, String> notification) {
                        // do something
                    }
                };
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        CacheBuilder.newBuilder()
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .build(loader);

        try {
            cache.get("a");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CacheLoader<String, String> asyncLoader = CacheLoader.asyncReloading(loader, threadPool);


        cache.invalidate("a");

        List<String> keys = new ArrayList<>();
        keys.add("a");
        keys.add("b");
        keys.add("c");
        cache.invalidateAll(keys);

        cache.invalidateAll();

        ConcurrentMap<String, String> map = cache.asMap();
        map.remove("key");
        map.replace("key", "value");
        map.compute("key", (key, oldValue) -> key.toLowerCase());
    }
}
