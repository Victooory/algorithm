package algorithm.problems;

import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

public class Random20LettersSort {
	public static char getRandomLetter(){
		Random random = new Random();
		return (char)(random.nextInt(25)+97);
	}
	public static void main(String[] args) {
		
		TreeSet<Character> TS = new TreeSet<>(new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				if(o1>o2){
					return -1;
				}else if(o1<o2){
					return 1;
				}
				return 0;
			}
		});
		for(int i=0;i<20;i++){
			TS.add(Character.valueOf(getRandomLetter()));
		}
		for(Object c:TS.toArray()){
			System.out.println(c);
		}
	}
}
