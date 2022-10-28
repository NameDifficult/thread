package thread2;

public class Threadsleep {

	public static void main(String[] args) {


		Runnable runnable = new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		};
		
		Thread thread1 = new Thread(runnable);
		thread1.start();
		

	}

}
















/*
 * Thread thread = new Thread(new Runnable() {
 * 
 * public void run() { for (int i = 0; i < 10; i++) { System.out.println(i); try
 * { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();
 * }
 * 
 * }
 * 
 * } }); thread.start();
 */


