package com.amazingcode.in.example.consumer;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private static final Logger log = LoggerFactory.getLogger(QueueConsumer.class);

    @Autowired
    HazelcastInstance hazelcastInstance;

    public void startConsumer(){
        new Thread(()->{
            try{
                IQueue<String> queue = hazelcastInstance.getQueue("my-queue");
                while (true){
                    String message = queue.take();
                    log.info("Task received: {}", message);
                }
            }catch (Exception e){
                log.error("Error is: {}", e.getMessage());
            }
        }).start();
    }
}
