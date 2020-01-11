package com.gecj.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCacheTest{
    static LoadingCache<String,String> cache = CacheBuilder.newBuilder()
    .expireAfterAccess(3L,TimeUnit.SECONDS)
    .expireAfterWrite(3L, TimeUnit.MINUTES)
    .concurrencyLevel(6)
    .initialCapacity(100)
    .maximumSize(1000)
    .softValues()
    .build(new CacheLoader<String,String>() {

                @Override
                public String load(String key) throws Exception {
                    // TODO Auto-generated method stub
                    return key + "value";
                }
    });

    public static void main(String[] args) throws Exception{
        cache.put("name", "hello cache");
        int interval = 1000;
        while(true){
            System.err.println("name cache = "+ cache.getIfPresent("name"));
            System.err.println("loadCache: key="+
                interval +",value="+cache.get(interval+""));
            Thread.sleep(interval);
            interval += 1000;
        }
    }
}