package DP;

import java.util.Scanner;

import Tree.Node;

public class LCS {
	public int[][] m=new int[100][100];
	public int[][] b=new int[100][100];
	public int[][] root=new int[100][100];
	public int count=0;
	public LCS(int lena,int lenb){
		for(int i=0;i<lena;i++){
			for(int j=0;j<lenb;j++){
				m[i][j]=0;
				b[i][j]=0;
				root[i][j]=0;
			}
		}
	}
	//自顶向下的动态规划
	public  int lcs_length(String A,String B,int x,int y){
		if(x==-1||y==-1){
			return 0;
		}
		if(m[x][y]>0){
			return m[x][y];
			
		}
		if(A.charAt(x)==B.charAt(y)){
			m[x][y]=lcs_length(A,B,x-1,y-1)+1;
			return m[x][y];
		}
		else{
			int tempa=lcs_length(A,B,x-1,y);
			int tempb=lcs_length(A,B,x,y-1);
			if(tempa>tempb){
				m[x][y]=tempa;
				return m[x][y];
			}
			else{
				m[x][y]=tempb;
				return m[x][y];
			}
		}
	}
	public int lcs2(String A,String B){
		int m=A.length();
		int n=B.length();
	    int [][] len=new int[m][n];
	    //只有这种自底向上的方法 才可以 按照下面的方式赋值，自定向下的方式必须把所有的元素赋值
	    for(int i=0;i<m;i++){
	    	len[i][0]=0;
	    }
	    for(int j=0;j<n;j++){
	    	len[0][j]=0;
	    }
	    for(int i=0;i<m;i++){
	    	for(int j=0;j<n;j++){
	    		if(A.charAt(i)==B.charAt(j)){
	    			if(i==0||j==0){
	    				len[i][j]=1;
	    			}
	    			else{
	    				len[i][j]=len[i-1][j-1]+1;
	    				
	    			}
	    			b[i][j]=3;
	    			//System.out.println(A.charAt(i));
	    		}
	    		else{
	    			if(i==0||j==0){
	    				len[i][j]=0;
	    			}
	    			else{
	    				 
	    				if(len[i-1][j]>len[i][j-1]){
	    					len[i][j]=len[i-1][j];
	    					b[i][j]=1;
	    				}
	    				else{
	    					len[i][j]=len[i][j-1];
	    					b[i][j]=2;
	    				}
	    			}
	    		}
	    	}
	    }
	    return len[m-1][n-1];
	}
	public void print_path(String A,int x,int y){
		if(x==-1||y==-1){
			return ;
		}
		if(b[x][y]==3){
			print_path(A,x-1,y-1);
			System.out.println(A.charAt(x));
		}
		else if(b[x][y]==2){
			print_path(A,x,y-1);
		}
		else{
			print_path(A,x-1,y);
		}
	}
	public void most_increase_series(int [] a,int x){
		for(int i=0;i<x;i++){
			for(int j=0;j<=i;j++){
				b[i][j]=a[j];
			}
		}
		int maxm=1;
		int s=0;
		int e=0;
		for(int i=0;i<x;i++){
			for(int j=0;j<i;j++){
				if(a[i]>b[i-1][j]){
					b[i][j]=a[i];
					m[i][j]=m[i-1][j]+1;
					if(m[i][j]>maxm){
						maxm=m[i][j];
						s=j;
						e=i;
					}
				}
			}
		}
		//输出最长递增子序列
		System.out.println(a[s]);
		for(int j=s+1;j<=e;j++){
			if(a[j]>b[j-1][s]){
				System.out.println(a[j]);
			}
		}
		System.out.println(maxm);
	}
	//最优二叉搜索树 ,数组参数应该是 double,p伪节点被搜索的概率，q 实际节点被搜索的概率
	public void optimal_binary_tree(int [] p,int [] q,int n){
		for(int i=1;i<=n+1;i++){
			b[i][i-1]=p[i-1];
			m[i][i-1]=p[i-1];
		} 
		for(int k=1;k<=n;k++){
			for(int i=1;i<=n-k+1;i++){
				int j=i+k-1;
				m[i][j]=m[i][j-1]+p[j]+q[j];
				b[i][j]=Integer.MAX_VALUE;
				for(int r=i;r<=j;r++){
					int temp=b[i][r-1]+b[r+1][j]+m[i][j];
					if(temp<b[i][j]){
						b[i][j]=temp;
						root[i][j]=r;
					}
				}
			}
		}
//		for(int j=1;j<=n;j++){
//			for(int i=1;i<=j;i++){
//				int minm=Integer.MAX_VALUE;
//				m[i][j]=m[i][j-1]+p[j]+q[j];
//				for(int k=i;k<=j;k++){
//					int temp=b[i][k-1]+b[k+1][j]+m[i][j];
//					if(temp<minm){
//						minm=temp;
//					}
//				}
//				b[i][j]=minm;
//			}
//		}
		System.out.println(b[1][n]);
		
		
	}
	public void  print_optiomal_binary_tree(int s,int e){
		if(s>e){
			return;
		}
		int k=root[s][e];
		
		int left=root[s][k-1]; 
		if(s==k){
			System.out.println("k"+k+"的左儿子是d"+count);
			count++;
		}
		else{
			System.out.println("k"+k+"的左儿子是k"+left);
		}
		int right=root[k+1][e];
		if(k==e){
			System.out.println("k"+k+"的右儿子是d"+count);
			count++;
		}
		else{
			System.out.println("k"+k+"的右儿子是k"+right);
		}
		print_optiomal_binary_tree(s, k-1);
		print_optiomal_binary_tree(k+1, e);
	}
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int [] a=new int[100];
//		while(in.hasNext()){
////			String a=in.nextLine();
////			String b=in.nextLine();
//			LCS lcs=new LCS(100,100);
////			System.out.println(lcs.lcs_length(a,b,a.length()-1,b.length()-1));
////			System.out.println(lcs.lcs2(a, b));
////			lcs.print_path(a, a.length()-1, b.length()-1);
//			int n;
//			n=in.nextInt();
//			for(int i=0;i<n;i++){
//				a[i]=in.nextInt();
//			}
//			lcs.most_increase_series(a, n);
//		
	   int[] q={0,15,10,5,10,20};
	   int[] p={5,10,5,5,5,10};
	   LCS lcs=new LCS(100,100);
	   lcs.optimal_binary_tree(p, q, 5);
	   lcs.print_optiomal_binary_tree(1, 5);
		
	}

}
