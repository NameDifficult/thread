package aqs;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 		ReentrantLock是可重入锁，可中断锁，可实现公平锁
 * 
 * 
 * 
 * 公平锁   非公平锁
 * 非公平锁的优点：能够更充分的利用CPU的时机片，尽量减少CPU的空闲状态。因为线程的切换是需要开销的
 * 			而且，当一个同步状态的情况下，释放了同步状态，再去获取同步状态概率会变的非常大，所以可以减少线程切换的开销。
 * 
 * @author Mi
 *
 */
public class TestReenTrantLock {

	public static void main(String[] args) {
		
		ReenTrankLockDemo reenTrankLockDemo = new ReenTrankLockDemo();
		Thread thread1 = new Thread(reenTrankLockDemo);
		Thread thread2 = new Thread(reenTrankLockDemo);
		thread1.start();
		thread2.start();
		
		
	}
}

class ReenTrankLockDemo extends Thread{
	//非公平锁、公平锁要加true     new ReenTrantLock(true);
	
	private static ReentrantLock lock = new ReentrantLock();
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "获得锁");
			} finally {
				lock.unlock();
			}
		}
	}
}
