package algorithm.problems;

import java.util.Arrays;

public class AboutString {
	
	/**
	 * 字符串移位
	 * @param str
	 * @param n
	 * @return
	 */
    public String LeftRotateString(String str,int n) {
    	if(str.length() == 0) return "";
    	if(n>= str.length()){
    		n = n%str.length();
    	}
        String newstr = str + str;
        return newstr.substring(n, str.length() + n);
    }
    public static void main(String[] args) {
		System.out.println(new AboutString().LeftRotateString("",6));
		
	}
}
