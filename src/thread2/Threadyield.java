package thread2;

public class Threadyield {

	public static void main(String[] args) {

		
		//线程礼让，指的是让当前的运行状态的线程释放自己的CPU资源，由运行状态，回到就绪状态
		Runnable runnable1 = new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
					if(i==3) {
						Thread.yield();
					}
				}
			}
		};
		
		Thread thread1 = new Thread(runnable1,"thread-1");
		Thread thread2 = new Thread(runnable1,"thread-2");

		thread1.start();
		thread2.start();
	}

}
