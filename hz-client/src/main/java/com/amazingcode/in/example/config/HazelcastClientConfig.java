package com.amazingcode.in.example.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class HazelcastClientConfig {

    /*
    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("first-hz-cluster");
        clientConfig.getNetworkConfig().addAddress("localhost:5701", "localhost:5702");

        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setInvalidateOnChange(true);
        nearCacheConfig.setName("employeeMap");
        nearCacheConfig.setTimeToLiveSeconds(0);

        clientConfig.addNearCacheConfig(nearCacheConfig);

        return HazelcastClient.newHazelcastClient(clientConfig);
    }
    */

    @Value("${hazelcast.cluster.name:my-first-cluster}")
    private String clusterName;

    @Value("${hazelcast.near.cache:employeeMap}")
    private String nearCacheName;

    @Bean
    public HazelcastInstance hazelcastInstance() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("hazelcast.cluster.name", clusterName);
        properties.setProperty("hazelcast.near.cache", nearCacheName);

        XmlClientConfigBuilder builder = new XmlClientConfigBuilder("hazelcast-client-config.xml");
        builder.setProperties(properties); // inject values

        ClientConfig config = builder.build();
        return HazelcastClient.newHazelcastClient(config);
    }

}
