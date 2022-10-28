package thread1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 线程同步容器
 */
public class ThreadSynchronizeList {

    //list本身是线程不可见，不同步的
    //加了volatile保证线程可见，用synchronizeList保证线程同步
    volatile List list = Collections.synchronizedList(new LinkedList<>());

    public static void main(String[] args) {

    }
}
