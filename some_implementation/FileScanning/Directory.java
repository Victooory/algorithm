package algorithm.some_implementation.FileScanning;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.sound.midi.Soundbank;

import algorithm.some_implementation.AboutTree.TreeNode;

/**
 * 目录结构
 * 
 * @author 10142
 *
 */
public class Directory {

	private String name; // 名字
	private long size; // 大小
	private Set<Directory> son; // 子目录
	private PrintWriter out;
	private File file;
	
	public Directory() {
		son = new HashSet<>();

	}

	public Directory(String name) {
		son = new HashSet<>();
		this.name = name;
	}

	// 添加子目录
	public Directory addDirectory(String name) {
		Directory directory = new Directory(name);
		son.add(directory);
		return directory;
	}

	// 显示所有文件(不包括文件夹)
	public void display() {
		if (son != null) {
			Iterator<Directory> it = son.iterator();
			while (it.hasNext()) {
				Directory directory = it.next();
				directory.display();
				if (directory.isDirectory()) { // 判断son是否是文件夹
					System.out.println(directory.getName() + " (" + directory.getSize() / 1024 + "KB)");
				}
			}
		}
	}

	// 显示目录
	public void showDir(Directory directory) { // 先判断有无文件
		try {
			out = new PrintWriter(new FileOutputStream("C:\\zWork\\Workspaces\\jee\\test\\src\\result.txt"), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		this.showDir(1, directory); // 显示目录的方法
	}

	public void showDir(int indent, Directory directory) {
		for (int i = 0; i < indent; i++)
			out.print(" ");
		out.print("|");
		for (int i = 0; i < indent / 4; i++)
			out.print("—");
		out.println(directory.getStrOfDir());
		if (directory.isDirectory()) {
			Iterator<Directory> it = directory.getSon().iterator(); // son
			while (it.hasNext())
				showDir(indent + 4, it.next());
		}
	}

	// 是否是文件夹
	public boolean isDirectory() {
		if (this.getSon().iterator().hasNext())
			return true;
		else
			return false;
	}

	// 一个文件属性的字符串，格式化输出文件大小
	public String getStrOfDir() {
		int size;
		String rst;
		if (this.getSize() < 1024) {
			rst = new String(this.getName() + " (" + this.getSize() + "B)");
		} else if (this.getSize() < 1024 * 1024) {
			rst = new String(this.getName() + " (" + this.getSize() / 1024 + "KB)");
		} else if (this.getSize() < 1024 * 1024 * 1024) {
			rst = new String(this.getName() + " ("
					+ new DecimalFormat("0.00").format((double) (this.getSize() / 1024) / 1024) + "MB)");
		} else {
			rst = new String(this.getName() + " ("
					+ new DecimalFormat("0.00").format((double) (this.getSize() / 1024 / 1024) / 1024) + "GB)");
		}
		return rst;
	}

	//查询目录下的指定目录
	public void getDir(Directory directory,String dirName){
		if (son == null)
			return;
		for(Directory dir:directory.son){
			if(dir.getName().equals(dirName)){
				System.out.println(dir.getFile().getPath());
				if(dir.getFile().isDirectory()){
					for(Directory son:dir.son)
					getDir(son, dirName);
				}
			}else{
				if(dir.getFile().isDirectory()){
					for(Directory son:dir.son)
					getDir(son, dirName);
				}
			}
		}
	}
	// ------getter and setter --------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Set<Directory> getSon() {
		return son;
	}

	public void setSon(Set<Directory> son) {
		this.son = son;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
