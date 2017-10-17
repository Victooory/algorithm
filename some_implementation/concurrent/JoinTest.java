package algorithm.some_implementation.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JoinTest implements Runnable{

	@Override
	public void run() {
		System.out.println("Thread");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread a1 = new Thread(new JoinTest());
		Thread a2 = new Thread(new JoinTest());
		Thread a3 = new Thread(new JoinTest());
		a1.start();
		a2.start();
		a3.start();
		a1.join();
		a2.join();
		a3.join();
		System.out.println("over");
		
	}
}
