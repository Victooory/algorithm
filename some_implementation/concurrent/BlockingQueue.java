package algorithm.some_implementation.concurrent;

public interface BlockingQueue<E>{
    public void put(E e) throws InterruptedException;
    public E take() throws InterruptedException;
}