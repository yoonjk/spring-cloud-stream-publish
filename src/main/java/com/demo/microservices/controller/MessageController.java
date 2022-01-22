package com.demo.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.model.Loan;
import com.demo.microservices.processor.MyProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MessageController {
	@Autowired
	private MyProcessor processor;
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody Loan message) {
		log.info("send Message:{}", message);
		
		if (message.getAmount() > 500000) {
			processor.declined().send(MessageBuilder.withPayload(message).build());
		} else {
			processor.approved().send(MessageBuilder.withPayload(message).build());
		}
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/publish2")
	public ResponseEntity<String> publish2(@RequestBody Loan message) {
		log.info("send Message:{}", message);
		
		processor.validProcess().send(MessageBuilder.withPayload(message).build());
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}	
	
//	@StreamListener(target = "validProcess")
//	@SendTo("approved")
//	public Loan validProcess(Loan loan) {
//		log.info("Loan valid process:{}", loan);
//		
//		loan.setAmount(loan.getAmount() + 100);	
//		
//		return loan;
//	}	
	
	
	@ServiceActivator(inputChannel= "validProcess", outputChannel = "approved")
	public Loan validProcess(Loan loan) {
		log.info("@ServiceActivator Loan valid process:{}", loan);
		
		loan.setAmount(loan.getAmount() + 200);	
		
		if (loan.getAmount() > 900000) {
			return null;
		} else {
			return loan;
		}
		

	}	
	

}

