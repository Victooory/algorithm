package algorithm.problems;

import java.util.Scanner;

/**
 * 输入位数打印所有格雷码
 * @author dong
 *
 */
public class GrayCode {
	public String[] sovle(int n){
		String[] graycode = new String[(int)Math.pow(2, n)];
		if(n==0){
			graycode[0] = "0";
			return graycode;
		}
		if(n==1){
			graycode[0] = "0";
			graycode[1] = "1";
			return graycode; 
		}
		String[] last = sovle(n-1);					//递归求前一位的格雷码，后面的格雷码就是分别在开头加0和1
		for(int i=0;i<last.length;i++){
			graycode[i] = "0" + last[i];
			graycode[graycode.length - 1 - i] = "1" +last[i];			//保持对称
		}
		return graycode;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		GrayCode grayCode = new GrayCode();
		String[] array =  grayCode.sovle(n);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
	}
}
