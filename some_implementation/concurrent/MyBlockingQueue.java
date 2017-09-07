package algorithm.some_implementation.concurrent;


import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class MyBlockingQueue<E> implements BlockingQueue<E> {
	private LinkedList<E> list = new LinkedList<>();
	private int capacity;

    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();
    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();
    private final AtomicInteger count = new AtomicInteger();
   // private final AtomicInteger count = new AtomicInteger();
	/**
	 * 初始化容量为5
	 */
	public MyBlockingQueue() {
		this.capacity = 5;
	}

	public MyBlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void put(E e) throws InterruptedException {
		final ReentrantLock putLock = this.putLock; 
		final AtomicInteger count = this.count;
		int c = -1;
		putLock.lockInterruptibly();
		try{
		while(count.get() == capacity){
			notFull.await();
		}
		synchronized (list) {
			list.add(e);
		}
		c = count.getAndIncrement();
		if(c + 1 < capacity){
			notFull.signal();
		}
		}finally {
			putLock.unlock();
		}
		
		if(c == 0){			//signal notEmpty 如果c为0则说明之前队列为空，也就是说有可能有线程阻塞在NotEmpty条件变量上
			takeLock.lock();
			notEmpty.signal();
			takeLock.unlock();
		}
		
		
	}
	
	@Override
	public E take() throws InterruptedException {
		final ReentrantLock takeLock = this.takeLock;
		final AtomicInteger count = this.count;
		E e = null;
		int c = -1;
		takeLock.lockInterruptibly();
		try{
		while(count.get() == 0){
			notEmpty.await();
		}
		synchronized (list){
			e = list.pollFirst();
		}
		c = count.getAndDecrement();
		if(c > 1){
			notEmpty.signal();
		}
		}finally {
			takeLock.unlock();
		}
		if(c == capacity){		//signal notFull
			putLock.lock();
			notFull.signal();
			putLock.unlock();
		}
		
		return e;

	}


}
