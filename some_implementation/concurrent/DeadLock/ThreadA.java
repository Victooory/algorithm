package algorithm.some_implementation.concurrent.DeadLock;

public class ThreadA implements Runnable{
	public static Object a= new Object();
	public static Object b= new Object();
	public Runnable threadb = null; 

	@Override
	public void run() {
		synchronized(a){
			try {
				Thread.sleep(500);
				System.out.println("a...a");
				synchronized(b){
					System.out.println("a...b");	//去掉这个同步块就不会死锁
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA();
		ThreadB b  = new ThreadB();
		Thread threada = new Thread(a);
		Thread threadb= new Thread(b);
		threada.start();
		Thread.sleep(200);
		threadb.start();
	}
}
