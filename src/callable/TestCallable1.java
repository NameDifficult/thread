package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable1 {

	public static void main(String[] args) {
		
		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
			//定义一个变量为sun，用于存储i的和
			int sum = 0;
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//求1000内所有数的和
			for (int i = 0; i < 100; i++) {
				sum = sum+i;
			}
			return sum;
		}).whenComplete((v,e) -> {
			System.out.println("e="+v);
		}).exceptionally(e ->{
			return null;
		});
		
		System.out.println("你好");
		
		
		
		
		
		//创建Demo对象
		Demo demo = new Demo();
		//创建FutureTask实现类，用于接收运算结果
				//FutureTask是Future接口的实现类
				FutureTask<Integer> task = new FutureTask<>(demo);
		new Thread(task).start();
		
		//接收线程运算后的结果
		try {
			Thread.sleep(4000);
			//只有Thread线程执行完毕才会打印结果
			Integer integer = task.get();
			System.out.println(integer);
			//下面这一行，只有等上面两行执行完毕，才执行
			System.out.println("------------------------------------");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
			
		
	}
}

class Demo implements Callable<Integer>{
	//重写call方法
	public Integer call() throws Exception {
		//定义一个变量为sun，用于存储i的和
		int sum = 0;
		//求1000内所有数的和
		for (int i = 0; i < 100; i++) {
			sum = sum+i;
		}
		System.out.println("-------------------------------------");
		return sum;
	}
}
