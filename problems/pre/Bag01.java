package algorithm.problems.pre;


/**
 * 01背包问题
 * @author dong
 *
 */
public class Bag01 {  
    private int maxWeight;  
    private int[][] record;  
    private Stuff[] stuffs;  
  
    public Bag01(Stuff[] stuffs, int maxWeight) {  
        this.stuffs = stuffs;  
        this.maxWeight = maxWeight;  
        int n = stuffs.length + 1;  
        int m = maxWeight+1;  
        record = new int[n][m];  
        for(int i = 0; i < n; i ++) {  						//初始化二维表
            for(int j = 0; j < m; j ++) {  
                record[i][j] = -1;  
            }  
        }  
    }  
    public int solve(int i, int residue) {  
        if(record[i][residue] > 0) {  
            return record[i][residue];  
        }  
        int result;  
        if(i >= stuffs.length) {  
            return 0;  
        }  
        if(stuffs[i].getWeight() > residue) {  
            result = solve(i + 1, residue);  
        } else {  
            result = Math.max(solve(i + 1, residue),  
                 solve(i + 1, residue - stuffs[i].getWeight()) + stuffs[i].getValue());  
        }  
        record[i][residue] = result;  
        return result;  
    }  
  
    public static void main(String args[]) {  
        Stuff stuffs[] = {  
        		
            new Stuff(2, 3),  
            new Stuff(1, 2),  
            new Stuff(3, 4),  
            new Stuff(2, 2)  
        };  
        Bag01 bag = new Bag01(stuffs, 5);
        int result = bag.solve(0, 5);  
        System.out.println(result);  
    }  
}   
  
class Stuff{  					//每件物品的定义
    private int weight;  
    private int value;  
  
    public Stuff(int weight, int value) {  
        this.weight = weight;  
        this.value = value;  
    }  
  
    int getWeight() {  
        return weight;  
    }  
  
    void setWeight(int weight) {  
        this.weight = weight;  
    }  
  
    int getValue() {  
        return value;  
    }  
  
    void setValue(int value) {  
        this.value = value;  
    }  
}  
