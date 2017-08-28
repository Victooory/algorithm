package algorithm.sort;

import java.awt.event.KeyAdapter;

/**
 * 快速排序	O(N*logN) 不稳定
 * 第一个为基准数 ，通过交换，使得基准数左侧全小于它 右侧全大于它（切分）
 * 再递归分别算两个子序列  ，直到都有序
 * @author dong
 * 改进：在数组小时切换到插入排序
 */
public class Quick {
	public void sort(int[] a){
		sort(a,0,a.length-1);
	}
	public void sort(int[] a,int left,int right){
		if(right > left){
			int key = a[left];
			int low = left;
			int high = right;
			while(low<high){
				while(low<high && key < a[high])
					high--;
				if(low<high)
					a[low++] = a[high];
				while(low<high && key > a[low])
					low++;
				if(low<high)
					a[high--] = a[low];
			}
			a[low] = key;
			sort(a,left,low-1);
			sort(a, low+1, right);
		}
	}

 
	public static void main(String[] args) {
		int[] a = {1,3,6,9,7,4,5,2,8,3,1}; //第一次固定切分后	0 1 6 9 7 4 5 2 8 3 
		new Quick().sort(a);
		for(int i : a){
			System.out.print(i+" ");
		}
	}
/*	public static int partition(int []array,int lo,int hi){
        //三数取中切分
        int mid=lo+(hi-lo)/2;
        if(array[mid]>array[hi]){
            swap(array[mid],array[hi]);
        }
        if(array[lo]>array[hi]){
            swap(array[lo],array[hi]);
        }
        if(array[mid]>array[lo]){
            swap(array[mid],array[lo]);
        }
        int key=array[lo];
        
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }*/
}
