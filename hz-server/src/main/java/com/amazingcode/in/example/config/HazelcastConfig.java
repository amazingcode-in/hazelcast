/*package com.amazingcode.in.example.config;

import com.amazingcode.in.example.mapstore.EmployeeMapStore;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class HazelcastConfig {

    @Bean
    @Lazy
    public HazelcastInstance hazelcastInstance(EmployeeMapStore employeeMapstore){
        Config config = new Config();
        config.setInstanceName("first-hz-instance");
        config.setClusterName("first-hz-cluster");

        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig= networkConfig.getJoin();

        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true)
                .addMember("localhost:5701")
                .addMember("localhost:5702");

        networkConfig.setPortCount(2);

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("myMap");

        config.addMapConfig(mapConfig);

         MapConfig demoMapConfig = new MapConfig();
         demoMapConfig.setName("demoMap");
         demoMapConfig.setTimeToLiveSeconds(10);
         demoMapConfig.setMaxIdleSeconds(5);

         config.addMapConfig(demoMapConfig);

        MapStoreConfig empMapStoreConfig = new MapStoreConfig();
        empMapStoreConfig.setImplementation(employeeMapstore)
                .setWriteDelaySeconds(30)
                .setInitialLoadMode(MapStoreConfig.InitialLoadMode.EAGER);

        MapConfig employeeMapConfig = new MapConfig();
        employeeMapConfig.setMapStoreConfig(empMapStoreConfig).setName("employeeMap");

        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.LFU)
                .setMaxSizePolicy(MaxSizePolicy.ENTRY_COUNT)
                .setSize(5);

        employeeMapConfig.setEvictionConfig(evictionConfig);

        config.addMapConfig(employeeMapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
*/