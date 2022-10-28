package AQS;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

	public static void main(String[] args) {
		//参数表示允许多少个线程执行
		Semaphore semaphore = new Semaphore(1);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					semaphore.acquire();
					System.out.println("Thread1 : start");
					Thread.sleep(1000);
					System.out.println("Thread1 : end");
				} catch (Exception e) {
				}finally {
					semaphore.release();
				}
				
			}
		}).start();
		//----------------------------------------------------------------------------
		
		new Thread(new Runnable() {
			public void run() {
				try {
					semaphore.acquire();
					System.out.println("Thread2 : start");
					Thread.sleep(1000);
					System.out.println("Thread2 : end");
				} catch (Exception e) {
				}finally {
					semaphore.release();
				}
			}
		}).start();
		
		
	}

}
