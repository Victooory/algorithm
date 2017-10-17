package algorithm.problems;

public class LongestCommonSubSequence {
	public static void main(String[] args) {
		String s1 = "abcdef";
		String s2 = "acdsssbce";
		int a[][] = new int[s1.length()][s2.length()];
		System.out.println(new LongestCommonSubSequence().LCS(s1, s2,a));
	}
	/**
	 * LCS最长公共子序列
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int LCS(String s1,String s2,int a[][]){
		int max=0;
		int dp[][]= new int[s1.length()+1][s2.length()+1];
		for(int i=0;i<s1.length()+1;i++){			//初始化DP数组
			dp[i][0] = 0;
		}
		for(int j=0;j<s2.length()+1;j++){
			dp[0][j] = 0;
		}
		for(int i=1;i<s1.length()+1;i++){
			for(int j=1;j<s2.length()+1;j++){
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					a[i-1][j-1] = 0;
				}
				else{
					if(dp[i][j-1] > dp[i-1][j]){
						dp[i][j] = dp[i][j-1];
						a[i-1][j-1] = 1;
					}
					else{
						dp[i][j] = dp[i-1][j];
						a[i-1][j-1] = -1;
					}
				}
			}
		}
		max = dp[s1.length()][s2.length()];
		return max;
	}
	public void Print_LCS(int b[][],String s,int i,int j){
		if(i==0||j==0)  
	        return ;  
	    if(b[i-1][j-1]==0)  
	    {  
	        Print_LCS(b,s,i-1,j-1);  
	        System.out.println(s.charAt(i-1));  
	    }  
	    else if(b[i-1][j-1]==1)  
	    {  
	        Print_LCS(b,s,i-1,j);  
	    }  
	    else  
	        Print_LCS(b,s,i,j-1);
	}
}
