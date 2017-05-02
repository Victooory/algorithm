package algorithm.some_implementation.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class Instance {
	/**
	 * 获取Class实例的三种方式
	 */
	public static void TogetClass() {
		//通过类路径
		try {
			Class c1 = Class.forName("java.lang.Integer");
			System.out.println(c1.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//通过对象需要实例化的对象
		String s = null;
		Class c2 = s.getClass();
		System.out.println(c2.getName());
		
		//通过字面量，可以对基本类型  (推荐)
		Class c3 = int.class;
		System.out.println(c3.getName());
	}
	/**
	 * 获取类中方法的操作  
	 * 获取父类或实现的接口
	 * 
	 */
	public static void getMethod() {
		Class<?> c = String.class;
		Constructor<?> cons[] = c.getConstructors();   //getDeclaredConstructors() 获取所有构造函数	
		//获取公共的构造函数
		for(Constructor<?> constructor : cons){
			System.out.println(constructor);
		}
		//获取所有方法 
		Method m[] = c.getDeclaredMethods();
		for(Method method : m){
			System.out.println(method);
		}
		//对实例对象调用方法
		try {
			Constructor<?> cc = c.getConstructor();
			Method  m2= c.getMethod("length", null);
			int i=(int)m2.invoke(cc.newInstance(), null);			//obj 要一个实例化的对象
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取父类
		Class<?> cfather  = c.getSuperclass();
		//获取接口
		Class<?>[] cinterface = c.getInterfaces();			//接口可以多继承所以用数组
	}
	public static void main(String[] args) {
		Class<?> c = String.class;
		//通过构造方法实例对象
		try {
			Constructor<?> constructor = c.getDeclaredConstructor(String.class);
			String s = (String) constructor.newInstance("abc");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//通过set方法 实例对象
		Class<?> c1 = Cat.class;
		try {
			Method m1 = c1.getMethod("setName", String.class);
			Method m2 = c1.getMethod("setSex", boolean.class);
			Method m3 = c1.getMethod("getName");
			Object cat = c1.newInstance();
			m1.invoke(cat, "小扬");
			m2.invoke(cat, true);
			System.out.println("调用set方法："+cat);
            System.out.println("调用get方法："+m3.invoke(cat));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//获得类的所有属性属性
		Class<?> c2 = Cat.class;
		try {
			Object object = c2.newInstance();
			Field[] fields = c2.getDeclaredFields();
			for(Field field:fields){
				System.out.println(field);
			}
			Field field = c2.getDeclaredField("name");
			field.setAccessible(true);
			field.set(object, "Dong");
			System.out.println(field.get(object));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
