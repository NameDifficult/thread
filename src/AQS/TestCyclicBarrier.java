package AQS;

import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @author Mi
 *
 *CyclicBarrier : 循环栅栏   
 *				    当参数值达到指定值后执行某个线程
 *
 */
public class TestCyclicBarrier {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("满人，开栅栏");
			}
		});
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {				
				public void run() {
					try {
						cyclicBarrier.await();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}).start();
		}
	}

}
