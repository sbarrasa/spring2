package com.blink.springboot.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import com.blink.springboot.dao.CustomerRedisRepository;
import com.blink.springboot.entities.Customer;
import com.blink.springboot.entities.CustomerRedis;

@RestController
@RequestMapping("/customers")
public class CustomersController {

	
	@Autowired
	private CustomerRedisRepository customersRedisRepository;
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	
		
	
	@PostMapping("/redis/")
	public Customer saveToRedis(@RequestBody Customer customer) {
				
		return customersRedisRepository.save(new CustomerRedis(customer));
	}
	
	@GetMapping("/redis/all")
	public Iterable<CustomerRedis> getAllFromRedis() { 
		return customersRedisRepository.findAll(Sort.by("id"));
	} 
	
	@GetMapping("/redis/{id}")
	public Customer getFromRedis(@PathVariable Long id) {
		return customersRedisRepository.findByCustomerId(id).orElseThrow();
	}
	
	
}