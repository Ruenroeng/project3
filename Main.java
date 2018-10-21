
public class Main {

	public static void main(String[] args) {
		System.out.println("What up!");
		HashTable<Integer,Integer> MainTable = new HashTable<Integer,Integer>();
		//This should not fail.
		//MainTable.put(55,null);
		
		MainTable.put(2, 22);
	}
}
	
