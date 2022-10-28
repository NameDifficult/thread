package aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Mi
 * <p>
 * CyclicBarrier : 循环栅栏 当参数值达到指定值后执行某个线程
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        //线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), r -> new Thread(r, "MyThread"));

        Future<String> submit = threadPoolExecutor.submit(() -> Thread.currentThread().toString());
        System.out.println(submit);
        //执行了10次await()后就会执行第二个参数的runnable
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("满人，开栅栏");
        });
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    //等待。直到开栅栏，也就是在执行了10次await后，才会唤醒开栅栏前的10个线程
                    cyclicBarrier.await(10, TimeUnit.MINUTES);
                    System.out.println(Thread.currentThread().getName() + "结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        threadPoolExecutor.shutdown();
    }

}
