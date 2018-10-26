/**
 * Filename:   Profile.java
 * Project:    p3
 * Authors:    Jie Gu and Ryan Ruenroeng and CS400 Epic Lecture
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10/24/2018
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       None
 */


import java.util.TreeMap;

/** 
 * 
 * Tests hash table implementations.
 * 
 */

/** Generates a BST when called that will obey the rules of an AVL tree.
 * @param <K> Generic Type
 * @param <V> Generic Type
 */

public class Profile<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;
  
  /**
   * Constructor for the testing profile.
   */
	public Profile() {
	  hashtable = new HashTable<K,V>();
	  treemap = new TreeMap<K,V>();
	}
	
  /**
   * Calls the insert methods of hashtable and treemap.
   * @param key - unique key for a given node.
   * @param value - value to be stored corresponding to that key.
   */
	
	public void insert(K key, V value) {
	  hashtable.put(key, value);
	  treemap.put(key, value);
	  
	}

  /**
   * Calls the get methods of hashtable and treemap.
   * @param key - unique key for a given node.
   */	
	
	public void retrieve(K key) {
	  hashtable.get(key);
	  treemap.get(key);
	}

  /**
   * Instantiates and tests hash table implementations.  
   * @args1  - number of integers to insert and get from the tree implementations.
   */
	
	public static void main(String[] args) {
	  
	  if (args.length < 1) {
			System.out.println("Expected 1 argument: <num_elements>");
			System.exit(1);
		}
		int numElements = Integer.parseInt(args[0]);
		
		Profile<Integer,Integer> newProfile = new Profile<Integer,Integer>();
		
		try {
      for (int i=0; i<numElements; i++) {
        newProfile.insert(i, numElements-i);
      }
      
      for (int i=0; i<numElements; i++) {
        newProfile.retrieve(i);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		
		String msg = String.format("Successfully inserted and retreived %d elements into the hash table and treemap", numElements);
		System.out.println(msg);
	}
}
