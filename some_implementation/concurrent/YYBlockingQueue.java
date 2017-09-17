package algorithm.some_implementation.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class YYBlockingQueue<E> implements BlockingQueue {
	private final int capacity = 10000;
	private final AtomicInteger count = new AtomicInteger(0);
	private Node head;
	private Node last;
	private final ReentrantLock takeLock = new ReentrantLock();
	private final Condition notEmpty = takeLock.newCondition();
	private final ReentrantLock putLock = new ReentrantLock();
	private final Condition notFull = putLock.newCondition();

	public YYBlockingQueue() {
		last = head = new Node(null);
	}

	public void put(Object e) throws InterruptedException {
		if (e == null)
			throw new NullPointerException();
		int c = -1;
		Node node = new Node(e);
		ReentrantLock putLock = this.putLock;
		AtomicInteger count = this.count;
		putLock.lockInterruptibly();
		try {
			while (count.get() == capacity) {
				notFull.await();
			}
			enqueue(node);
			c = count.getAndIncrement();
			if (c + 1 < capacity)
				notFull.signal();
		} finally {
			putLock.unlock();
		}
		if (c == 0)
			signalNotEmpty();
	}

	public Object take() throws InterruptedException {
		E x;
		int c = -1;
		final AtomicInteger count = this.count;
		final ReentrantLock takeLock = this.takeLock;
		takeLock.lockInterruptibly();
		try {
			while (count.get() == 0) {
				notEmpty.await();
			}
			x = dequeue();
			c = count.getAndDecrement();
			if (c > 1)
				notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
		if (c == capacity)
			signalNotFull();
		return x;
	}

	private void enqueue(Node node) {
		last = last.next = node;
	}

	private E dequeue() {
		Node h = head;
		Node first = h.next;
		h.next = h; // help GC
		head = first;
		E x = (E) first.item;
		first.item = null;
		return x;
	}

	private void signalNotEmpty() {
		final ReentrantLock takeLock = this.takeLock;
		takeLock.lock();
		try {
			notEmpty.signal();
		} finally {
			takeLock.unlock();
		}
	}

	private void signalNotFull() {
		final ReentrantLock putLock = this.putLock;
		putLock.lock();
		try {
			notFull.signal();
		} finally {
			putLock.unlock();
		}
	}

	static class Node<E> {
		E item;
		Node next;

		Node(E x) {
			item = x;
		}
	}
}
