
public class Node<K,V>{
	private Node<K,V> lChild;
	private Node<K,V> rChild;
	private Node<K,V> parent;
	private K key;
	private V value;
	private int height;
	
	public Node(K key){
		this.key = key;
		lChild = null;
		rChild = null;
		parent = null;
		value = null;
		height = 0;
	}
	public Node(K key, V value){
		this.key = key;
		lChild = null;
		rChild = null;
		parent = null;
		this.value = value;
	}
	
	public void setLChild(Node<K,V> n){
		lChild = n;
	}
	public Node<K,V> getLChild(){
		return lChild;
	}
	public void setRChild(Node<K,V> n){
		rChild = n;
	}
	public Node<K,V> getRChild(){
		return rChild;
	}
	public void setParent(Node<K,V> n){
		parent = n;
	}
	public Node<K,V> getParent(){
		return parent;
	}
	public void setKey(K key){
		this.key = key;
	}
	public K getKey(){
		return key;
	}
	public V getValue(){
		return value;
	}
	public void setValue(V value){
		this.value = value;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
}
