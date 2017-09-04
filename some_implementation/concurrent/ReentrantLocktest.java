package algorithm.some_implementation.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocktest {
	public ReentrantLock Lock = new ReentrantLock();
	public static Integer count = 0;
	public Runnable a = new Runnable() {
		public void run() {
			synchronized (count) {
				try {
					System.out.println("aget");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("a" + count);
			}
		}
	};

	public Runnable b = new Runnable() {
		@Override
		public void run() {
			synchronized (count) {
				try {
					System.out.println("bget");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("b" + count);
			}
		}
	};
	public Runnable c = new Runnable() {
		public void run() {
			Lock.lock();
			try {
				System.out.println("cget");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("c" + count);
			Lock.unlock();
		}
	};
	public Runnable d = new Runnable() {
		public void run() {
			Lock.lock();

			System.out.println("dget");

			System.out.println("d" + count);
			Lock.unlock();
		}
	};

	public static void main(String[] args) throws InterruptedException {
		ReentrantLocktest re = new ReentrantLocktest();
		Thread a = new Thread(re.c);
		Thread b = new Thread(re.d);
		a.start();
		b.start();

	}
}
