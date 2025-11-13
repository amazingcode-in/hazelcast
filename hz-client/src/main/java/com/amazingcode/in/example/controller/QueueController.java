package com.amazingcode.in.example.controller;

import com.amazingcode.in.example.consumer.QueueConsumer;
import com.amazingcode.in.example.producer.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class QueueController {

    @Autowired
    QueueProducer queueProducer;

    @Autowired
    QueueConsumer queueConsumer;

    @PostMapping("/{message}")
    public String queue(@PathVariable String message){
        return queueProducer.sendTask(message);
    }

    @GetMapping("/start-consumer")
    public String startConsumer(){
        queueConsumer.startConsumer();
        return "Consumer started.";
    }
}
