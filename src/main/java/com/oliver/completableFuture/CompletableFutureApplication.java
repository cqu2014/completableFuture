package com.oliver.completableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CompletableFutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompletableFutureApplication.class, args);
	}

}
