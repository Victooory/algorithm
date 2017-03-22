package algorithm.sort;
/**
 * 归并排序	O(N*logN) 稳定
 * 自顶向下 归并 将小的有序数组合并成一个有序数组 效率最高的排序算法
 * @author dong
 *
 */
public class Merge {
	public static Comparable[] aux;		//中间数组
	public void sort(Comparable[] a){
		aux = new Comparable[a.length];
		sort(a,0,a.length-1);
	}
	public void sort(Comparable[] a,int lo,int hi){			//将数组a[lo..hi]排序
		if(hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a,lo,mid);
		sort(a, mid+1,hi);
		merge(a, lo, mid, hi);
	}
	public void merge(Comparable[] a,int lo,int mid,int hi){			//两个有序数组归并
		int i = lo,j = mid + 1;
		for(int k=lo;k<=hi;k++){
			aux[k] = a[k];
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid) a[k] = aux[j++];  //左半边用尽 取右边元素
			else if(j>hi) a[k] = aux[i++];  //右半边用尽 取左边元素
			else if(aux[j].compareTo(aux[i])<0) a[k] = aux[j++];	//右小于左
			else 	a[k] = aux[i++];
		}
	}

	public static void main(String[] args) {
		Integer[] a = {1,3,6,9,7,4,5,2,8,0};
		new Merge().sort(a);
		for(Comparable<?> i : a){
			System.out.print(i+" ");
		}
	}
}
