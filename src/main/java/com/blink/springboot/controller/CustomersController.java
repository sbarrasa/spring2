package com.blink.springboot.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.blink.springboot.dao.CustomerRedisRepository;
import com.blink.springboot.entities.Customer;
import com.blink.springboot.entities.CustomerRedis;

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
		return customersRedisRepository.findByCustomerId(id).orElseThrow(
							() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
															"Customer #%d not foud".formatted(id) )  );
	}
	
	
}