package algorithm.problems.pre;

import java.util.Scanner;

/**
 * 青蛙跳台阶种数
 * 动态规划DP
 * @author dong
 *
 */
public class JumpFloor {
	public int sovle(int target) {
		if(target==0){
			return 0;
		}else if(target==1){
			return 1;
		}else if(target==2){
			return 2;
		}
		return sovle(target-1)+sovle(target-2);
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		JumpFloor jumpFloor = new JumpFloor();
		System.out.println(jumpFloor.sovle(n));
	}
}
