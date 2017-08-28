package algorithm.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 给定无序整数求其第k大的数
 * 滴滴笔试题
 * @author 10142
 *
 */
public class KLarge {
	public static void Knum(int a[],int n){		//由大到小
		int temp = 0;
		for(int i=0;i<a.length;i++){
			if(n>a[i]){
				temp = a[i];
				a[i] = n;
				n = temp;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a[] = null;
		int n=0;
		String all = in.nextLine();
		String c[] = all.split("\\s+");
		a = new int[c.length];
		for(int i=0;i<c.length;i++){
			a[i] = Integer.parseInt(c[i]);
		}
		int k = in.nextInt();			//第K个数
		int b[] = new int[k]; 
		
		for(int i=0;i<a.length;i++){
			Knum(b, a[i]);
		}
		System.out.println(b[k-1]);
	}

}
