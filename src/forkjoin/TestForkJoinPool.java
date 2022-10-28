package forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask框架
 * ForkJoinPool由ForkJoinTask数组和ForkJoinWorkerThread数组组成
 * ForkJoinTask数组负责将存放程序提交给ForkJoinPool
 * ForkJoinWorkerThread负责执行这些任务。
 * @author Mi
 *
 */
public class TestForkJoinPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//需要ForkJoinPool的支持
		ForkJoinPool pool = new ForkJoinPool();		
		//开始时间
		Instant startInstant = Instant.now();
		//创建对象，并穿入初值
		ForkJoinTask<Long> task = new ForkJoinF(0L, 1000000000L);
		Long sumLong = pool.invoke(task);
		System.out.println(sumLong);
		//结束时间
		Instant endInstant = Instant.now();
		//计算出结束时间，并输出
		System.out.println("耗费时间为：" + Duration.between(startInstant, endInstant).toMillis());
		
		
		
		
		
		
		//开始时间
		Instant startInstant1 = Instant.now();
		CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
			long l = 0L;
            for (long i = 0 ; i <= 1000000000L ; i++){
                l +=i;
            }
            return l;
		});
		System.out.println(completableFuture.get());
		new Thread().start();
		Instant endInstan1 = Instant.now();
		System.out.println("直接计算耗费时间为：" + Duration.between(startInstant1, endInstan1).toMillis());

	}

}


class ForkJoinF extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4216612351049880926L;
	
	//设立初值
	private long start;
	
	//设立末值
	private long end;
	
	//设立临界值，就是拆分后的程度
	private static final long thurshold = 100000L;
	
	public ForkJoinF (long start,long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		
		long length = end - start;
		
		//判断是否需要拆分，不需要则直接计算
		if(length<=thurshold) {
			long sum = 0L;
			for(long i=start;i<=end;i++) {
				sum += i;
			}
			return sum;
		}
		else {
			
			long middle = (start + end)/2;
			
			//进行拆分，同时压入到线程队列
			ForkJoinF left = new ForkJoinF(start, middle);
			left.fork();
			
			ForkJoinF rigth = new ForkJoinF(middle+1, end);
			rigth.fork();
			//汇总
			return left.join() + rigth.join();
			
		}
	}
	
}