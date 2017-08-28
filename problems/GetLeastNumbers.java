package algorithm.problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import algorithm.sort.Quick;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,
 * 
 * @author 10142
 *
 */
public class GetLeastNumbers {
	//可以运用快排思想***
	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		if (k > input.length) {
            return array;
        }
		qsort(input,0,input.length-1,k);
		for(int i=0;i<k;i++){
			array.add(input[i]);
		}
		return array;
	}
	
	
	public void qsort(int[] a,int low,int high,int k){
		if(low>=high) return ;
		int mid = partition(a, low, high);
		if(mid>k){
			qsort(a, low,mid-1,k);
		}else if(mid<k){
			qsort(a, mid+1, high,k);
		}else{
			return;
		}
	}

	
	//快排代码
	public void sort(int[] a){
		sort(a,0,a.length-1);
	}
	public void sort(int[] a,int low,int high){
		if(low>=high) return ;
		int mid = partition(a,low,high);
		sort(a,low,mid-1);
		sort(a,mid+1,high);
	}
	public int partition(int[] a,int low,int high){
		int key = a[low];
		while(low<high){
			while(low<high && key<a[high]){
				high--;
			}
			if(low<high){
				a[low++] = a[high];
			}
			while(low<high && key>a[low]){
				low++;
			}
			if(low<high){
				a[high--] = a[low];
			}
		}
		a[high] = key;
		return high;
	}
	public static void main(String[] args) {
		//生成一个长度20的随机数组
		int[] rand = new int[20];
		Random random = new Random();
		for(int i=0;i<20;i++){
			rand[i] = random.nextInt(20);
		}
		for(int i:rand){
			System.out.print(i + " ");
			
		}
		System.out.println();
		ArrayList<Integer> array = new GetLeastNumbers().GetLeastNumbers_Solution(rand,9);
		Iterator<Integer> it = array.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
//		int[] a = {2,11,1,3,15,6,12,9,7,17,4,5,10,8,0};
//		new GetLeastNumbers().GetLeastNumbers_Solution(a,1);
//		//new GetLeastNumbers().sort(a);
//		for(int i:a){
//			System.out.print(i + " ");
//		}
	
		
		//测试快排
//		int[] a = {1,3,6,9,7,4,5,2,8,0}; //第一次固定切分后	0 1 6 9 7 4 5 2 8 3 
//		new GetLeastNumbers().sort(a);
//		for(int i:a){
//			System.out.print(a[i]+" ");
//		}
	}
}
