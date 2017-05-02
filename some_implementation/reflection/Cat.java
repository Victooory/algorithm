package algorithm.some_implementation.reflection;

public class Cat{
	private String name;
	private boolean sex;    //0女  1男
	private int age;
	public Cat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cat(String name, boolean sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Cat [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}
