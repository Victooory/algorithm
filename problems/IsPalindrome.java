package algorithm.problems;

import java.util.Scanner;

public class IsPalindrome {
	public static void main(String[] args) {
		boolean flag = false;
		int low,high;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			char[] c = sc.nextLine().toCharArray();
			
			flag = true;
			low = 0;
			high = c.length-1;
			while(low<high){		//判断这部分是不是回文
				if(c[low] == c[high]){
					low++;
					high--;
				}else{
					flag = false;
					break;
				}
				flag = true;
			}
			System.out.println(flag);
		}
	}
	/**
	 * 最大回文前缀
	 * @param chs
	 * @return
	 */
	public static int maxPrefixPlindrome(char[] chs) {
	        int length = chs.length;
	        for (int i = length - 1; i > 0; i--) {
	            if (isPlindrome(chs,0,i)){
	                return i + 1;
	            }
	        }
	        return 1;
	    }
	/**
	 * 判断回文
	 * @param chs
	 * @param low
	 * @param high
	 * @return
	 */
	public  static boolean isPlindrome(char[] chs,int low, int high) {
        while (low <= high) {
            if (chs[low] != chs[high]) {
                return false;
            }
            ++low;
            --high;
        }
        return true;
    }
	/**
	 * 判断是否为素数
	 * @param n
	 * @return
	 */
	public boolean isPrime(int n){
		boolean flag = true;
		if(n<2){
			flag = false;
		}
		else{
			for(int i=2;i<=Math.sqrt(n);i++){
				if(n%i==0){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
}
