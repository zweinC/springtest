package com.gecj.guava;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EventBusConfig
 */
@Configuration
public class EventBusConfig {

    /**
     * 定义事件总线bean
     * @return
     */
    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }

    @Bean
    public AsyncEventBus asyncEventBus(){
        return new AsyncEventBus(Executors.newFixedThreadPool(5));
    }
}