package thread3;

import java.util.concurrent.locks.ReentrantLock;


public class ThreadLinjieziyuan2 {

	public static void main(String[] args) {

		//实例化一个锁对象
		ReentrantLock reentrantLock = new ReentrantLock(true);//加true表示公平锁
		
		//实例化四个售票员
		Runnable runnable = new Runnable() {
			
			public void run() {
				//对临界资源上锁
				while(ticketcenter1.ticket>0) {
				reentrantLock.lock();
				if(ticketcenter1.ticket<=0) {
					reentrantLock.unlock();
					break;
				}
				System.out.println(Thread.currentThread().getName()+":"+ --ticketcenter1.ticket+"张");
				
				//对临界资源解锁
				reentrantLock.unlock();
				}
			}
		};
		//四个线程模拟四个售票员
		Thread thread1 = new Thread(runnable,"thread-1");
		Thread thread2 = new Thread(runnable,"thread-2");
		Thread thread3 = new Thread(runnable,"thread-3");
		Thread thread4 = new Thread(runnable,"thread-4");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

}




class ticketcenter1{
	
	//描述剩余的票数
	public static int ticket = 100;
	
}


