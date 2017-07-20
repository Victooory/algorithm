package algorithm.some_implementation.reflection;

import java.lang.reflect.Proxy;

public class test {
	public static void main(String[] args) throws ClassNotFoundException {
		Cat cat = new Cat();
		Class cls = cat.getClass();
		cls = Class.forName("Cat");
		cls = Cat.class;
	}
}
