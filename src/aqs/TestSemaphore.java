package aqs;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {

    public static void main(String[] args) {

        //线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), r -> new Thread(r, "MyThread:" + r.toString().substring(46, 55)));

        //参数表示信号量，表示允许多少个线程执行
        Semaphore semaphore = new Semaphore(1);
        threadPoolExecutor.execute(() -> {
            try {
                //信号量+1
                semaphore.acquire();
                System.out.println("Thread1 : start");
                Thread.sleep(1000);
                System.out.println("Thread1 : end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //信号量-1
                semaphore.release();
            }
        });

        threadPoolExecutor.execute(() -> {
            try {
                //信号量+1
                semaphore.acquire();
                System.out.println("Thread2 : start");
                Thread.sleep(1000);
                System.out.println("Thread2 : end");
            } catch (Exception e) {
            } finally {
                //信号量-1
                semaphore.release();
            }
        });
        threadPoolExecutor.shutdown();

    }

}
