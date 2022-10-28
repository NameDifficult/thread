package thread3;

/**
 * 
 * @author Mi
 *
 *	wait的作用：保留当前任务的执行进度，并释放当前拥有的锁，然后进入等待队列
 *	notifyAll：唤醒以当前锁为对象的所有任务，被唤醒的任务共同争夺该锁，争夺失败的回到等待池等待再次被唤醒
 *	notify	： 随机唤醒以当前锁为对象的一个任务
 */
public class DeathLock {

	public static void main(String[] args) {

		//死锁：多个线程彼此持有对方所需要的锁对象，而不是释放自己的锁
		
		Runnable runnable = new Runnable() {
			
			public void run() {
				synchronized ("A") {
					System.out.println("A线程有了A锁，等待B锁");
					
//					try {
//						"A".wait();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
					synchronized ("B") {
						System.out.println("A线程同时持有了A锁和B锁");
						"A".notifyAll();
					}
				}
			}
		};
		
		Runnable runnable1 = new Runnable() {
					
					public void run() {
						synchronized ("A") {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("C线程有了C锁，等待B锁");
							
							try {
								"A".wait();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							synchronized ("B") {
								System.out.println("C线程同时持有了C锁和B锁");
							}
						}
					}
				};
		
		Runnable runnable2 = new Runnable() {
			
			public void run() {

				synchronized ("B") {
					System.out.println("B线程持有了B锁，等待A锁");
					
					synchronized ("A") {
						System.out.println("B线程同时持有了B锁和A锁");
						
//						"A".notifyAll();
						//只唤醒了一个以A为锁的任务，还有一个未唤醒
						"A".notify();
					}
				}
				
			}
		};
		
		Thread thread1 = new Thread(runnable1);
		Thread thread = new Thread(runnable);
		Thread thread2 = new Thread(runnable2);
		
		thread.start();
//		thread1.start();
		thread2.start();
		
	}

}
