package algorithm.problems;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * 包含字符集的最短字符串的长度
 * @author 10142
 *
 */
public class ContainSomeCharLeastString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			Map<Character, Integer> map = new HashMap<>();			//key:字符，value位置从1开始
			int minlen=Integer.MAX_VALUE;
			char[] a = sc.next().toCharArray();
			String str = sc.next();
			for(int i=0;i<a.length;i++){
				map.put(a[i], new Integer(-1));
			}
			for(int i=0;i<str.length();i++){
				boolean flag = false;
				for(int j=0;j<map.size();j++){				//这个位置有没有字符集中的字符
					if(map.containsKey(str.charAt(i))){
						map.put(str.charAt(i), i);
						flag = true;
						break;
					}
				}
				if((!map.containsValue(-1)) && flag){				//获取最小长度
					int smin=Integer.MAX_VALUE,smax=0;
					Collection<Integer> list = map.values();
					for(Integer s : list){
						if(smin>s)
							smin = s;
						if(smax<s)
							smax = s;
					}
					if(minlen>(smax-smin))
						minlen = smax - smin + 1 ;
				}
			}
			System.out.println(minlen);
		}
	}
	
}
