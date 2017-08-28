package algorithm.problems.pre;

/**
 * 购入和卖出股票得到最佳收益
 * @param prices Prices[i]即第i天的股价	3,8,5,1,7,8
 * @author dong
 * 网上时间最优的答案 和 一般理解的方法
 * output 12
 */
public class BestTime2Stock {
	public int sovle(int[] price){
		int firstBuy = Integer.MIN_VALUE, firstSell = 0;		//买入负值
		int secondBuy = Integer.MIN_VALUE, secondSell = 0;
		for(int curPirce:price){
			firstBuy = Math.max(firstBuy,-curPirce);
			firstSell = Math.max(firstSell, firstBuy + curPirce);
			secondBuy = Math.max(secondBuy, firstSell-curPirce);
			secondSell = Math.max(secondSell, secondBuy + curPirce);
		}
		return secondSell;
	}
	
	public static void main(String[] args) {
		int[] n= {3,8,5,1,7,8};
		BestTime2Stock bestTime2Stock = new BestTime2Stock();
		bestTime2Stock.sovle(n);
	}
}
