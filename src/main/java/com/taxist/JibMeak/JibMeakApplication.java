package com.taxist.JibMeak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class JibMeakApplication {
	public static void main(String[] args) {
        SpringApplication.run(JibMeakApplication.class, args);
	}
}
