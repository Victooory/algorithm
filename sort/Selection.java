package algorithm.sort;

/**
 * 选择排序	O(n^2)	不稳定
 * 从未排序的数列中选出最小(大)的放到排序好的最后面，直到所有都有序
 * @author dong
 *
 */
public class Selection {

	void selection_sort(int a[], int n)  
	{  
	    int i, j, k;  
	    for (i = 0; i< n-1; i++) {  
	        k = i;  
	        for (j = i+1; j < n; j++) {  
	            if (a[k] > a[j])  
	                k = j;  
	        }  
	        if (k != i) {  
	            int tmp = a[i];  
	            a[i] = a[k];  
	            a[k] = tmp;  
	        }  
	    }  
	}  
	public void sort(Comparable[] a){
		int N = a.length;
		for(int i=0;i<N;i++){
			int min = i;
			for(int j=i+1;j<N;j++){
				if(a[j].compareTo(a[min])<0) min = j;		//j<min
			}
			exch(a, i, min);
		}
	}
	public void exch(Comparable[] n,int a,int b){		//交换数组中的元素
		Comparable temp = 0;
		temp = n[a];
		n[a] = n[b];
		n[b] = temp;
	}
	public static void main(String[] args) {
		Integer[] a = {1,3,6,9,7,4,5,2,8,0};
		new Selection().sort(a);
		for(Comparable<?> i : a){
			System.out.print(i+" ");
		}
	}

}
