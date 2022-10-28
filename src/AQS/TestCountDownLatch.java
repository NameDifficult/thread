package AQS;

import java.util.concurrent.CountDownLatch;


/**
 * CountDownLatch的使用场景
 * 1. 某一线程开始运行前等待n个线程执行完毕
 * 2. 实现多个线程执行任务的最大并行性
 * 
 * @author Mi
 *
 */
public class TestCountDownLatch {

	public static void main(String[] args) {

		//创建一个计数器
		final CountDownLatch latch = new CountDownLatch(2);
		
		//创建一个线程，里面设置了countDown方法
		new Thread(new Runnable() {
			
			public void run() {
				
				try {
					System.out.println("线程"+Thread.currentThread().getName()+":"+"正在执行");
					Thread.sleep(1000);
					System.out.println("线程"+Thread.currentThread().getName()+":执行完毕");
					//计数器-1
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			public void run() {

				try {
					System.out.println("线程"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(1000);
					System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
					//计数器-1
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("等待两个线程执行完毕");
					/*
					 * 只有当前面的设置有countDown的方法执行完，并且计数器减了0；
					 * 下面的代码才会执行
					 * 否则该线程一直处于await状态
					 */
					latch.await();
					System.out.println("两个线程已经执行完毕");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		System.out.println("主线程");
				

	}

}
