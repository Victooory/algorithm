package algorithm.some_implementation.FileScanning;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 对文件扫描，保存为一整个目录
 * @author 10142
 *
 */
public class Scanning {

	private Directory directory;
	public Scanning() {
		directory = new Directory();
	}
	
	//扫描形成指定文件目录结构
	public long ScanningAll(Directory directory,File F){			
		long size = 0;	
		File[] files = F.listFiles();
		if (files != null) {
			for (File file : files) {
				long cursize = 0;
				Directory son = directory.addDirectory(file.getName());
				if (file.isDirectory()) {
					cursize = this.ScanningAll(son,file);
				} else {
					cursize = file.length();
				}
				size +=cursize;
				son.setSize(cursize);
			}
		}
		return size;
	}
	
	public Directory Scanning(File F) {	
		directory.setSize(this.ScanningAll(directory, F));			//返回所有文件的size 给根目录
		directory.setName(F.getName());
		return directory;
	}
	
}

