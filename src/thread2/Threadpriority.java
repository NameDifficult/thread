package thread2;

public class Threadpriority {

	public static void main(String[] args) {

		/*
		 * 设置线程的优先级，只是修改这个线程可以去抢到CPU时间片的概率 
		 * 并不是优先级高的线程一定能抢到CPU时间片
		 * 优先级的设置，是一个整数(0，10]的整数，默认是5
		 */		
		Runnable runnable1 = new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}	
			}
		};
		
		Thread thread1 = new Thread(runnable1,"thread1");
		Thread thread2 = new Thread(runnable1,"thread2");
		
		//设置优先级
		//设置优先级必须要放到这个线程开始执行start之前
		thread1.setPriority(1);
		thread2.setPriority(10);
		
		thread1.start();
		thread2.start();
		
		
	}

}
