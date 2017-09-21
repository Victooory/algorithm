package algorithm.problems;

/**
 * 连续子数组最大和
 * @author 10142
 *
 */
public class MaxSubSum {

	public static void main(String[] args) {
		int maxSum = 0;
		int thisSum = 0;
		int a[] = new int[10];
		for(int i=0;i<a.length;i++){
			thisSum +=a[i];
			if(thisSum>maxSum){
				maxSum = thisSum;
			}else if(thisSum < 0){
				thisSum = 0;
			}
		}
		System.out.println(thisSum);
	}
}
