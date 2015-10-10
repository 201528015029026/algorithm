package DP;
public class Coin {
	public int[] v=new int[100];
	public int[] coin={0,1,2,9,10};
	public int[] info=new int[100];//记录使用了那些coin ，输出 所用的coin
	//问题描述：给定 一个 金额 money，和 一些一定面值的coin ，希望用最少的coin 兑换 money
	//默认输入的coin 按照从小到大排序
	//算法思想：a[m]=min(a[m-c1]+1,a[m-c2]+1....);
	public void moneytocoins(int money,int numcoins){
		for(int i=1;i<=money;i++){
			v[i]=Integer.MAX_VALUE;
			for(int j=1;j<=numcoins;j++){
				if(coin[j]>i){
					break;
				}
				else{
					if(v[i-coin[j]]+1<v[i]){
						v[i]=v[i-coin[j]]+1;
						info[i]=coin[j];
					}
				}
			}
		}
		System.out.println(v[money]);
		for(int i=money;i>0;){
			System.out.println(info[i]);
			i-=info[i];
		} 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coin coin=new Coin();
		coin.moneytocoins(19,4);
	}

}
