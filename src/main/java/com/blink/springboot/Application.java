package com.blink.springboot;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableCaching
@SpringBootApplication
public class Application {
	private static ObjectMapper mapper ;
	
	public static ObjectMapper getMapper() {
		if(mapper == null)
			mapper = new ObjectMapper();
		return mapper;
	}
	
	public static void show(Object object) {
		try {
			System.out.println(getMapper().writeValueAsString(object));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	
		SpringApplication.run(Application.class, args);
		
	}


}