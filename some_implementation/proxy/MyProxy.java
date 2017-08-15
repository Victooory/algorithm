package algorithm.some_implementation.proxy;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



/**
 * 动态代理
 * 
 * @author 10142
 *
 */
public class MyProxy {
	public interface IHello {
		
		void sayHello();
	}

	static class Hello implements IHello {
		public void sayHello() {
			System.out.println("Hello world!!");
		}
	}

	// 自定义InvocationHandler
	static class HWInvocationHandler implements InvocationHandler {
		// 目标对象
		private Object target;

		public HWInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("------插入前置通知代码-------------");
			// 执行相应的目标方法
			Object rs = method.invoke(target, args);
			System.out.println("------插入后置处理代码-------------");
			return rs;
		}
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException,
			IllegalArgumentException, InvocationTargetException {
		// 生成$Proxy0的class文件
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		// 获取动态代理类
		Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class);
		// 获得代理类的构造函数，并传入参数类型InvocationHandler.class
		Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
		// 通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
		IHello iHello = (IHello) constructor.newInstance(new HWInvocationHandler(new Hello()));
		// 通过代理对象调用目标方法
		iHello.sayHello();

		// 第二种方法
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		IHello ihello2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), // 加载接口的类加载器
				new Class[] { IHello.class }, // 一组接口
				new HWInvocationHandler(new Hello())); // 自定义的InvocationHandler
		ihello2.sayHello();
	}

	/**
	 * Spring aop动态代理基本原理
	 */
	static class SpringAop implements InvocationHandler {
		// 目标对象
		private Object target;

		public SpringAop(Object target){
            this.target = target;
        }

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// BeforeAdvice
			Object retVal = null;
			try {
				// AroundAdvice
				retVal = method.invoke(target, args);
				// AroundAdvice
				// AfterReturningAdvice
			} catch (Throwable e) {
				// AfterThrowingAdvice
			} finally {
				// AfterAdvice
			}
			return retVal;
		}

	}

}