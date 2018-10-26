import java.util.NoSuchElementException;
public class Main {

	public static void main(String[] args) {
		HashTable<String,Integer> MainTable = new HashTable<String,Integer>();
		//This should not fail.
		//MainTable.put(55,null);
		try{
			MainTable.put("Alabama", 1);
			MainTable.put("Alaska", 2);
			MainTable.put("Arizona", 3);
			MainTable.put("Arkansas", 4);
			MainTable.put("California", 5);
			MainTable.put("Colorado", 6);
			MainTable.put("Connecticut", 7);
			MainTable.put("Delaware", 8);
			MainTable.put("Florida", 9);
			MainTable.put("Georgia", 10);
			MainTable.put("Hawaii",11);
			MainTable.put("Idaho",12);
			MainTable.put("Illinois",13);
			MainTable.get("Arkansas");
			MainTable.get("Idaho");
//			MainTable.get("New Hampshire");
//			MainTable.remove("Vermont");
			MainTable.get("Hawaii");
			MainTable.remove("Hawaii");
			MainTable.remove("Arkansas");
//			MainTable.get("Hawaii");
			MainTable.remove(null);
		} catch(NoSuchElementException e) {
			System.out.println("There is no such element!");
		} catch(IllegalArgumentException e) {
			System.out.println("That is an illegal argument!");
					}
	}
}
	
