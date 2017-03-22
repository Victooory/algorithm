package algorithm.sort;
/**
 * 希尔排序 	O(n^1.3)	不稳定
 * 缩小增量排序   先分成若干子序列(相隔h个元素)，分别直接插入排序
 * 一次缩减增量h，再排序，基本有序时，对所有元素进行直接插入排序O(n)
 * @author dong
 *
 */
public class Shell {
	public void sort(Comparable[] a){
		int N = a.length;
		int h = 1;
		while(h < N/3)	h= 3*h + 1; //1,4,13,40..
		
		while(h>=1){
			for(int i=h;i<N;i++){
				for(int j=i; j>=h && a[j].compareTo(a[j-h])<0; j = j-h){	//j<j-h
					exch(a, j, j-h);
				}
			}
			h = h/3;
		}
/*		void shellsort1(int a[], int n)  
		{  
		    int i, j, gap;  
		  
		    for (gap = n / 2; gap > 0; gap /= 2) //步长  
		        for (i = 0; i < gap; i++)        //直接插入排序  
		        {  
		            for (j = i + gap; j < n; j += gap)   
		                if (a[j] < a[j - gap])  
		                {  
		                    int temp = a[j];  
		                    int k = j - gap;  
		                    while (k >= 0 && a[k] > temp)  
		                    {  
		                        a[k + gap] = a[k];  
		                        k -= gap;  
		                    }  
		                    a[k + gap] = temp;  
		                }  
		        }  
		}  */
			
	}
	public void exch(Comparable[] n,int a,int b){		//交换数组中的元素
		Comparable temp = 0;
		temp = n[a];
		n[a] = n[b];
		n[b] = temp;
	}
	public static void main(String[] args) {
		String[] a = {"s","a","e","q","u"};
		new Shell().sort(a);
		for(Comparable i : a){
			System.out.print(i+" ");
		}
	}
}
