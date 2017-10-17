package algorithm.problems;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AboutNumber {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 数组中出现次数超过一半的数
	 * @param array
	 * @return
	 */
	public int MoreThanHalfNum_Solution(int [] array) {
		int i=0;
		int count=0;
		Arrays.sort(array);
		int rst = array[array.length/2];
		while(i != array.length){
			if(array[i++] == rst) count++;
		}
		if(count>array.length/2)
        	return rst;
        else return 0;
    }
	

	/**
	 * 定义圆周上两点的距离s为这两点之间的劣弧对应的圆心角度数(0<=s<=180)，现输入圆周上的n个点（n>=2），
	 * 以角度a表示其位置(0<=a<360)，输入按a从小到大排序。求最远的一对点之间的距离。
	 *
	 */
	public static void ChouShu() {
		Scanner sc = new Scanner(System.in);
		// BufferedReader br = new BufferedReader(sc);
		DecimalFormat df = new DecimalFormat("###.00000000");
		double longest = 0;
		int T = sc.nextInt();
		double[] a = new double[2 * T];
		for (int i = 0; i < T; i++) {
			a[i] = sc.nextDouble();
		}
		// 拼接一个a
		int n = T;
		for (int i = 0; i < T; i++) {
			a[n++] = a[i] + 360;
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] - a[i] > 180) {
					break;
				} else {
					if (a[j] - a[i] > longest)
						longest = a[j] - a[i];
				}
			}
		}
		System.out.println(df.format(longest));
	}

	/**
	 * 第一个数为序列个数，后面n-1个数为序列数 这个序列中 每个数的差值是可以组成一个从1开始到n-1的递增序列
	 */
	public static void IncSeqOfSubNum() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> a = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		String str = sc.nextLine();
		String[] arr = str.split("\\s+");
		for (int i = 0; i < arr.length; i++) {
			a.add(Integer.parseInt(String.valueOf(arr[i])));
		}
		int b[] = new int[a.size() - 1];
		for (int i = 0; i < b.length; i++) {
			b[i] = Math.abs(Math.abs(a.get(i)) - Math.abs(a.get(i + 1)));
		}
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
		int count = 0;
		for (int i = 0; i < b.length; i++) {
			set.add(b[i]);
		}
		for (int k = 1; k <= b.length; k++) {
			if (set.contains(k)) {
				count++;
			}
		}
		if (count == b.length)
			System.out.println("Luck sequence");
		else
			System.out.println("Unluck sequence");

	}

	/**
	 * 1 2 1 2 3 2 3 4 5 4 5 6 7 8 7 8 9 ....
	 */
	public static void aSeq(){
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			boolean flag = true;
			int l = sc.nextInt();
			int num = 1;
			int ch = 1, temp = 1;
			for (int i = 1; i < l; i++) {
				if (flag) {
					num++;
					if (--temp == 0)
						flag = false;
				} else {
					flag = true;
					num--;
					ch++;
					temp = ch;
				}
			}
			System.out.println(num);
		}

	}
	/**
	 * 输入一个二维数组 选择方向 使得距离最短
	 * @param args
	 */
	public static void minDistance() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int min = Integer.MAX_VALUE;
		while (T-- != 0) {
			StringBuilder sb = new StringBuilder();
			int l = sc.nextInt();
			int[][] a = new int[4][l]; // 0E 1S 2W 3N
			for (int i = 0; i < 4; i++) { // 构建二维数组
				for (int j = 0; j < l; j++) {
					a[i][j] = sc.nextInt();
				}
			}
			long sum = 0;
			for (int k = 0; k < l; k++) {
				min = Integer.MAX_VALUE;
				int c = 0; // 记录方向
				for (int i = 0; i < 4; i++) {
					if (a[i][k] < min) {
						min = a[i][k];
						c = i;
					}
				}
				sum = sum + min;
				if (c == 0)
					sb.append("E");
				else if (c == 1)
					sb.append("S");
				else if (c == 2)
					sb.append("W");
				else if (c == 3)
					sb.append("N");
			}
			System.out.println(sum);
			System.out.println(sb.toString());
		}
	}
	/**
	 * 螺旋矩阵
	 * @param n
	 * @param m
	 * @return
	 */
	 static int[][] fill(int n, int m) {
		 int array [][] = new int[n][m];
		 if(n==0 || m==0){
			 return array;
		 }
		 int layers = (Math.min(n, m)-1)/2+1;
		 int inc = 1;
		 for(int i=0;i<layers;i++){
			 for(int k = i;k<m-i;k++) array[i][k] = inc++;
			 for(int j=i+1;j<n-i;j++) array[j][m-i-1] = inc++;
			 for(int k=m-i-2;(k>=i)&&(n-i-1!=i);k--) array[n-i-1][k] = inc++;
			 for(int j=n-i-2;(j>i)&&(m-i-1!=i);j--) array[j][i] = inc++;
		 }
		 return array;
	 }
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int[][] res;
//
//		int _n;
//		_n = Integer.parseInt(in.nextLine().trim());
//
//		int _m;
//		_m = Integer.parseInt(in.nextLine().trim());
//
//		res = fill(_n, _m);
//		int res_rowLength = res.length;
//		int res_colLength = res[0].length;
//		for (int res_i = 0; res_i < res_rowLength; res_i++) {
//			for (int res_j = 0; res_j < res_colLength; res_j++) {
//				System.out.print(String.valueOf(res[res_i][res_j]) + " ");
//			}
//			System.out.println();
//		}
//
//	}
}
