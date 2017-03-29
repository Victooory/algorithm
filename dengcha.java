package algorithm;
import java.util.Scanner;

/**
 * 输入N（等差的和）和L（等差元素的个数）
 * 求等差数列
 * @author dong
 *
 */
public class dengcha {
	private double sum = 0;
	private double L = 0;
	private double x = 0;
	public dengcha(double sum,double num){
		this.sum = sum;
		this.L = num;
	}
	public String solve(){
		StringBuilder result = new StringBuilder();
		for(;;L++){						
        	x = (2*sum/L+1-L)/2;
        	if(x==(int)x){
        		for(int j=0;j<L;j++){
        			result.append(((int)x+" "));
        			x++;
        		}
        		break;
        	}
        }
		return result.toString();
	}
	public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        double n= (double)sc.nextInt();
        double l= (double)sc.nextInt();
        dengcha about_dengcha = new dengcha(n, l);
        System.out.println(about_dengcha.solve());
	}
}
