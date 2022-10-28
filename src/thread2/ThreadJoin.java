package thread2;

public class ThreadJoin {

	public static void main(String[] args) {

		//线程1
		Thread threadone = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("A : " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	
		//线程2
		Thread threadtwo = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("B->"+threadone.getState());
					//就是将当前线程加入到了threadone的后面,从下面这条语句开始
					threadone.join();
					System.out.println("执行完毕");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		//获取线程的状态
		System.out.println(threadone.getState());
		
		threadone.start();
		threadtwo.start();
		
		
	}
}
