package algorithm.some_implementation.TheadTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

/**
 * 用来在启动后，等待唤醒
 * @author yinwenjie
 */
public class SyncThread implements Runnable {

    /**
     * 日志
     */
    private static final Log LOGGER = LogFactory.getLog(SyncThread.class);

    private Integer value;

    private static Integer NOWVALUE;

    static {
        BasicConfigurator.configure();
    }

    public SyncThread(int value) {
        this.value = value;
    }
    
    //相当于对这个类的class对象进行同步检查   
    //如果用synchronized(this){} 对拥有这个方法的对象进行检查 会读脏数据
    private void doOtherthing() {
        synchronized (SyncThread.class) {
            NOWVALUE = this.value;
            LOGGER.info("当前NOWVALUE的值：" + NOWVALUE);
        }
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        Long id = currentThread.getId();
        this.doOtherthing();
    }

    public static void main(String[] args) throws Exception {
        Thread syncThread1 = new Thread(new SyncThread(10));
        Thread syncThread2 = new Thread(new SyncThread(100));

        syncThread1.start();
        syncThread2.start();
    }
}