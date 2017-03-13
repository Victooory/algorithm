package algorithm;

import java.util.Scanner;

/**
 * 浮点底数的整数次幂
 * @author dong
 *
 */
public class FloatPower {
	public double Power(double base, int exponent){
		double result = 0.0;
		int n=0;
		//Math.floor(base);
		if(base==(int)base){
			result = Math.pow(base,exponent);
		}else{
			while(base!=(int)base){
				base = base*10;
				n--;
			}
			result = Math.pow(base,exponent)*Math.pow(10,n*exponent);
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double base = sc.nextDouble();
		int exponent = sc.nextInt();
		FloatPower floatPower = new FloatPower();
		System.out.println(floatPower.Power(base, exponent));
	}
}
