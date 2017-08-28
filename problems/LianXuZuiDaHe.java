package algorithm.problems;

import java.util.Scanner;
/**
 * 给定无序整数序列求连续子串最大和
 * @author 10142
 *
 */
public class LianXuZuiDaHe {

    public static void main(String[] args) {
    	int a[]=null;
    	int i = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
           a[i] = in.nextInt();
           i++;
        }
        int Max = a[0];
		int sum = 0;
		for (i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > Max) {
				Max = sum;
			}
			if (a[i]<0) {
				sum = 0;
			}
		}
		System.out.println(Max);
    }
	
}
