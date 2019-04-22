package com.michael.disruptor.zconfig;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.michael.disruptor.disruptor.LongEvent;
import com.michael.disruptor.disruptor.LongEventFactory;
import com.michael.disruptor.disruptor.LongEventHandler;
import com.michael.disruptor.disruptor.LongEventProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class MyConfiguration {

    @Bean
    public Disruptor disruptor() {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        disruptor.handleEventsWith(longEventHandler());
        disruptor.start();
        return disruptor;
    }

    @Bean
    public LongEventProducer longEventProducer() {
        RingBuffer<LongEvent> ringBuffer = disruptor().getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        return producer;
    }

    @Bean
    public LongEventFactory longEventFactory() {
        return new LongEventFactory();
    }


    @Bean
    public LongEventHandler longEventHandler() {
        return new LongEventHandler();
    }
}
