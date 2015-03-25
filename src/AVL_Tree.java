public class AVL_Tree<K extends Comparable<K>,V> extends BST<K,V>{
	
	private int height(Node<K,V> z){
		if(z == null)
			return -1;
		return z.getHeight();
	}
	private int BF(Node<K,V> z){
		return height(z.getLChild())-height(z.getRChild());
	}
	private void updateNodeH(Node<K,V> z){
		if(z != null)
			z.setHeight(Math.max(height(z.getLChild()), height(z.getRChild()))+1);
	}
	private void adjustHeight(Node<K,V> z, boolean insertion){ //can be insertion or deletion
		while(z != null){
			updateNodeH(z);
			if(Math.abs(BF(z)) == 2){
				balance(z);
				if(insertion)
					break; 										//break in case of insertion only
			}
			z = z.getParent();
		}
	}
	private void balance(Node<K,V> z){
		if(BF(z) == 2){
			if(BF(z.getLChild()) >= 0)	// we put =1 in insertion but there is cases in deletion require 0
				rotateWithLeftChild(z);
			else
				doubleRotateWithLeftChild(z);
		}else{
			if(BF(z.getRChild()) <= 0) // we put =-1 in insertion but there is cases in deletion require 0
				rotateWithRightChild(z);
			else
				doubleRotateWithRightChild(z);
		}
		updateNodeH(z.getParent().getLChild());
		updateNodeH(z.getParent().getRChild());
		updateNodeH(z.getParent());

	}
	
	
	//Q2.almost identical code of the rotateWithRightChild
	private Node<K,V> rotateWithLeftChild(Node<K,V> k2){
		Node<K,V> k1 = k2.getLChild();//
		Node<K,V> b = k1.getRChild();//
		Node<K,V> p = k2.getParent();
		if(p == null)
			this.root = k1;
		else{
			if(k2 == p.getLChild())
				p.setLChild(k1);
			else
				p.setRChild(k1);
		}
		k1.setParent(p);
		k1.setRChild(k2);//
		k2.setParent(k1);
		k2.setLChild(b);//
		if(b != null)
			b.setParent(k2);
		return k1;
	}
	private Node<K,V> rotateWithRightChild(Node<K,V> k2){
		Node<K,V> k1 = k2.getRChild();//
		Node<K,V> b = k1.getLChild();//
		Node<K,V> p = k2.getParent();
		if(p == null)
			this.root = k1;
		else{
			if(k2 == p.getLChild())
				p.setLChild(k1);
			else
				p.setRChild(k1);
		}
		k1.setParent(p);
		k1.setLChild(k2);//
		k2.setParent(k1);
		k2.setRChild(b);//
		if(b != null)
			b.setParent(k2);
		return k1;
	}
	private Node<K,V> doubleRotateWithLeftChild(Node<K,V> z){
		rotateWithRightChild(z.getLChild());
		return rotateWithLeftChild(z);
	}
	private Node<K,V> doubleRotateWithRightChild(Node<K,V> z){
		rotateWithLeftChild(z.getRChild());
		return rotateWithRightChild(z);
	}
	

	public void insert(Node<K,V> z){
		super.insert(z);
		adjustHeight(z, true);
	}
	public void delete(Node<K,V> z){
		Node<K,V> illNode;
		if(z.getLChild() == null || z.getRChild() == null) 	//cover case 1 & 2 in deletion procedure
			illNode = z.getParent();
		else if(successor(z) == z.getRChild())				//cover case 3 in deletion procedure
			illNode = successor(z);
		else
			illNode = successor(z).getParent();				//cover case 4 in deletion procedure
		super.delete(z);
		adjustHeight(illNode, false);

	}
	
	
	public int getTreeHeight(){
		return height(this.getRoot());
	}
}
