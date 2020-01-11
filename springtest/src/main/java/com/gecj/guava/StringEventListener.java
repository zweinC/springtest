package com.gecj.guava;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import org.springframework.stereotype.Component;

/**
 * StringEventListener
 */
@Component
public class StringEventListener {

    @Subscribe
    @AllowConcurrentEvents
    public void listener(String event) {
        System.err.println("receive msg: "+event+", ThreadID:"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        boolean flag = true;
        if(flag) System.err.println("hello,java!");
        if(flag == true) System.err.println("hello,jvm!");
    }
}