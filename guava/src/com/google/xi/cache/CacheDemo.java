package com.google.xi.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheDemo {

    public static void main(String[] args) {

        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        cache.size();// 0
        cache.getUnchecked("simple test");// cache miss  -> load -> "SIMPLE TEST"

        cache.size();// 1
        cache.getUnchecked("simple test");// cache hit  ->  "SIMPLE TEST"

    }

}
