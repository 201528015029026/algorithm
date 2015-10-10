package graph.storge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private Vertex[] vlist;
	private boolean isDirected;//是否是有向图
	private int nvers=0;
	private int nedges=0;
	private int init_vers=20;
	private int time=0;
	
	public Graph(){
		vlist=new Vertex[init_vers];
	}
	public Graph(int n,boolean isis){
		vlist=new Vertex[n];
		isDirected=isis;
		 
	}
	public void addVertex(Vertex v){
		v.setIndex(nvers);
		System.out.println(nvers);
		vlist[nvers]=v;
		nvers++;
		
	}
	public void addEdge(int a,int b){
		vlist[a].addAdj(vlist[b]);
		nedges++;
		if(!isDirected){
			vlist[b].addAdj(vlist[a]);
		}
		
	}
	public ArrayList<Vertex> bfs(){
		ArrayList<Vertex> list=new ArrayList<Vertex>();
		Queue<Vertex> queue=new LinkedList<Vertex>();
		queue.offer(vlist[0]);
		list.add(vlist[0]);
		Vertex v1; 
		// 下面的for 循环是为了 记录节点的父节点，便于输出从源节点到目标节点的最短路径中中的节点
		for(int i=0;i<vlist.length;i++){
			vlist[i].p=null;
		}
		while(!queue.isEmpty()){
			v1=queue.poll();
			ArrayList<Vertex> adjlist=v1.getAdj();
			for(int i=0;adjlist!=null && i<adjlist.size();i++){
				Vertex v2=adjlist.get(i);
				if(v2.getIsVisited()==false){
					queue.offer(v2);
					v2.setIsVisited(true);
					//为了输出路径
					v2.p=v1;
					list.add(v2);
				}
			}
		}
		// 这个是好习惯，访问完了 ，把状态还原
		for(int i=0;i<nvers;i++){
			vlist[i].setIsVisited(false);
		}
		return list;
	}
	public void printPath(Vertex s,Vertex v){
		if(s==v){
			System.out.println(s.toString());
		}
		else if(v.p==null){
			System.out.println("no path to s!");
		}
		else{
			printPath(s,v.p);
			System.out.println(v.toString());
		}
	}
	public ArrayList<Vertex> dfs(){
		ArrayList<Vertex> list=new ArrayList<Vertex>();
		List<Vertex> templist=new ArrayList<Vertex>();
		Stack<Vertex> s=new Stack<Vertex>();
		s.push(vlist[0]);
		list.add(vlist[0]);
		Vertex temp;
		Vertex v = null;
		while(!s.isEmpty()){
			temp=s.peek();
			templist=temp.getAdj();
			int i=0;
//			if(templist!=null && temp.getTempPosition()<templist.size()){
//				temp.setTempPosition(temp.getTempPosition());
//			}
			for(i=0;templist!=null && i<templist.size();i++){
				v=templist.get(i);
				// 多做一个 交换操作，保证 时间复杂度O(V+E),否则时间复杂度是O(VE)
				
				if(v.getIsVisited()==false){
					break;
				}
			}
			if(templist!=null && i<templist.size()){
				s.push(v);
				list.add(v);
				v.setIsVisited(true);
			}
			else{
				s.pop();
			}
		}
		for(int i=0;i<nvers;i++){
			vlist[i].setIsVisited(false);
		}
		
		return list;
	}
	//下面的复杂度为 O(V+E)
	// 下面的代码的通用性更好，适用于不连通图，有向图，无向图
	public void DFS2(){
		for(int i=0;i<vlist.length;i++){
			vlist[i].color='w';
			vlist[i].p=null;
		}
		time=0;
		for(int i=0;i<vlist.length;i++){
			if(vlist[i].color=='w'){
				DFS_VISIT(vlist[i]);
			}
		}
	}
	public void DFS_VISIT(Vertex v){
		time++;
		v.color='g';
		// 在 这个位置输出既是 dfs 节点访问顺序
		System.out.println(v.toString());
		for(int i=0;v.getAdj()!=null && i<v.getAdj().size();i++ ){
			if(v.getAdj().get(i).color=='w'){
				v.getAdj().get(i).p=v;
				DFS_VISIT(v.getAdj().get(i));
			}
		}
		v.color='b';
		//在这个位置输出既是 拓扑排序的逆序
		//System.out.println(v.toString());
		time++;
	}
	
//	public Vertex getNextVertex(Vertex v){
//		Vertex vertex;
//		ArrayList<Vertex> list=v.getAdj();
//		for(int i=0;list!=null && i<=list.size();i++){
//			if(list)
//		}
//		return vertex;
//	}
	public static void main(String[] args){
		Graph g=new Graph(5,true);
		int c='A';
		for(int i=0;i<5;i++){
			Vertex v=new Vertex((char)(c+i));
			g.addVertex(v);
		}
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(1, 4);
		g.addEdge(0, 4);
		//g.addEdge(1, 2);
//		g.addEdge(2, 4);
//		g.addEdge(4, 3);
		System.out.println(g.nvers+"::"+g.nedges);
		ArrayList<Vertex> list=g.vlist[3].getAdj();
		//这种代码就可能存在问题 如果 list 为 null 就会报错
		//for(int i=0;i<list.size();i++){
//		for(int i=0;list!=null&&i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
//	    list=g.bfs();
//	    for(int i=0;list!=null && i<list.size();i++){
//	    	System.out.println(list.get(i).toString());
//	    }
	    list=g.bfs();
	    for(int i=0;list!=null && i<list.size();i++){
	    	System.out.println(list.get(i).toString());
	    }
	    System.out.println("---------");
	    g.printPath(g.vlist[0], g.vlist[4]);
	}
}
