
public class test {
	public static void main(String[] args){
		AVL_Tree<Integer,Object> avl = new AVL_Tree<Integer, Object>();
		
//		int[] bookEg = {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
//		for(int i=0; i<bookEg.length; i++)
//			avl.insert(new Node<Integer,Object>(bookEg[i]));

		int[] arr = {30,89,80,25,90,53,12,79,64,58,55,17,11,32,98,29,10,45,40,21,66,92,88,97,39,86,16,67,74,94,50,13,47,100,81,83,95,59,33,41,70,48,14,36};
		for(int i=0; i<arr.length; i++)
			avl.insert(new Node<Integer,Object>(arr[i]));
		
		for(int i=0; i<arr.length-7; i++)
			avl.delete(avl.search(arr[i]));
		avl.showTree();
		System.out.println(avl.getTreeHeight());
		System.out.println(avl.getSize());
		

	}
}
