package thread3;

public class ThreadLinjieziyuan1 {

    public static void main(String[] args) {

        //同步方法：用关键字  synchronized 修饰的方法就是同步方法
        //实例化四个售票员，用4个线程模拟4个售票员

        Runnable runnable1 = new Runnable() {

            public void run() {
                while (ticketcenter2.restcound > 0) {
                    soldticket();
                }

            }
        };

        //四个线程模拟四个售票员
        Thread thread1 = new Thread(runnable1, "thread-1");
        Thread thread2 = new Thread(runnable1, "thread-2");
        Thread thread3 = new Thread(runnable1, "thread-3");
        Thread thread4 = new Thread(runnable1, "thread-4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }

    /*
     * 同步的方法
     * 静态的方法：同步锁就是 类锁   当前类.class
     * 非静态方法：同步锁是 this
     */

    private synchronized static void soldticket() {
        if (ticketcenter2.restcound <= 0) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + ":" + --ticketcenter2.restcound + "张");
    }


}


class ticketcenter2 {

    //描述剩余的票数
    public static int restcound = 100;

}

