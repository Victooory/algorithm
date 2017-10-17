package algorithm.some_implementation.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 公共常量
 * @author 骆昊
 *
 */
class Constants1 {
    public static final int MAX_BUFFER_SIZE = 10;
    public static final int NUM_OF_PRODUCER = 2;
    public static final int NUM_OF_CONSUMER = 3;
}

/**
 * 工作任务
 * @author 骆昊
 *
 */
class Task1 {
    private String id;  // 任务的编号

    public Task1() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}

/**
 * 消费者
 * @author 骆昊
 *
 */
class Consumer1 implements Runnable {
    private List<Task1> buffer;

    public Consumer1(List<Task1> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            synchronized(buffer) {
                while(buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task1 task = buffer.remove(0);
                buffer.notifyAll();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            }
        }
    }
}

/**
 * 生产者
 * @author 骆昊
 *
 */
class Producer1 implements Runnable {
    private List<Task1> buffer;

    public Producer1(List<Task1> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (buffer) {
                while(buffer.size() >= Constants1.MAX_BUFFER_SIZE) {
                    try {
                        buffer.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task1 task = new Task1();
                buffer.add(task);
                buffer.notifyAll();
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            }
        }
    }

}

public class preConsumerProducer  {

    public static void main(String[] args) {
        List<Task1> buffer = new ArrayList<>(Constants1.MAX_BUFFER_SIZE);
        ExecutorService es = Executors.newFixedThreadPool(Constants1.NUM_OF_CONSUMER + Constants1.NUM_OF_PRODUCER);
        for(int i = 1; i <= Constants1.NUM_OF_PRODUCER; ++i) {
            es.execute(new Producer1(buffer));
        }
        for(int i = 1; i <= Constants1.NUM_OF_CONSUMER; ++i) {
            es.execute(new Consumer1(buffer));
        }
    }
}