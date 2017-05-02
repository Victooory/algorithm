package algorithm.problems;

/**
 * 在一个上至下、左至右递增的二维数组中查找是否有指定数字
 * @author dong
 *
 */
public class NumIn2Array {
	public NumIn2Array(){}
	public boolean Find(int target,int[][] array){
		boolean flag = false;
		if(array.length==0 ||(array.length==1&&array[0].length==0)){			//判断数组是否是{}或{{}}
			return flag;
		} 
		else{
			int n = array[0].length;;		//n限定二维数组的列数，可变
			for(int i=0;i<array.length;i++){
				if(target<array[i][0]){
					break;
				}
				for(int j=0;j<n;j++){
					if(target>array[i][j]){
						continue;
					}else if(target==array[i][j]){
						flag = true;
					}else{
						n = j;
					}
				}
			}
		}
		return flag;
	}
	public static void main(String args[]){
		int array[][] = {{ 1, 2, 8, 9 },
			      { 2, 4, 8, 12 },
			      { 4, 7, 10, 13 },
			      { 6, 8, 11, 15 }};
		int i = 7;
		NumIn2Array test = new NumIn2Array();
		if(test.Find(i, array)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}
}
