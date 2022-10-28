package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

/*
 * 一、线程池
 */
public class TestThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		//创建一个ThreadPool实例
		ThreadTest threadpool  = new ThreadTest();
		//1.创建一个固定大小的线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		//2.为线程池中的线程分配任务
		for (int i = 0; i < 10; i++) {
			pool.submit(threadpool);
		}
		//3.关闭线程池
		pool.shutdown();
	}
}




//创建一个类，实现Runnable方法
class ThreadTest implements Runnable{
	private int i = 0;
	public void run() {
		while(i<=100) {
			System.out.println(Thread.currentThread().getName()+":"+i++);
		}
	}
}