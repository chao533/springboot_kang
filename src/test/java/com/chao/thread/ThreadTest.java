package com.chao.thread;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

public class ThreadTest {

	public static void main(String[] args) throws Exception {
//		TimeInterval timer = DateUtil.timer();
//		test4();
//		Console.log("总时长:{}" ,timer.intervalMs());
		
	}
	
	static int sum = 0;
	static int val1 = 0;
	static int val2 = 0;
	static int val3 = 0;
	public static void test1() {
		CyclicBarrier barrier = new CyclicBarrier(3, () -> Console.log("sum:{}" , val1 + val2 + val3));
		new Thread(() -> {
			try {
				val1 = RandomUtil.randomInt(100000);
				sum = sum + val1;
				Console.log(Thread.currentThread().getName()+"---val1:{}" , val1);
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}).start();
		
		new Thread(() -> {
			try {
				val2 = RandomUtil.randomInt(100000);
				sum = sum + val2;
				Console.log(Thread.currentThread().getName()+"---val2:{}" , val2);
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
		new Thread(() -> {
			try {
				val3 = RandomUtil.randomInt(100000);
				sum = sum + val3;
				Console.log(Thread.currentThread().getName()+"---val3:{}" , val3);
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
	
	public static void test2() throws InterruptedException {
		TimeInterval timer = DateUtil.timer();
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(() -> {
			val1 = RandomUtil.randomInt(100000);
//			sum = sum + val;
			Console.log(Thread.currentThread().getName()+"---val:{}" , val1);
//				countDownLatch.await();
			countDownLatch.countDown();
		}).start();
		
		new Thread(() -> {
			val2 = RandomUtil.randomInt(100000);
//			sum = sum + val;
			Console.log(Thread.currentThread().getName()+"---val:{}" , val2);
//				countDownLatch.await();
			countDownLatch.countDown();
		}).start();
		
		new Thread(() -> {
			val3 = RandomUtil.randomInt(100000);
//			sum = sum + val;
			Console.log(Thread.currentThread().getName()+"---val:{}" , val3);
//				countDownLatch.await();
			countDownLatch.countDown();
		}).start();
		
		countDownLatch.await();
		Console.log("sum:{}" , val1 + val2 + val3);
		Console.log("时长：{}" , timer.intervalMs());
		
		
		
	}
	
	static int sum1 = 0;
	public static void test3() throws InterruptedException {
		TimeInterval timer = DateUtil.timer();
		List<FutureTask<Integer>> list = CollUtil.newArrayList();
		for(int i=0; i<3; i++) {
//			FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
//
//				@Override
//				public Integer call() throws Exception {
//					return RandomUtil.randomInt(100000);
//				}
//			});
			
			FutureTask<Integer> futureTask2 = new FutureTask<>(() -> RandomUtil.randomInt(100)) ;
			
//			new Thread(new FutureTask<>(() -> RandomUtil.randomInt(100000))).start();
			
			new Thread(futureTask2).start();
			list.add(futureTask2);
			
		}
		list.forEach(task -> {
			try {
				Integer val = task.get();
				sum1 = sum1 + val;
				Console.log(Thread.currentThread().getName()+"---val:{}" , val);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Console.log("sum:{}" , sum1);
		Console.log("时长：{}" , timer.intervalMs());
		
	}
	// 创建线程的几种方式
	public static void test4() throws Exception {
		// 1
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Console.log(Thread.currentThread().getName() + "---1");
			}
		}).start();
		
		// 2
		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Console.log(Thread.currentThread().getName() + "---2");
				return null;
			}
		});
//		new Thread(futureTask).start();
//		futureTask.run();
		
		// 3
		new Callable<String>() {

			@Override
			public String call() throws Exception {
				Console.log(Thread.currentThread().getName() + "---3");
				return null;
			}
		}.call();
		
		// 4
		ExecutorService  executorService  = Executors.newFixedThreadPool(3);
		executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Console.log(Thread.currentThread().getName() + "---4");
				return "";
			}
		});
		
		executorService.submit(futureTask);
		
		executorService.shutdown();
		
		
	}
}
