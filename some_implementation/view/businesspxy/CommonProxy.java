package algorithm.some_implementation.view.businesspxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class HandlerInvocation {
	private Iterator<Interceptor> iterator;
	private Object proxy;
	private Method method;
	private Object[] args;

	public HandlerInvocation(Object proxy, Method method, Object[] args, Iterator<Interceptor> iterator) {
		this.proxy = proxy;
		this.method = method;
		this.args = args;
		this.iterator = iterator;
	}

	public Object invoke() throws Exception {
		Object result = null;
		if (iterator != null && iterator.hasNext()) {
			Interceptor interceptor = iterator.next();
			result = interceptor.intercept(this);
		} else {
			result = method.invoke(proxy, args);
		}
		return result;
	}
}

interface Interceptor {
	public Object intercept(HandlerInvocation invocation) throws Exception;
}

public class CommonProxy implements InvocationHandler {
	private static List<Interceptor> interceptorList = new ArrayList<Interceptor>();
	private Object target;

	public static void initInterceptors(List<Interceptor> list) {
		interceptorList.addAll(list);
	}

	public CommonProxy(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		HandlerInvocation handlerInvocation = new HandlerInvocation(target, method, args, interceptorList.iterator());
		return handlerInvocation.invoke();
	}

	public static void main(String[] args) {
		List<Interceptor> list = new ArrayList<Interceptor>();
		list.add(new LoggerInterceptor());
		CommonProxy.initInterceptors(list);
		MyBusinessImpl myBusinessImpl = new MyBusinessImpl();
		MyBusiness myBusiness = (MyBusiness) Proxy.newProxyInstance(MyBusiness.class.getClassLoader(),
				new Class<?>[] { MyBusiness.class }, new CommonProxy(myBusinessImpl));
		myBusiness.xxx1("aaa");
		myBusiness.xxx2(123);
	}
}

class LoggerInterceptor implements Interceptor {
	public Object intercept(HandlerInvocation invocation) throws Exception {
		System.out.println("pre handle");
		Object result = invocation.invoke();
		System.out.println("post handle");
		return result;
	}
}