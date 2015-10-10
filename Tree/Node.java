package Tree;

public class Node {
	private int key;
	private Node left;
	private Node right;
	private Node p;
	public Node getP() {
		return p;
	}
	public void setP(Node p) {
		this.p = p;
	}
	public Node(int key){
		this.key=key;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	

}
