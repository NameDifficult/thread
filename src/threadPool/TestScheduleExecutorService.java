package threadPool;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduleExecutorService {

	static int count = 0 ;

	public static void main(String[] args)  throws Exception{
		

		//创建一个固定大小，可延迟或定时的线程池
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		
		Future<Integer> future = pool.schedule(new Callable<Integer>() {

			public Integer call() throws Exception {
				//生成一个随机数
				int num = new Random().nextInt(100);
				//打印这个随机数
				System.out.println(Thread.currentThread().getName()+ ":" +num);
				return num;
			}
		}, 1, TimeUnit.SECONDS);
		pool.shutdown();
		
		//打印future
		System.out.println(future.get());

		
		

		
		//------------------------------------------------------------------------------------------------
		//创建一个固定大小，可延迟或定时的线程池
		ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(2);
		//给线程池分配任务，并设定固定时间执行。该方法会将方法内部的休眠时间也记录在内
		pool2.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				++count;
				System.out.println("执行2");
				if (count == 2){
					pool2.shutdown();
				}
			}
		},2,3,TimeUnit.SECONDS);//这里表示在2秒钟之后开始执行，然后每隔3秒执行一次

		
		
		

		//------------------------------------------------------------------------------------------------
		//创建一个固定大小，可延迟或定时的线程池
		ScheduledExecutorService pool3 = Executors.newScheduledThreadPool(2);
		//给线程池分配任务，并设定固定时间执行,该方法不将方法内部的休眠时间记录在内
		pool3.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println("执行3");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},2,3,TimeUnit.SECONDS);//这里表示在2秒钟之后开始执行，然后每隔3秒执行一次
	}
}
