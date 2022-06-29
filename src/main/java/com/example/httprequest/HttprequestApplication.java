package com.example.httprequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttprequestApplication {

	 @Bean
	 public RestTemplate getRestTemplate(){
		 return  new RestTemplate();
	 }

	public static void main(String[] args) {
		SpringApplication.run(HttprequestApplication.class, args);
	}

}
