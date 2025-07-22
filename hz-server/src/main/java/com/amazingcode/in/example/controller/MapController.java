package com.amazingcode.in.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@RestController
@RequestMapping("/api/map")
public class MapController {

    @Autowired
    HazelcastInstance hazelcastInstance;

    @GetMapping("/{key}")
    public String getMapData(@PathVariable (name = "key") String key){
        IMap<String, String> mapData = hazelcastInstance.getMap("myMap");
        return mapData.get(key);
    }

    @PostMapping
    public String putData(@RequestParam (name = "key") String key, @RequestParam (name = "value") String value){
        IMap<String, String> demoMapData = hazelcastInstance.getMap("demoMap");
        demoMapData.put(key, value);
        return "Added: " + key + " - " + value;
    }
    @GetMapping("/demoMapData/{key}")
    public String getDemoMapData(@PathVariable (name = "key") String key){
        IMap<String, String> mapData = hazelcastInstance.getMap("demoMap");
        return mapData.get(key);
    }
}
