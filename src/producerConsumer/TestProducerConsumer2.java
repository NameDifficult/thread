package producerConsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProducerConsumer2 {
    public static void main(String[] args) {

        MyList<String> myList = new MyList<>();

        for (int i = 0 ; i < 10 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(myList.get());
                }
            },"C" + i).start();
        }

        for (int i = 0 ; i < 2 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0 ; j < 6 ; j++){
                        myList.put(Thread.currentThread().getName() + " " + j);
                    }
                }
            },"P" + i).start();
        }

    }
}

class MyList2<T>{
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10 ;
    private int count ;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    //-------------------------------------------------------------------------------------------------------------
    public void put(T t){
        lock.lock();
        while (lists.size() == MAX){
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        condition2.notifyAll();
        lock.unlock();
    }
    //-------------------------------------------------------------------------------------------------------------
    public  T get(){
        T t = null ;
        lock.lock();
        while (lists.size() == 0){
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        --count;
        condition1.notifyAll();
        lock.unlock();
        return t;
    }
}
