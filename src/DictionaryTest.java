import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

public class DictionaryTest {
	static Escanner sc = new Escanner();
	static JFileChooser chooser = new JFileChooser(); //why
	static AVL_Tree<String, Object> t = new AVL_Tree<String, Object>();
	
	
	static void printOptions() {
		System.out.printf("\t\t\t\t\t\tWelcome to the dictionary System\n");
		System.out.println("Please Choose one of the following options :");
		System.out.println("1- Load the dictionary");
		System.out.println("2- Insert a new word");
		System.out.println("3- Remove a word");
		System.out.println("4- Look-up a word");
		System.out.println("5- Batch look-ups");
		System.out.println("6- Batch deletions");
		System.out.println("7- Print dictionary size");
		System.out.println("8- Exit");
	}
	public static File ChooseFile(){
		chooser.setDialogTitle("Load");
		chooser.setCurrentDirectory(new File("/Users/islamsalah/Desktop"));
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() != null && chooser.getSelectedFile().getName().endsWith(".txt"))
			return chooser.getSelectedFile();
		return null;
	}
	public static boolean insert() throws IOException{
		System.out.println("Enter the word");
		String word = sc.nextStr();
		if(t.search(word) == null){
			t.insert(new Node<String,Object>(word));
			return true;
		}
		return false;
	}
	public static boolean remove() throws IOException{
		System.out.println("Enter the name of the node to be removed");
		String word = sc.nextStr();
		if(t.search(word) != null){
			t.delete(t.search(word));
			return true;
		}
		return false;
	}
	public static boolean lookup() throws IOException{
		System.out.println("Enter the word");
		String word = sc.nextStr();
		if(t.search(word) != null)
			return true;
		return false;
	}
	public static boolean loadDic() throws IOException {
		File file = ChooseFile();
		if(file == null )
			return false;
		BufferedReader in = new BufferedReader(new FileReader(file));
		while(in.ready()){
			String w = in.readLine();
			if(t.search(w) == null)
				t.insert(new Node<String, Object>(w));
		}
		in.close();
		return true;
	}
	public static boolean batchLookUps() throws IOException{
		File file = ChooseFile();
		if(file == null )
			return false;
		BufferedReader in = new BufferedReader(new FileReader(file));
		while(in.ready()){
			String w = in.readLine();
			if(t.search(w) != null)
				System.out.println(w+" YES");
			else
				System.out.println("NO");
		}
		in.close();
		return true;
		
	}
	public static boolean batchDelete() throws IOException{
		File file = ChooseFile();
		if(file == null )
			return false;
		BufferedReader in = new BufferedReader(new FileReader(file));
		while(in.ready()){
			String word = in.readLine();
			if(t.search(word) != null)
				t.delete(t.search(word));
		}
		in.close();
		return true;
	}
	 
	public static void loading() throws IOException{
		if(loadDic())
			System.out.println("Loaded!");
		else
			System.err.println("Choose txt File!");
//		t.showTree();
	}
	public static void insertion() throws IOException{
		if(insert())
			System.out.println("size:"+t.getSize()+" "+t.getTreeHeight());
		else
			System.err.println("Word already in the dictionary!");
	}
	public static void deletion() throws IOException{
		if(remove())
			System.out.println("size:"+t.getSize()+" "+t.getTreeHeight());
		else
			System.err.println("Word doesn't Exist!");
	}
	public static void Searching() throws IOException{
		if(lookup())
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	public static void batchSearching() throws IOException{
		if(batchLookUps())
			System.out.println("size:"+t.getSize()+" "+t.getTreeHeight());
		else
			System.err.println("Choose txt File!");
	}
	public static void batchDeletion() throws IOException{
		if(batchDelete())
			System.out.println("size:"+t.getSize()+" "+t.getTreeHeight());
		else
			System.err.println("Choose txt File!");
	}
	
	public static void main(String[] args) throws IOException {	
		printOptions();
		while (true) {			
			int c = sc.nextInt();
			switch (c) {
			case 1:
				loading();
				break;
			case 2:
				insertion();
				break;
			case 3:
				deletion();
				break;
			case 4:
				Searching();
				break;
			case 5:
				batchSearching();
				break;
			case 6:
				batchDeletion();
				break;
			case 7:
				System.out.println(t.getSize());
				break;
			case 8:
				break;
			default:
				System.out.println("Choose one of the shown options");
				break;

			}
			if(c == 8)
				break;
		}
	}
	static class Escanner{
		BufferedReader in;
		StringTokenizer st;
		Escanner(){
			in = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer("");
		}
		String nextStr()throws IOException{
			if(st.hasMoreTokens())
				return st.nextToken();
			st = new StringTokenizer(in.readLine());
			return nextStr();
			
		}
		int nextInt() throws IOException{
			return Integer.parseInt(nextStr());
		}
	}
}

