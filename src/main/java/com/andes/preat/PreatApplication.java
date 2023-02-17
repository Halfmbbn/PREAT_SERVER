package com.andes.preat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PreatApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreatApplication.class, args);
	}

}
