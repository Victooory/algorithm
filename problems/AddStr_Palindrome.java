package algorithm.problems;

import java.util.Scanner;

/**
 * 给定一个字符串，问是否能通过添加一个字母将其变为回文串。 输出YES/NO
 * 
 * @author dong
 *
 */
public class AddStr_Palindrome {
	private String str;

	public AddStr_Palindrome() {
	}

	public AddStr_Palindrome(String str) {
		this.str = str;
	}

	public boolean solve() {
		boolean flag = false;
		int i;
		for (i = 0; i < str.length(); i++) {
			StringBuilder sb = new StringBuilder(str);
			if (ispalindrome(sb.deleteCharAt(i).toString())) {
				flag = true;
				break; 
			}
		}
		return flag;
	}

	public boolean ispalindrome(String str) { // 判断是否是回文
		boolean flag = false;
		StringBuilder sb = new StringBuilder(str);
		if (str.equals(sb.reverse().toString())) {
			flag = true;
		}
		return flag;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.nextLine(); // 对于判断回文，增加一个和删掉一个字符的结果屎一样的
			AddStr_Palindrome add_str_palindrome = new AddStr_Palindrome(str);
			if (add_str_palindrome.solve()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
