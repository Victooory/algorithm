package algorithm.some_implementation.view.businesspxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class Myproxy {
	static class BusInvocationHandler implements InvocationHandler {

		private Object target;
		
		public BusInvocationHandler(Object target) {
			this.target = target;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before");
			Object rs = method.invoke(target, args);
			System.out.println("after");
			return rs;
		}
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		MyBusiness mb = (MyBusiness) Proxy.newProxyInstance(MyBusiness.class.getClassLoader(),new Class[]{ MyBusiness.class}, new BusInvocationHandler(new MyBusinessImpl()));
		mb.xxx1("1");
		mb.xxx2(22);
	}
}
