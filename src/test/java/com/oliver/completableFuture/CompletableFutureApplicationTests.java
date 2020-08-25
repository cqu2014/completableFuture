package com.oliver.completableFuture;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
class CompletableFutureApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void thenApply() {
		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello");
		// 但依赖
		String join = stringCompletableFuture.thenApply(s -> s + " world").join();
		System.out.println(join);
	}

	@Test
	public void thenCombine(){
		String join = CompletableFuture.supplyAsync(() -> {
			sleep(3);
			return "hello";
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			sleep(5);
			return " world";
		}), String::concat).join();
		System.out.println(join);
	}

	@Test
	public void applyToEither(){
		String join = CompletableFuture.supplyAsync(() -> {
			sleep(3);
			return "Tom";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			sleep(2);
			return "John";
		}), s -> "hello " + s).join();
		System.out.println(join);
	}




	/**
	 * sleep 秒钟
	 *
	 * @param second
	 */
	private void sleep(int second){
		try {
			TimeUnit.SECONDS.sleep(second);
		} catch (InterruptedException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
