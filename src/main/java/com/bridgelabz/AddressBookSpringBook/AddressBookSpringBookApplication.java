package com.bridgelabz.AddressBookSpringBook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AddressBookSpringBookApplication {

	public static void main(String[] args) {
		log.info("info : welcome to spring boot !!");
		log.debug("debug : welcome to spring boot !!");
		log.warn("warn: welcome to spring boot !!");
		log.error("error : welcome to spring boot !!");
		SpringApplication.run(AddressBookSpringBookApplication.class, args);
		System.out.println("Welcome to AddressBook");
	}

}
