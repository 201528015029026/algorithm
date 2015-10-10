package graph.storge;

import java.util.ArrayList;

public class Vertex {
	private int index; 
	private char label;
	private boolean isVisited;
	public char color;
	public Vertex p=null;
	private ArrayList<Vertex> next=null;
	public Vertex(char lab){
		this.label=lab;
		this.isVisited=false;
	}
	public void  addAdj(Vertex vertex){
		if(next==null){
			next=new ArrayList<Vertex>();
		}
		next.add(vertex);
	}
	public ArrayList<Vertex> getAdj(){
		return next;
	}
	public void setIndex(int index){
		this.index=index;
	}
	public String toString(){
		return "name:"+label+"||index:"+index;
	}
	public void setIsVisited(boolean vi){
		this.isVisited=vi;
	}
	public boolean getIsVisited(){
		return this.isVisited;
	}
}
