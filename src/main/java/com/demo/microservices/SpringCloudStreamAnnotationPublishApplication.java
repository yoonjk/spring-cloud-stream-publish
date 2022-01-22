package com.demo.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.demo.microservices.processor.MyProcessor;

@EnableBinding(MyProcessor.class)
@SpringBootApplication
public class SpringCloudStreamAnnotationPublishApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamAnnotationPublishApplication.class, args);
	}

}

