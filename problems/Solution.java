package algorithm.problems;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		int a[] = {-2,-8,-1,-5,-9};
		System.out.println(FindGreatestSumOfSubArray(a));
	}

	/**
	 * 连续子数组最大和(正数)
	 * @param array
	 * @return
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
		if(array.length==0) return 0;
		int thisSum = array[0];
		int maxSum = array[0];
		for(int i=1;i<array.length;i++){
			if(thisSum>0){
				thisSum += array[i];
			}else{
				thisSum = array[i];
			}
			if(thisSum > maxSum){
				maxSum = thisSum;
			}
		}
        return maxSum;
    }
	/**
	 * 螺旋矩阵
	 * 划定范围 判断x和y的+-
	 * 没通过
	 * @param matrix
	 * @return
	 */
	public static ArrayList<Integer> printMatrix(int [][] matrix) {
//		main():
//		int [][] a = new int[5][5];
//		int i=0,j=0;
//		int ict = 0;
//		while(i<5){
//			a[i][j++] = ++ict;
//			if(j==5){
//				j = 0;
//				i++;
//			}
//		}
//		for(int n=0;n<4;n++){
//			for(int m=0;m<4;m++){
//				System.out.print(a[n][m]+" ");
//			}
//			System.out.println();
//		}
//		ArrayList<Integer> list = printMatrix(a);
//		for(Integer i1:list){
//			System.out.println(i1);
//		}
		
		ArrayList<Integer> list = new ArrayList<>();
		if(matrix.length == 0) return null;
		if(matrix.length == 1){ list.add(matrix[0][0]); return list;}
		int x=0,y=0;
		int a = matrix.length;
		int n = 0;
		if(a%2 == 0) n = a*a;
		else n = a*a+1;
	    for(int i=0;i<n;i++){
	    	if((x>=y && x<-y+a-1) || (x==(y-1) && x<(matrix.length-3))){
	    		list.add(matrix[y][x]);
	    		x++;
	    	}else if(x>y && x>=-y+a-1){
	    		list.add(matrix[y][x]);
	    		y++;
	    	}else if(x<=y && x>-y+a-1){
	    		list.add(matrix[y][x]);
	    		x--;
	    	}else if(x<y && x<=-y+a-1){
	    		list.add(matrix[y][x]);
	    		y--;
	    	}
	    }
	    return list;
    }
	
	/**
	 * 输入一个整数数组，把奇数和偶数分开 最简单的是统计奇数的个数k 然后新建一个数组，依次在K位置插入偶数，0位置插入奇数
	 * 
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		int[] res = new int[array.length];
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 != 0) {
				res[j++] = array[i];
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				res[j++] = array[i];
			}
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = res[i];
		}
	}

	/**
	 * 二进制中1的个数 如果一个整数不为0那么至少这个整数有一位是1，把这个整数减1，处在原来整数最右边的1会变为0，之后0的数都变为1，相与就会去除1
	 * 
	 * @param n
	 * @return
	 */
	public int NumberOf1(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n = n & (n - 1);
		}
		return 0;
	}

	/**
	 * 覆盖矩阵
	 * 
	 * @param target
	 * @return
	 */
	public int RectCover(int target) {
		if (target == 0)
			return 0;
		if (target == 1)
			return 1;
		if (target == 2)
			return 2;
		return RectCover(target - 1) + RectCover(target - 2);
	}

	/**
	 * 斐波那契
	 * 
	 * @param n
	 * @return
	 */
	public static int Fibonacci(int n) {
		if (n == 1)
			return 1;
		if (n == 0)
			return 0;
		return Fibonacci(n - 1) + Fibonacci(n - 2);
	}

	/**
	 * 输入一个旋转数组，输出最小元素 二分查找更加优化
	 * 
	 * @param array
	 * @return
	 */
	public int minNumberInRotateArray(int[] array) {
		if (array.length == 0)
			return 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > array[i + 1]) {
				return array[i + 1];
			}
		}
		return array[0];
	}

	/**
	 * 把字符串中的空格替换为%20
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceSpace(StringBuffer str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ')
				str.replace(i, i + 1, "%20");
		}
		// return str.toString().replaceAll("\\s", "%20");
		return str.toString();

	}

}
