package longAdd;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;





class Count1{
	public volatile static long count1 = 0L;
}


class Count2{
	public  static AtomicLong count2 = new AtomicLong(0L);
}


class Count3{
	//LongAdd底层用的是分段锁；分段锁也是CAS操作，在高并发下性能比atomicLong要好
	public 	static LongAdder count3 = new LongAdder();
}






public class TestLongAdd {
	
	public static void main(String[] args) throws Exception{

		Thread[] threads = new Thread[1000];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
							Count2.count2.incrementAndGet();
					}
				}
			});
		}
		long start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}
		while (Count2.count2.get()!=100000000L) {
		}
		long end = System.currentTimeMillis();
		System.out.println("count2:" + (end - start) + "值为："+Count2.count2.get());
		System.out.println("CPU核数:"+Runtime.getRuntime().availableProcessors());
		
		
		
		
		
		
		
		//----------------------------------------------------------------------------------------
		Object lock = new Object();
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					for (int j = 0; j < 100000; j++) {
						synchronized (lock) {
							++Count1.count1;
						}
					}
				}
			});
		}
		
		start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}
		while (Count1.count1 != 100000000L) {
			
		}
		end = System.currentTimeMillis();
		
			System.out.println( "count1:" + (end - start) + "值为："+Count1.count1);
		
		
		
		
		
		
		
		
		
		
		//------------------------------------------------------------------------------------------------
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						Count3.count3.increment();
					}
				}
			});
		}
		start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}
		while (Count3.count3.sum()!=100000000L) {
			
		}
		end = System.currentTimeMillis();
		System.out.println( "count3:" + (end - start) + "值为："+Count3.count3.sum());
		
		
	}

}

