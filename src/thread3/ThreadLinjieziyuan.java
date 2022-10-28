package thread3;

public class ThreadLinjieziyuan {

	public static void main(String[] args) {

		//临界资源问题
		//某个景区有四个售票员同时售票
		Runnable runnable1 = new Runnable() {
			
			public void run() {
				while(ticketcenter.restcound>0) {
					/*
					 * 类锁
					/*
					 * synchronized (ThreadLinjieziyuan.class) {
					 *  if(ticketcenter.restcound<=0) {
					 * return; }
					 *  }
					 */
					//创建对象锁
					//设置锁时需要保证，多个线程看到的锁，需要是同一把锁
					synchronized (" ") {
						if(ticketcenter.restcound<=0) {
							return;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+":"+ --ticketcenter.restcound+"张");
					}
					
				}
				
			}
		};
		//四个线程模拟四个售票员
		Thread thread1 = new Thread(runnable1,"thread-1");
		Thread thread2 = new Thread(runnable1,"thread-2");
		Thread thread3 = new Thread(runnable1,"thread-3");
		Thread thread4 = new Thread(runnable1,"thread-4");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		
		
		
	}

}

class ticketcenter{
	
	//描述剩余的票数
	public static int restcound = 100;
	
}
