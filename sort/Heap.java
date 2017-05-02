package algorithm.sort;

import java.lang.reflect.Array;
/**
 * 堆排序
 * @author dong
 *
 */
public class Heap {
	private int[] array;
	public Heap(int[] array){
		this.array = array;
	}
	public void heapSort(){
		buildHeap();
		System.out.println("建堆...");
		display();
		System.out.println();
		
		int lastIndex = array.length -1;
		while(lastIndex>0){
			swap(0,lastIndex);    //堆顶元素和堆底交换
			if(--lastIndex == 0) break;   //只有一个元素就不用排序
			adjustHeap(0, lastIndex);
			System.out.println("调整堆...");
			display();
			System.out.println();
		}
	}
	public void swap(int firstIndex,int lastIndex){
		int temp = 0;
		temp = array[firstIndex];
		array[firstIndex] = array[lastIndex];
		array[lastIndex] = temp;
	}
	//用数组元素建堆
	public void buildHeap(){
		int lastIndex = array.length -1;
		for(int i=(lastIndex-1)/2;i>=0;i--){			//最后一个元素的根节点下标
			adjustHeap(i, lastIndex);
		}
	}
	//调整rootIndex下标的根节点的子树
	public void adjustHeap(int rootIndex,int lastIndex){
		//左子结点下标 [2n] 右[2n+1]
		int biggerIndex = rootIndex;
		int leftChildIndex = rootIndex*2 + 1;			//？+1
		int rightChildIndex = rootIndex*2 + 2;
		if (rightChildIndex<=lastIndex) {
			if (array[rootIndex]<array[leftChildIndex]||array[rootIndex]<array[rightChildIndex]) {//子节点有比根节点大的
				biggerIndex = array[rightChildIndex]>array[leftChildIndex]?rightChildIndex:leftChildIndex;
			}
		}else if(leftChildIndex<=lastIndex){   	//只存在左子结点
			if(array[rootIndex]<array[leftChildIndex])
				biggerIndex = leftChildIndex;
		}
		if(biggerIndex!=rootIndex){
			swap(rootIndex, biggerIndex);
			adjustHeap(biggerIndex, lastIndex);
		}
		
	}
	public void printTree(int len){
		
	}
	
	public void display(){
		for(int i:array){
			System.out.print(i + " ");
		}
	}
	public static void main(String[] args) {
		int[] i ={7,5,1,3,4,9,2,6,8};
		Heap heap = new Heap(i);
		heap.heapSort();
		heap.display();
	}
}
