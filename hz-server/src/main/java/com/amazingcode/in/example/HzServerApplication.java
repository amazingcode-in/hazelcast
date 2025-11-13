package com.amazingcode.in.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:hazelcast-config.xml")
public class HzServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HzServerApplication.class, args);
	}

}
