package algorithm.some_implementation.concurrent.DeadLock;

public class ThreadB extends ThreadA implements Runnable{


	@Override
	public void run() {
		synchronized(b){
			System.out.println("b...b");
			synchronized(a){
				System.out.println("b...a");
			}
		}
		
	}

}
