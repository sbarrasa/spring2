package com.blink.springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.blink.springboot.entities.Customer;


@Component
@KafkaListener(topics = "${kafka.topic.name}")
public class KafkaConsumer {
private Logger logger = LoggerFactory.getLogger(getClass());

  @KafkaHandler
  public void listener(Customer customer) {
    logger.info("Customer received {} ", customer);
  }
  
  
  @KafkaHandler
  public void listener(String message) {
    logger.info("Message received {} ", message);
  }
}