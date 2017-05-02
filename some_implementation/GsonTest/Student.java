package algorithm.some_implementation.GsonTest;

import java.util.Arrays;

public class Student {
	private String Name = null;
	private int age = 0;
	private Book [] book;
	public Student() {
		
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "Student [Name=" + Name + ", age=" + age + ", book=" + Arrays.toString(book) + "]";
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Book[] getBook() {
		return book;
	}
	public void setBook(Book[] book) {
		this.book = book;
	}
	public Student(String name, int age, Book[] book) {
		super();
		Name = name;
		this.age = age;
		this.book = book;
	}
}
