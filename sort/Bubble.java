package algorithm.sort;

/**
 * 冒泡排序
 * @author dong
 *
 */
public class Bubble {
	public static int count=0;
	public void sort(Comparable[] a){			
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				
				if(a[i].compareTo(a[j])>0){
					exch(a, i, j);
				}
				count++;
			}
		}
	}
	public void sort2(Comparable[] a)		//正规冒泡
	{
		int N=a.length;
		for(int i=0;i<N;i++)
		{
			for(int j=N-1;j>i;j--)
			{
				if(a[j].compareTo(a[j-1])<0)
				{
					exch(a, j, j-1);
				}
				count++;
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
		Integer[] a = {5,6,9,8,7,4,3,2,1,0};
		new Bubble().sort2(a);
		for(Comparable i : a){
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println(count);
	}
}
