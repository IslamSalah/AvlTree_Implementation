import java.util.LinkedList;
import java.util.Queue;


public class BST<K extends Comparable<K>,V> {
	//Q1. avl Node
	protected Node<K,V> root;
	private int size;
	
	public BST(){
		root = null;
		size = 0;
	}
	
	public Node<K,V> maximum(Node<K,V> n){
		if(n == null) 
			return n;
		while(n.getRChild() != null)
			n = n.getRChild();
		return n;
	}
	public Node<K,V> minimum(Node<K,V> n){
		if(n == null) 
			return n;
		while(n.getLChild() != null)
			n = n.getLChild();
		return n;
	}
	public Node<K,V> successor(Node<K,V> n){
		if(n.getRChild() != null)
			return minimum(n.getRChild());
		Node<K,V> p = n.getParent();
		while(p!=null && n == p.getRChild()){
			n = p;
			p = p.getParent();
		}
		return p;
	}
	public Node<K,V> predecessor(Node<K,V> n){
		if(n.getLChild() != null)
			return maximum(n.getLChild());
		Node<K,V> p = n.getParent();
		while(p!=null && n == p.getLChild()){
			n = p;
			p = p.getParent();
		}
		return p;
	}
	public Node<K,V> search(K k){
		Node<K,V> n = root;
		while(n != null && k.compareTo(n.getKey()) != 0){
			if(k.compareTo(n.getKey()) < 0)
				n = n.getLChild();
			else
				n = n.getRChild();
		}
		return n;
	}
	public void insert(Node<K,V> z){
		if(root == null)
			root = z;
		else{
			Node<K,V> n = root;
			while(z.getParent() == null){
				if(z.getKey().compareTo(n.getKey()) <= 0)
					if(n.getLChild() != null)
						n = n.getLChild();
					else{
						n.setLChild(z);
						z.setParent(n);
					}
				else
					if(n.getRChild() != null)
						n = n.getRChild();
					else{
						n.setRChild(z);
						z.setParent(n);
					}
			}
		}
		size++;
		
	}
	private void replace(Node<K,V> u, Node<K,V> v){ //replace tree rooted at u with tree rooted at v
		if(u.getParent() == null)
			root = v;
		else if (u == u.getParent().getRChild())
			u.getParent().setRChild(v);
		else
			u.getParent().setLChild(v);
		if(v != null)
			v.setParent(u.getParent());
	}
	public void delete(Node<K,V> z){
		if(z.getLChild() == null)
			replace(z, z.getRChild());
		else if (z.getRChild() == null)
			replace(z, z.getLChild());
		else{
			Node<K,V> y = successor(z); // must exist in z's right subtree
			if(y != z.getRChild()){
				replace(y, y.getRChild());
				y.setRChild(z.getRChild());
				y.getRChild().setParent(y);
			}
			replace(z, y);
			y.setLChild(z.getLChild());
			y.getLChild().setParent(y);
		}
		size--;
	}
	public int getSize(){
		return size;
	}
	public Node<K,V> getRoot(){
		return root;
	}
	public void Inorder(Node<K,V> n){
		if(n == null)
			return;
		Inorder(n.getLChild());
		System.out.print(n.getKey()+" ");
		Inorder(n.getRChild());
	}
	public void showTree(){
		int nodes=0;
		Queue<Node<K,V>> q = new LinkedList<Node<K,V>>();
		q.add(root);
		System.out.println(root.getKey());
		while(!q.isEmpty()){
			Node<K,V> n = q.poll();
			if(n.getLChild()!=null){
				System.out.print(n.getLChild().getKey()+"("+n.getKey()+")"+" ");
				q.add(n.getLChild());
			}else
				System.out.print("X("+n.getKey()+")"+" ");
			if(n.getRChild()!=null){
				System.out.print(n.getRChild().getKey()+"("+n.getKey()+")"+" ");
				q.add(n.getRChild());
			}else
				System.out.print("X("+n.getKey()+")"+" ");
			nodes++;
			double h = Math.log(nodes+1)/Math.log(2)-1;
			if(Math.abs(h-(int)h)<0.00000001) //complete tree
				System.out.println();
		}
		System.out.println();
	}
}
