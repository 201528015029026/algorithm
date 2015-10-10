package hash;

public class OpenAddressing {
	public  int[] A;
	private int len;
	public OpenAddressing(int len){
		this.len=len;
		A=new int[len];
	}
	// 采用二次探测法 h1=k%m ,h2=1+k%m1 ,这里 
	// m 和m1的选择策略，选择m 为一个素数，并设计一个从事返回 比 m 小的正整数的h2
	public boolean insert(int k){
		int h1=k%13;
		int h2=1+k%11;
		int h;
		for(int i=0;i<len;i++){
			h=(h1+i*h2)%13;
			if(A[h]==0){
				A[h]=k;
				 
				return true;
			}
		}
		return false;
	}
	public boolean search(int k){
		int h1=k%13;
		int h2=1+k%11;
		int h;
		for(int i=0;i<len;i++){
			h=(h1+i*h2)%13;
			if(A[h]==k){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args){
		OpenAddressing oa=new OpenAddressing(13);
		oa.insert(79);
		oa.insert(69);
		oa.insert(5);
		oa.insert(72);
		oa.insert(14);
		oa.insert(50);
		for(int i=0;i<13;i++){
			System.out.println(i+"::"+oa.A[i]);
		}
		 
	}
}
