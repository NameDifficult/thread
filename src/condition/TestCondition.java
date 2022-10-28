package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Condition用于多个线程间的协调
 * 
 * @author Mi
 *
 */
public class TestCondition {

	public static void main(String[] args) {

		//创建一个sout对象
		sout Sout = new sout();
		
		new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					Sout.show1(i);
				}
				
			}
		},"A").start();
		
		
		
		new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					Sout.show2(i);
				}
				
			}
		},"B").start();
		
		
		
		new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					Sout.show3(i);
				}
				
			}
		},"C").start();
		
		
		
		
		
	}

}

class sout{
	
	//定义一个整数，用来表示当前线程进行的标记
	private int number = 1;
	
	//创建一个Lock实例
	private Lock lock = new ReentrantLock();
	
	//利用Lock实例来创建condition实例
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	
	//创建三个方法，用来输出A,B,C
	public void show1(int num) {
		//上锁
		lock.lock();
		System.out.print("获得A锁：");
		
		//判断是否为1
			try {
				if(number!=1) {
					//释放lock进入等待状态
					condition1.await();
				}
				
				//打印
				System.out.println(Thread.currentThread().getName()+num);
				
				//通知第二个方法
				number = 2;
				condition2.signal();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				//释放锁
				lock.unlock();
			}
			
		
	}
	
	
	public void show2(int num) {
		//上锁
				lock.lock();
				System.out.print("获得B锁：");
				
				//判断是否为2
					try {
						if(number!=2) {
							//释放lock进入等待状态
							condition2.await();
						}
						//打印
						System.out.println(Thread.currentThread().getName()+num);
						
						//通知第二个方法
						number = 3;
						condition3.signal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finally {
						//释放锁
						lock.unlock();
					}
					
	}
	
	
	public void show3(int num) {
		//上锁
				lock.lock();
				System.out.print("获得C锁：");
				
				//判断是否为2
					try {
						if(number!=3) {
							//释放lock进入等待状态
							condition3.await();
						}
						
						
						//打印
						System.out.println(Thread.currentThread().getName()+num);
						
						//通知第二个方法
						number = 1;
						condition1.signal();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finally {
						//释放锁
						lock.unlock();
					}
					
	}
	
	
}
