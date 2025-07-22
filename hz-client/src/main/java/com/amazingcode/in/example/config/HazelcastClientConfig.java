package com.amazingcode.in.example.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastClientConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("first-hz-cluster");
        clientConfig.getNetworkConfig().addAddress("localhost:5701", "localhost:5702");
        return HazelcastClient.newHazelcastClient(clientConfig);
    }
}
