package com.demo.microservices.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {
	@Input("validProcess")
	SubscribableChannel validProcess();
	
	@Output("approved")
	MessageChannel approved();
	
	@Output("declined")
	MessageChannel declined();
	
}

