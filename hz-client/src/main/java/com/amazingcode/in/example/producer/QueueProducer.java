package com.amazingcode.in.example.producer;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class QueueProducer {

    private static final Logger log = LoggerFactory.getLogger(QueueProducer.class);

    @Autowired
    HazelcastInstance hazelcastInstance;

    public String sendTask(String task){
        IQueue<String> queue = hazelcastInstance.getQueue("my-queue");
        boolean isInserted = queue.offer(task);
        log.info("Inserts successful into queue : {}, Task send: {}",isInserted, task);
        return task;
    }
}
