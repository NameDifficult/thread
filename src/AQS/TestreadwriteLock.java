package AQS;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/*
 * 读写锁
 * 写写/读写 需要“互斥”
 * 读读  则不互斥
 * 用读写锁，在高并发的情况下，多个线程读取同一个资源是没有问题的
 * 在写操作时，只有有一个线程进行写操作，进行写时，其他线程被阻塞。
 * 
 * 
 * 
 * ReentrantLock和synchronized基本上都是排它锁，意味着这些锁在同一时刻只允许一个线程进行访问，
 * 而读写锁在同一时刻可以允许多个读线程访问，在写线程访问的时候其他的读线程和写线程都会被阻塞。
 * 读写锁维护一对锁(读锁和写锁)，通过锁的分离，使得并发性提高
 */
public class TestreadwriteLock {
	
	private static int value = 0;

	public static void main(String[] args) {
		
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		Lock readLock = readWriteLock.readLock();
		Lock writeLock = readWriteLock.writeLock();
		
		//创建一个写线程
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						writeLock.lock();
						value = new Random().nextInt(100) ;
						Thread.sleep(1000);
					} catch (Exception e) {
					}finally {
						writeLock.unlock();
					}
				}
			},"书写").start();
		}
		
		
		//读线程
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				public void run() {
					readLock.lock();
					System.out.println("value = " + value);
					readLock.unlock();
				}
			},"读取1").start();
		}
		
		
	
	}

}


