package algorithm;

import java.util.Scanner;

/**
 * 超级素数幂的判断
 * 输入一个数n  p为素数 q大于1 n=p^q 输出p，q
 * @author dong
 *
 */

public class Prime_Pow {
	private double n;
	public Prime_Pow(){} 
	public Prime_Pow(double n) {
		this.n = n;
	}
	public boolean solve(){
		boolean flag = true;
		int i;			//底数
		double j;		//指数
		for(i=2;i<=Math.pow(n,1.0/2.0);i++){
			if(isPrime(i)){
				for(j=2;Math.pow((double)i, j)<=n;j++){
					if(Math.abs((double)i-Math.pow(n,1.0/j))<0.00001){	//pow的精度问题
						System.out.println(i+" "+(int)j);
						flag = false;
						break;
					}
				}
			}
			if(!flag){
				break;
			}
		}
		return flag;
	}
	/**
	 * 判断是否为素数
	 * @param n
	 * @return
	 */
	public boolean isPrime(int n){
		boolean flag = true;
		if(n<2){
			flag = false;
		}
		else{
			for(int i=2;i<=Math.sqrt(n);i++){
				if(n%i==0){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			double n= (double)sc.nextInt();
			Prime_Pow prime_pow =new Prime_Pow(n);
			if(prime_pow.solve()){
				System.out.println("NO");		//如果不是输出NO
			}
		}
	}

}

