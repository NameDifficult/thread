package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPool2 {

	public static void main(String[] args) throws Exception {

		//创建线程池,并给一定数量的线程
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		
		//
		for (int i = 0; i < 10; i++) {
			Future<Integer> future = pool.submit(new Callable<Integer>() {

				public Integer call() throws Exception {
					int sum = 0;
					for (int i = 0; i <= 100; i++) {
						sum = sum+ i;
					}
					return sum ;
				}
			});
			
			list.add(future);
			}
		
		//关闭线程
		pool.shutdown();
		
		for (Future<Integer> future1:list) {
			
			System.out.println(future1.get());
			
		}
		
		
		
	}

}
