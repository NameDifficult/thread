package lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport非常容易控制线程的停启
 * 利用一种名为premit（许可）的概念来做到阻塞和唤醒线程的功能，每个线程都有一个permit。上限为1
 * 也就是只能执行一次park()然后就要unpark()之后才能再次执行
 */
public class TestLockSupport {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0 ; i < 10 ; i++){
					System.out.println(i);
					if (i == 5){
						//线程走到这里时阻塞
						System.out.println("线程阻塞");
						LockSupport.park();
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		//unpark可以先于park调用。而且依然有效。   wait和notify不行，await和signal不行
		//LockSupport.unpark(thread);

		try {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("关闭阻塞");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//解除阻塞线程
		LockSupport.unpark(thread);


	}

}
