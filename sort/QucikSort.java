package sort;

import java.util.Scanner;

public class QucikSort {
	
	public static void qucikSort(int [] A,int s,int e){
		if(s<e){
			int p=partition(A,s,e);
			qucikSort(A,s,p-1);
			qucikSort(A,p+1,e);
		}
	}
	public static int partition(int[] A,int s,int e){
		int i=s-1;
		//随机化处理
		int random=(int)(Math.random()*(e-s))+s;
		int temp=A[random];
		A[random]=A[e];
		A[e]=temp;
		int x=A[e];
		for(int j=s;j<e;j++){
			if(A[j]<=x){
				i++;
				temp=A[i];
				A[i]=A[j];
				A[j]=temp;
			}
		}
		temp=A[e];
		A[e]=A[i+1];
		A[i+1]=temp;
		return i+1;
	}
	public static void main(String[] args){
		int a[]=new int[10];
		 
//		Scanner input=new Scanner(System.in);
//		while(input.hasNextInt()){
//			a[i++]=input.nextInt();
//		}
		for(int k=0;k<100;k++){
			for(int i=0;i<10;i++){
				a[i]=(int)(Math.random()*10);
			}
			qucikSort(a,0,9);
			int j;
			for( j=0;j<9;j++){
				 if(a[j+1]>a[j]){
					 System.out.println("no");
					 break;
				 }
			}
			if(j>=9){
				System.out.println("yes");
			}
		}
		 
		
	}
     

}
