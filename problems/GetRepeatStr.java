package algorithm.problems;

import java.awt.image.RescaleOp;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取一个字符串中重复的字符串
 * 使用map去重并记录出现次数
 * @author 10142
 *
 */
public class GetRepeatStr {
	public static void main(String[] args) {
		Map<String, Integer> resMap = new HashMap<String, Integer>();
		String str = "abcdabcdab";
		String substr;
		Integer count;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				substr = str.substring(i, j+1);
				count = resMap.get(substr);
				if (count == null) {
					resMap.put(substr, 1);
				} else {
					count++;
					resMap.put(substr, count);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : resMap.entrySet()) {
			if (entry.getValue() > 1)
				System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}
	

}
