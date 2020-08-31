package com.oliver.completableFuture;


import cn.hutool.core.lang.Console;
import com.oliver.completableFuture.util.ListTools;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
class CompletableFutureApplicationTests {
	@Test
	void contextLoads() {
	}

	@Test
	public void thenApply() {
		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello");
		// 单依赖-thenApply  [get()抛检查异常，join非检查异常]
		String join = stringCompletableFuture.thenApply(s -> s + " world").join();
		System.out.println(join);
	}

	@Test
	public void thenCombine(){
		// 双依赖，两个阶段的结果作为BiFunction的入参 [thenCompose 第二个阶段本身作为Function进行计算]
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
		// 两者之中任意一个(最快的一个) 最快执行完的结果作为Function的参数
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
		// 单依赖消费型接口 返回 CompletableFuture<Void> 结果是 Void
		CompletableFuture.supplyAsync(()-> "hello").thenAccept(s -> System.out.println(s + " world"));
	}

	@Test
	public void thenAcceptBoth(){
		// 双依赖消费型接口 两者的结果作为BiFunction的参数
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
		// 任意依赖 结果作为Function参数
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
		// 单依赖 非函数非消费型
		CompletableFuture.supplyAsync(()->{
			sleep(2);
			return "hello";
		}).thenRun(()->System.out.println("hello world"));
		sleep(3);
	}

	@Test
	public void runAfterBoth(){
		// 双依赖 非函数非消费型
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
		// 最快依赖 非函数非消费型
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
	 * CompletableFuture本身作为Function进行计算
	 */
	@Test
	public void thenCompose(){
		// completionStage本身作为Function进行计算
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
		/**
		 * 异常与否均能执行的消费型接口 入参是
		 * 	BiConsumer<? super T, ? super Throwable> action
		 *	需要单独判别 Throwable 参数
		 */

		String join = CompletableFuture.supplyAsync(() -> {
			sleep(2);
			if (1 > 0) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "hello ";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			sleep(1);
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
		// 异常与否均能执行的函数型接口 接收Throwable参数
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
		// 异常才会执行的入参为 Throwable的函数型接口
		String result = CompletableFuture.supplyAsync(() -> {
			sleep(1);
			if (1 == 1) {
				throw new RuntimeException("测试一下异常情况");
			}
			return "s1";
		}).exceptionally(e -> {
			e.printStackTrace(); //e肯定不会null
			return "hello world"; //补偿返回
		}).join();
		System.out.println(result); //打印hello world
	}

	@Test
	public void withParam(){
		List<Integer> integerList = new ArrayList<>();
		AtomicInteger atomicInteger = new AtomicInteger(0);
		IntStream.rangeClosed(0,10).forEach(integerList::add);
		integerList.forEach(x-> CompletableFuture.supplyAsync(()->(int)Math.pow(x,3)).thenApply(s->{
			atomicInteger.addAndGet(s);
			return s;
		})
				.thenAccept(System.out::println));
		sleep(20);
		System.out.println("atomicInteger = " + atomicInteger.get());
	}

	@Test
	public void synchronizedList(){
		Executor executor = Executors.newFixedThreadPool(5);
		List<Integer> integerList = new ArrayList<>();
		IntStream.rangeClosed(1,100).forEach(integerList::add);
		/*List<Object> synchronizedList = Collections.synchronizedList(new LinkedList<>());
		Objects.requireNonNull(ListTools.pageByNum(integerList, 2)).forEach(x->{
			CompletableFuture.supplyAsync(()-> synchronizedList.add(x.get(0)+x.get(1)),executor).
					thenAccept(b -> Console.log(synchronizedList));
		});*/
		final CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		Objects.requireNonNull(ListTools.pageByNum(integerList, 2)).forEach(x-> CompletableFuture.supplyAsync(()->
				copyOnWriteArrayList.add(x.get(0)+x.get(1)),executor).
				thenAccept(b -> {
					Console.log(Thread.currentThread().getName());
					Console.log(copyOnWriteArrayList);
				}));
		do {
			System.out.print("");
		}while (50 != copyOnWriteArrayList.size());
		System.out.println(copyOnWriteArrayList.size());
	}

	@Test
	public void	calculator(){
		int max = 1 << 30;
		int n = 64 - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		n =  (n < 0) ? 1 : (n >= max) ?max : n + 1;
		System.out.println(n);
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
