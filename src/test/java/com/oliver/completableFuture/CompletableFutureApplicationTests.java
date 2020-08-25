package com.oliver.completableFuture;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
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

	@Test
	public void thenAccept(){
		CompletableFuture.supplyAsync(()-> "hello").thenAccept(s -> System.out.println(s + " world"));
	}

	@Test
	public void thenAcceptBoth(){
		CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2);
			return "hello";
		}).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
			sleep(2);
			return " world";
		}), (s1, s2) -> System.out.println(s1 + s2)).thenApply(s-> "china".toUpperCase());

		System.out.println(objectCompletableFuture.join());

		sleep(3);
	}

	@Test
	public void acceptEither(){
		CompletableFuture.supplyAsync(() -> {
			sleep(2);
			return "hello john";
		}).acceptEither(CompletableFuture.supplyAsync(() -> {
			sleep(1);
			return "hello tom";
		}),System.out::println);

		sleep(3);
	}

	@Test
	public void thenRun(){
		CompletableFuture.supplyAsync(()->{
			sleep(2);
			return "hello";
		}).thenRun(()->System.out.println("hello world"));
		sleep(3);
	}

	@Test
	public void thenAfterBoth(){
		CompletableFuture.supplyAsync(()->{
			sleep(2);
			return "s1";
		}).runAfterBoth(CompletableFuture.supplyAsync(()->{
			sleep(2);
			return "s2";
		}),() ->System.out.println("hello world"));
		sleep(3);
	}

	@Test
	public void runAfterEither(){
		CompletableFuture.supplyAsync(()->{
			sleep(2);
			System.out.println("hello tom");
			return "s1";
		}).runAfterEither(CompletableFuture.supplyAsync(()->{
			sleep(3);
			System.out.println("hello Jim");
			return "s2";
		}),() -> System.out.println("hello world"));
		sleep(3);
	}


	/**
	 * CompletableFuture 本身作为Function的参数进行计算
	 */
	@Test
	public void thenCompose(){
		System.out.println(CompletableFuture.supplyAsync(() -> {
			sleep(1);
			return "hello ";
		}).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
			sleep(2);
			return s.concat("word").concat(Thread.currentThread().getName());
		})).join());
	}

	@Test
	public void whenComplete(){
		String join = CompletableFuture.supplyAsync(() -> {
			sleep(1);
			if (1 > 0) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "hello ";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			sleep(3);
			//会执行
			System.out.println("return world...");
			return "world";
		}), (s) -> {
			//并不会执行
			System.out.println("combine result :" + s);
			return s;
		}).whenComplete((s, t) -> {
			System.out.println("current result is :" + s);
			if (Objects.nonNull(t)) {
				System.out.println("阶段执行过程中存在异常：");
				t.printStackTrace();
			}
		}).join();
		System.out.println(join);
	}

	@Test
	public void handle(){
		System.out.println(CompletableFuture.supplyAsync(() -> {
			sleep(3);
			//出现异常
			if (1 < 3) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "Tom";
		}).handle((s, t) -> {
			if (Objects.nonNull(t)) {
				return t.getMessage();
			}
			//这里也可以对正常结果进行转换
			return s.toLowerCase();
		}).join());
	}

	@Test
	public void exceptionally() {
		String result = CompletableFuture.supplyAsync(() -> {
			sleep(1);
			if (1 == 2) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).exceptionally(e -> {
			e.printStackTrace(); //e肯定不会null
			return "hello world"; //补偿返回
		}).join();
		System.out.println(result); //打印hello world
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
