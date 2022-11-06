package com.spaceyatech.berlin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BerlinspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BerlinspringbootApplication.class, args);
		log.info("hello");
	}

}
