package algorithm.problems.pre;

import java.util.Scanner;

/**
 * 青蛙跳台阶种数 动态规划DP
 * 
 * @author dong
 *
 */
public class JumpFloor {

	// 可以调N级台阶的青蛙
	public int JumpFloorII(int target) {
		int count = 0;
		if (target <= 0)
			return count;
		else if (target == 1)
			return 1;
		else if (target == 2)
			return 2;
		else {
			for (int k = 1; k <= target - 1; k++)
				count += JumpFloorII(target - k);
			count++;
			return count;
		}
	}

	public int JumpFloorII2(int target) {
		if (target <= 0) {
			return -1;
		} else if (target == 1) {
			return 1;
		} else {
			return 2 * JumpFloorII(target - 1);
		}
	}

	public int sovle(int target) {
		if (target == 0) {
			return 0;
		} else if (target == 1) {
			return 1;
		} else if (target == 2) {
			return 2;
		}
		return sovle(target - 1) + sovle(target - 2);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		JumpFloor jumpFloor = new JumpFloor();
		System.out.println(jumpFloor.sovle(n));
	}
}
