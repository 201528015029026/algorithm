package hash;

import java.util.ArrayList;

public class Chaining {
	public Vertex [] A;
	public Chaining(){
		A=new Vertex[13];
		for(int i=0;i<13;i++){
			A[i]=new Vertex();
		}
	}
	// h=k%13;
	public boolean insert(int k){
		int h=k%13;
		 
		if(A[h].key==-1){
			A[h].key=k;
		}
		else{
			A[h].add(new Vertex(k));
		}
		return true;
	}
	public boolean search(int k){
		int h=k%13;
		if(A[h].key==k){
			return true;
		}
		else{
			for(int i=0;A[h].next!=null&& i<A[h].next.size();i++){
				if(A[h].next.get(i).getKey()==k){
					return true;
				}
			}
		}
		return false;
	}
	public boolean delete(int k){
		int h=k%13;
		if(A[h].key==k){
			if(A[h].next==null){
				A[h].key=-1;
			}
			else{
				A[h].key=A[h].next.remove(0).getKey();
			}
			return true;
		}
		else{
			for(int i=0;A[h].next!=null&& i<A[h].next.size();i++){
				if(A[h].next.get(i).getKey()==k){
					A[h].next.remove(A[h].next.get(i));
					return true;
				}
			}
		}
		return false;
	}
	//内部类
	class Vertex{
		public int key=-1;
		private ArrayList<Vertex> next=null;
		public Vertex(int key){
			this.key=key;
		}
		public Vertex(){
			
		}
		public void setKey(int key){
			this.key=key;
		}
		public int getKey(){
			return this.key;
		}
		//这个很重要 ，只有调用了 add 方法 ，next 才真正定义，及对已 数组头元素调用了add之后，才new,
		//对于链表中的对象，由于没有调用add 方法，所以 next 一直 为null,节约了内存空间
		public void add(Vertex v){
			if(next==null){
				next=new ArrayList<Vertex>();
			}
			next.add(v);
		}
		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Chaining c=new Chaining();
         //System.out.println(c.search(10));
         c.insert(1);
         c.insert(14);
         c.insert(27);
         c.insert(12);
         System.out.println(c.search(12));
         System.out.println(c.delete(1));
         for(int i=0;i<13;i++){
        	 System.out.println(i+"::"+c.A[i].getKey());
        	 for(int j=0;c.A[i].next!=null&& j<c.A[i].next.size();j++){
        		 System.out.println(i+"::"+j+"::"+c.A[i].next.get(j).getKey());
        	 }
         }
	}

}
