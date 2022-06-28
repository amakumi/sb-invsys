package com.idmgmt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class SpringbootWebAppApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebAppApp.class, args);
	}

    @Bean
    //@LoadBalanced
    public RestTemplate restTemp() {
        return new RestTemplate();
    }

}
