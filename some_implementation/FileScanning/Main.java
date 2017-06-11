package algorithm.some_implementation.FileScanning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanning s = new Scanning();
		Directory root = new Directory();
		root = s.Scanning(new File("C:\\zWork\\IDE\\idea\\IntelliJ IDEA 2017.1.3"));
		root.showDir(root);;
//		try {  
//            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");   
//       } catch (Exception e) {  
//           e.printStackTrace() ;  
//       }  
		
	}
	
}
