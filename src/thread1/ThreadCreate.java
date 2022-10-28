package thread1;

public class ThreadCreate {

	public static void main(String[] args) {

		//线程实例化
		//方法一 创建一个Mythread类继承Thread类，做一个线程子类，(自定义线程类)
		MyThread mythread = new MyThread();
		
		/*
		 * 注意：
		 * 需要调用start方法，使线程启动
		 * start方法会开启一个新的线程，来执行run中的逻辑
		 * 如果直接调用run方法，则线程不会进入就绪状态
		 */
		mythread.start();
		
		
		
		//方法二 通过Runnable接口
		//写法1
//		Runnable runnable1 =() -> {
//			
//			System.out.println("线程一号");
//		};
		
/*		Runnable runnable  = new Runnable() {
			
			public void run() {

				System.out.println("线程二号");
				
			}
		};
		
		Thread thread1 = new Thread(runnable);
		thread1.start();
*/		
		
		
		/*
		 * //使用自定义线程类，在实例化线程对象的同时，进行名称的赋值 //需要在线程内添加对应的构造方法 MyThread mt = new
		 * MyThread("名字");
		 * 
		 * 
		 * System.out.println(mt.getName());
		 */
		
		
		
		
	}
	
}
	class MyThread extends Thread{
		
		/*
		 * 需要重写run方法
		 * 将需要并发执行的任务写到run方法中
		 */
		public void run() {
			for(int i= 0;i<10;i++) {
				System.out.println("子线程的逻辑"+i);
			}
			
		}
		
		
		public MyThread() {}
		public MyThread(String name) {
			super(name);
		//	this.setName(name);
		}
		
	}


