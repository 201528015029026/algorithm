package Tree;

public class BinaryTree {
	private Node root=null;
	public BinaryTree(){
		this.root=null;
	}
	public BinaryTree(Node node){
		this.root=node;
	}
	public boolean insert(Node node){
		if(this.root==null){
			this.root=node;
		}
		else{
			Node temp=this.root;
			Node x = null;
			while(temp!=null){
				x=temp;
				if(temp.getKey()<=node.getKey()){
					temp=temp.getRight();
				}
				else{
					temp=temp.getLeft();
				}
			}
			if(x.getKey()<node.getKey()){
				x.setRight(node);
			}
			else{
				x.setLeft(node);
			}
		}
		return true;
	}
	//用子树V 代替 U
	public void transplant(Node u,Node v){
		if(u.getP()==null){
			this.root=v;
		}
		else if(u==u.getP().getLeft()){
			u.getP().setLeft(v);
		}
		else {
			u.getP().setRight(v);
		}//下面的代码比较关键
		if(v!=null){
			v.setP(u.getP());
		}
	}
	public Node treeMinmum(Node v){
		while(v.getLeft()!=null){
			v=v.getLeft();
		}
		return v;
	}
	//可能返回 null 级没有后继
	public Node Successor(Node v){
		if(v.getRight()!=null){
			return treeMinmum(v.getRight());
		}
		while(v.getP()!=null&& v.getP().getRight()==v){
			v=v.getP();
		}
		return v.getP();
		
	}
	public void delete(Node v){
		if(v.getRight()==null){
			transplant(v, v.getLeft());
		}
		else if(v.getLeft()==null){
			transplant(v,v.getRight());
		}
		else{
			Node y=treeMinmum(v.getRight());
			if(y.getP()!=v){
				transplant(y, y.getRight());
				y.setRight(v.getRight());
				v.getRight().setP(y);
			}
			y.setLeft(v.getLeft());
			v.getLeft().setP(y);
			transplant(v,y);				
		}
	}

}
