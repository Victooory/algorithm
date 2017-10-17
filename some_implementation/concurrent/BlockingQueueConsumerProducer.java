package algorithm.some_implementation.concurrent;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.tools.JavaCompiler;

/**
 * 公共常量
 * @author 骆昊
 *
 */
class Constants {
    public static final int MAX_BUFFER_SIZE = 10;
    public static final int NUM_OF_PRODUCER = 2;
    public static final int NUM_OF_CONSUMER = 3;
}

/**
 * 工作任务
 * @author 骆昊
 *
 */
class Task {
    private String id;  // 任务的编号

    public Task() {
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
class Consumer implements Runnable {
    private java.util.concurrent.BlockingQueue<Task> buffer;

    public Consumer(java.util.concurrent.BlockingQueue<Task> buffer2) {
        this.buffer = buffer2;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Task task = buffer.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 生产者
 * @author 骆昊
 *
 */
class Producer implements Runnable {
    private java.util.concurrent.BlockingQueue<Task> buffer;

    public Producer(java.util.concurrent.BlockingQueue<Task> buffer2) {
        this.buffer = buffer2;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Task task = new Task();
                buffer.put(task);
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
public class BlockingQueueConsumerProducer {
	 public static void main(String[] args) {
	        java.util.concurrent.BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(10);
	        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
	        for(int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
	            es.execute(new Producer(buffer));
	        }
	        for(int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
	            es.execute(new Consumer(buffer));
	        }
	    }
}
