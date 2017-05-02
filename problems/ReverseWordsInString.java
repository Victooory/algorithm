package algorithm.problems;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * 字符串翻转，单词不变
 * @author dong
 * 强大的java库
 */
public class ReverseWordsInString {
	public String sovle(String s){
		String[] words = s.trim().split(" +");
		Collections.reverse(Arrays.asList(words));
		return String.join(" ", words);
	}
	public static void main(String[] args) {
		ReverseWordsInString reverseWordsInString = new ReverseWordsInString();
		String getIn = new String("the sky is blue");
		System.out.println(reverseWordsInString.sovle(getIn));
	}
}
