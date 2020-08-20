package com.oliver.completableFuture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CompletableFutureApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void thenApply() {
		CompletableFuture<String> stage = CompletableFuture.supplyAsync(() -> "Hello");
		String result = stage.thenApply(s-> s + " world").join();
		System.out.println(result);
	}

	@Test
	public void thenCombine(){
		String result = CompletableFuture.supplyAsync(()->{
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		}).thenCombineAsync(CompletableFuture.supplyAsync(()->{
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return " world";
		}), String::concat).join();

		System.out.println(result);
	}

}
