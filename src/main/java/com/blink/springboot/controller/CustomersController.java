package com.blink.springboot.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.blink.springboot.Application;
import com.blink.springboot.dao.CustomerRedisRepository;
import com.blink.springboot.entities.Customer;
import com.blink.springboot.entities.CustomerRedis;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/customers")
public class CustomersController {

	
	@Autowired
	private CustomerRedisRepository customersRedisRepository;
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@PostMapping("/")
	public CustomerRedis saveToRedis(@RequestBody Customer customer) {
		CustomerRedis customerRedis = new CustomerRedis(customer);
		
		return customersRedisRepository.save(customerRedis);
	}
	
	@GetMapping("/all")
	public Iterable<CustomerRedis> getAllFromRedis() { 
		return customersRedisRepository.findAll(Sort.by("id"));
	} 
	
	@GetMapping("/{id}")
	public CustomerRedis getFromRedis(@PathVariable Long id) {
		return customersRedisRepository.findByCustomerId(id).orElseThrow();
	}
	
	
}