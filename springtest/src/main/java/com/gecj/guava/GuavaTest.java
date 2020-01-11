package com.gecj.guava;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class GuavaTest {

    @Subscribe
    public void sendMessageByMail(String message){
        System.out.println("邮件发送一条信息："+message+ ", ThreadID:"+Thread.currentThread().getId());
    }
    
    @Subscribe
    public void sendMessageByPhone(String message) {
        System.out.println("短息发送一条信息："+message + ", ThreadID:"+Thread.currentThread().getId());
    }

    @Subscribe
    public void listen(DeadEvent event){
        System.err.println(event + ", ThreadID:"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        System.err.println("ThreadID:"+Thread.currentThread().getId());
        EventBus eventBus = new EventBus();
        eventBus.register(new GuavaTest());
        eventBus.post("hello eventBus");
        eventBus.post(1);
        System.out.println("=================");
        EventBus asyncBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        asyncBus.register(new GuavaTest());
        asyncBus.post("hello asyncBus");
        asyncBus.post(1);
    }
}