package producerConsumer;

import java.util.LinkedList;

public class TestProducerConsumer {
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

class MyList<T>{
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10 ;
    private int count ;
    //-------------------------------------------------------------------------------------------------------------
    public synchronized void put(T t){
        while (lists.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();
    }
    //-------------------------------------------------------------------------------------------------------------
    public synchronized T get(){
        T t = null ;
        while (lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        --count;
        this.notifyAll();
        return t;
    }
}
