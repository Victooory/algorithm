package algorithm.sort;
/**
 * 快速排序	O(N*logN) 不稳定
 * 第一个为基准数 ，通过交换，使得基准数左侧全小于它 右侧全大于它（切分）
 * 再递归分别算两个子序列  ，直到都有序
 * @author dong
 * 改进：在数组小时切换到插入排序
 */
public class Quick {
	public void sort(Comparable[] a){
		sort(a,0,a.length-1);
	}
	public void sort(Comparable[] a,int lo,int hi){
		if(hi <= lo) return;
		int mid = partition(a, lo, hi);
		sort(a,lo,mid-1);
		sort(a, mid+1, hi);
	}
	public int partition(Comparable[] a,int low,int high){			//固定切分
		Comparable key = a[low];
		while(low<high){
			while(key.compareTo(a[high])<0 && high > low){
				high--;
			}
			a[low] = a[high];
			while(key.compareTo(a[low])>0 && high > low){
				low++;
			}
			a[high] = a[low];
		}
		a[high] = key;
		return high;
	}
 
	public static void main(String[] args) {
		Integer[] a = {1,3,6,9,7,4,5,2,8,0}; //第一次固定切分后	0 1 6 9 7 4 5 2 8 3 
		new Quick().sort(a);
		for(Comparable i : a){
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
