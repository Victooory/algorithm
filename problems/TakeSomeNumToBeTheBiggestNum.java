package algorithm.problems;

import java.util.Scanner;
import java.util.Stack;

/**
 * 给定一个十进制的正整数number，选择从里面去掉一部分数字，希望保留下来的数字组成的正整数最大。 
 * 思路：从左到右找第一次出现比后面小的数，如果没找到，就从后面删除
 * @author 10142
 *
 */
public class TakeSomeNumToBeTheBiggestNum {
	  public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        while (scanner.hasNext()) {
	        	StringBuilder str = new StringBuilder();
	            str.append(scanner.next());
	            int cnt = scanner.nextInt();
	            int count = 0;
	            while(count < cnt){
	            	int len = str.length() - 1;
	            	int s = 0;
	            	while(s<len  && str.codePointAt(s) >= str.codePointAt(s+1))
	            		s++;
	            	str.deleteCharAt(s);
	            	count++;
	            }
	            System.out.println(str);
	        }
	    }

/*	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int cnt = sc.nextInt();	
		int count =0;
		StringBuffer str = new StringBuffer(String.valueOf(number));
		for(int i=0;i<10;i++){
			for(int j=0;j<str.length();j++){
				if( Integer.toString(i).charAt(0) == str.toString().charAt(j)){
					if(count == cnt)
						break;
					count++;
					str.delete(j, j+1);
					j--;					//如果不J--的话 length会更新，会错过一个的检查
				}
			}
		}
		System.out.println(str);
	}*/
	

}
