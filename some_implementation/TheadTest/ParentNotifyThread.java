package algorithm.some_implementation.TheadTest;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

/**
 * 这个线程用来发出notify请求
 * @author yinwenjie
 */
public class ParentNotifyThread implements Runnable {
    /**
     * 这个对象的“钥匙”，为每个ChildNotifyThread对象所持有，
     * 模拟这个对象为所有ChildNotifyThread对象都要进行独占的现象
     */
    public static final Object WAIT_CHILEOBJECT = new Object();

    static {
        BasicConfigurator.configure();
    }

    public static void main(String[] args) throws Exception {
        new Thread(new ParentNotifyThread()).start();
    }

    public void run() {
        /*
         * 3个进行WAIT_CHILEOBJECT对象独立抢占的线程，观察情况
         * */
        int maxIndex = 3;
        for(int index = 0 ; index < maxIndex ; index++) {
            ChildNotifyThread childNotify = new ChildNotifyThread();
            Thread childNotifyThread = new Thread(childNotify);
            childNotifyThread.start();
        }

        /*
         * 请在这里加eclipse断点，
         * 以便保证ChildNotifyThread中的wait()方法首先被执行了。
         * 
         * 真实环境下，您可以通过一个布尔型（或者其他方式）进行阻塞判断
         * 还可以使用CountDownLatch类
         * */
        synchronized (ParentNotifyThread.WAIT_CHILEOBJECT) {
            ParentNotifyThread.WAIT_CHILEOBJECT.notify();
        }

        // 没有具体的演示含义；
        // 只是为了保证ParentNotifyThread不会退出
        synchronized (ParentNotifyThread.class) {
            try {
                ParentNotifyThread.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

