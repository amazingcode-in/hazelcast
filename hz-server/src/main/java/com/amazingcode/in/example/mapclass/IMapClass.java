package com.amazingcode.in.example.mapclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import jakarta.annotation.PostConstruct;

@Component
public class IMapClass {

    public static final Logger logger = LoggerFactory.getLogger(IMapClass.class);

    @Autowired
    HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void initData(){
        IMap<String, String> map = hazelcastInstance.getMap("myMap");
        logger.info("Preloaded data into IMap");
        map.put("fruit", "apple");
        map.put("color", "blue");
    }

}
