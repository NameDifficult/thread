package aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * 				邮戳锁
 * 				读写锁的升级优化
 * @author Mi
 *
 */
public class TestStampedLock {

	static int number = 30;
	static StampedLock stampedLock = new StampedLock();
	
	
	public void write() {
		long l = stampedLock.writeLock();
		System.out.println("写线程准备修改");
		try {
			number = number + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			stampedLock.unlockWrite(l);
		}
		System.out.println("写线程已修改完毕:"+number);
	}
	
	
	public void read() throws InterruptedException {
		long stamp = stampedLock.readLock();
		try {
			System.out.println("读线程准备读");
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				System.out.println("正在读……" + number);
			}
			System.out.println("已读完：" + number);
		} finally {
			stampedLock.unlockRead(stamp);
		}
	}
	
	
	//乐观读
	public void tryOptimisticRead() {
		try {
			long stamp = stampedLock.tryOptimisticRead();
			int result = number;
			System.out.println("读前：" + result + ":::" + stampedLock.validate(stamp));
			for (int i = 0; i < 4; i++) {
				Thread.sleep(1000);
				System.out.println("正在读：" + result + ":::" + stampedLock.validate(stamp));
			}
			System.out.println();
			if (!stampedLock.validate(stamp)) {
				System.out.println("已有人修改过");
				stamp = stampedLock.readLock();
				
				try {
					System.out.println("从乐观读升级为悲观读");
					result = number;
					System.out.println("重新悲观读后："+result);
				} finally {
					stampedLock.unlockRead(stamp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		TestStampedLock testStampedLock = new TestStampedLock();
		
		new Thread(() -> testStampedLock.tryOptimisticRead()).start();
		
		
		Thread.sleep(1000);
		
		new Thread(() -> testStampedLock.write()).start();
		
		
	}

}







