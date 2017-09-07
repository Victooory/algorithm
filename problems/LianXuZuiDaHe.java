package algorithm.problems;

import java.util.Scanner;
/**
 * 给定无序整数序列求连续子串最大和
 * @author 10142
 *
 */
public class LianXuZuiDaHe {

    public static void main(String[] args) {
    	int i = 0;
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int []a = new int[len];
        int []ans = new int[len];
        for(i=0;i<len;i++){
        	a[i] = in.nextInt();
        }
        int mark;
        ans[0] = mark = a[0];  
		int sum = 0;
		for (i = 1; i < len; i++) {
			ans[i]=Math.max(ans[i-1]+a[i],a[i]);  
            if(mark<ans[i])   mark=ans[i]; 
		}
		System.out.println(mark);
    }	
}
