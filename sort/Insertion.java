package algorithm.sort;
/**
 * 插入排序 	O(n^2) 稳定
 * 将每一张牌插入到已经有序的牌中的适当位置
 * @author dong
 *
 */
public class Insertion {
	public void sort(Comparable[] a){
		int N = a.length;
		for(int i=1;i<N;i++){
			for(int j=i;j>0 && a[j].compareTo(a[j-1])<0;j--){		// j<j-1 交换
				exch(a, j, j-1);   		//交换
			}
		}
	}
	public void exch(Comparable[] n,int a,int b){		//交换数组中的元素
		Comparable temp = 0;
		temp = n[a];
		n[a] = n[b];
		n[b] = temp;
	}
	public static void main(String args[]){
		Integer[] a = {1,3,6,9,7,4,5,2,8,0};
		new Insertion().sort(a);
		for(Comparable i : a){
			System.out.print(i+" ");
		}
	}
}
