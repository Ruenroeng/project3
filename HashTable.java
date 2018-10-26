/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    Ryan Ruenroeng and Jie Gu
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10/27/2018
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       No known bugs
 */

import java.util.NoSuchElementException;
import java.util.ArrayList;

/** Hashtable implementation. Buckets of ArrayLists are chosen for collision resolution. The
 * standard hashCode algorithm was used to hash key values.
 * @param <K> Generic Type
 * @param <V> Generic Type
 */

public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  /** Sub-class of the HashTable. Each Node will act as a linked list.
   * @param <K> Generic Type
   * @param <V> Generic Type
   */
  
  private static class Node<K,V> {
	//Instance Variables
	  K key;
	  V value;
	  Node<K,V> next;
	  
	  /**Constructor for a new Node.
	   * @param key - unique key for a given node.
	   * @param value - value to be stored corresponding to that key.
	   */
	  private Node (K key, V value) {
	    this.key = key;
	    this.value = value;
	    next = null;
	    }
	}
  
  //Data Field Members
  private ArrayList<Node<K,V>>[] table;
  
  //List of prime numbers used to resize the HashTable
  private ArrayList<Integer> primeList = new ArrayList<Integer>();
  private int primeIndex;
  private int numNodes;
  private int size;
  private double hashLoadFactor;

  /**No argument constructor for a new HashTable.
  */
	public HashTable() {
		//Created a linked list of prime values to track size and next size of HashTable.
	  primeList.add(11);
		primeList.add(23);
		primeList.add(47);
		primeList.add(97);
		primeList.add(301);
		primeList.add(1013);
		primeIndex = 0;
		numNodes = primeList.get(primeIndex);
		primeIndex ++;
		this.table = new ArrayList[numNodes];
		this.size = 0;
		this.hashLoadFactor = 0.7;
		for (int i = 0; i < numNodes; i++) { 
            table[i] = new ArrayList<Node<K,V>>();
            table[i].add(null);
        } 
	}
	/**Parameterized constructor for a HashTable.
   * @param initialCapacity - unique key for a given node.
   * @param loadFactor - A ratio of nodes with and without data. Insertion beyond the loadFactor
   * will trigger a resizing of the HashTable.
   */
	
	public HashTable(int initialCapacity, double loadFactor) {
	  //Created a linked list of prime values to track size and next size of HashTable.
	  primeList.add(11);
		primeList.add(23);
		primeList.add(47);
		primeList.add(97);
		primeList.add(301);
		primeList.add(1013);
		if (initialCapacity<11) {
			primeIndex = 0;
		}
		else if(initialCapacity<23) {
			primeIndex = 1;
		}
		else if (initialCapacity<47) {
			primeIndex = 2;
		}
		else if (initialCapacity<97) {
			primeIndex = 3;
		}
		else if (initialCapacity<301) {
			primeIndex = 4;
		}
		else if (initialCapacity<1013) {
			primeIndex = 5;
		}
		else {
			primeIndex = 6;
		}
		numNodes = initialCapacity;
		this.table = new ArrayList[numNodes];
		this.size = 0;
		this.hashLoadFactor = loadFactor;
		for (int i = 0; i < numNodes; i++) { 
            table[i] = new ArrayList<Node<K,V>>();
            table[i].add(null);
        }  
	}
 

  /**Retrieval function for the HashTable. 
   * @return - The value corresponding to the unique key stored in the table. Otherwise it will
   * throw an exception.
   * @param key - unique key for a value to be stored in the HashTable.
   * @throws NoSuchElementException - if the specified key is null. This map does not permit 
   * null keys
   */
  @Override
  public V get(K key) throws NoSuchElementException {
    if (key == null)
       return null;
    V value = getValue(key);
    return value;
  }
  
  /**Helper function for the get function. 
   * @return - The value corresponding to the unique key stored in the table. Otherwise it will
   * throw an exception.
   * @param key - unique key for a value to be stored in the HashTable.
   * @throws NoSuchElementException - if the key's value cannot be located in the hashtable.
   */
  
  private V getValue(K key) {
	int index = getIndex(key);
    if (table[index].get(0)==null) {
    	throw new NoSuchElementException();
    }
    //Loop through ArrayList to try and find value.
    for (int i = 0; i < table[index].size(); i++) {
      if (table[index].get(i)==null){
    	  throw new NoSuchElementException();
      }
      else if (table[index].get(i).key.equals(key)) {
          return table[index].get(i).value;
        }
    }
      
      throw new NoSuchElementException();
  }
  
  /**Helper function for the getValue function. 
   * @return - The index corresponding to the node where the key's value should be stored. 
   * @param key - unique key for a value to be stored in the HashTable.
   * @throws NoSuchElementException - if an index cannot be determined corresponding to the key
   * value provided.
   */
  
  private int getIndex(K key) {
    int hash = Math.abs(key.hashCode());
    int index = -1;
    index = hash % table.length;
    if (index == -1) 
      throw new NoSuchElementException();
    return index;
  }
  
  /** Inserts a <key,value> pair entry into the hash table if the key already exists in the table. 
   *  Replaces existing value for that key with the value specified in this call to put.
   *  Permits null values but not null keys and permits the same value to be paired 
   *  with different key.
   *  @param key - unique key for a given node.
   *  @param value - value to be stored corresponding to that key.
   * 
   *  @throws IllegalArgumentException when key is null
   */
  @Override  
	public void put(K key, V value) throws IllegalArgumentException {
    if (key == null)
      throw new IllegalArgumentException();
    putIndex(key,value);
	}
	
  /**Helper function for the getValue function. 
   *  @param key - unique key for a given node.
   *  @param value - value to be stored corresponding to that key.
   */   
    
  private void putIndex(K key, V value) {
    int index = getIndex(key);
    Node<K,V> firstNode = table[index].get(0);
    while (firstNode != null) { 
    	  
        // If already present the value is updated 
        if (firstNode.key.equals(key)) { 
        	firstNode.value = value; 
            return; 
        } 
        firstNode = firstNode.next; 
    } 
    Node<K,V> newNode = new Node<K,V>(key,value);
    firstNode = table[index].get(0);
    newNode.next = firstNode;
    table[index].add(0,newNode);
    size++;
    double currentLoadFactor = (1.0 * size) / numNodes; 

    if (currentLoadFactor > hashLoadFactor) { 
        // Rehash 
        rehash(); 
    } 
  }
  /** Rehashes the the table. Intended to be called after a resize.
   * 
   *  @throws IllegalArgumentException when key is null
   */
  private void rehash() 
  { 
      // copy current table
	  ArrayList<Node<K,V>>[] temp = table; 

      // access new size of table
	  if(primeIndex<6) {
		  numNodes = primeList.get(primeIndex);
	  }
	  else {
		  numNodes = numNodes*2+1;
	  }
	  primeIndex++;
	  table = new ArrayList[numNodes];
	  size = 0;
		for (int i = 0; i < numNodes; i++) { 
          table[i] = new ArrayList<Node<K,V>>();
          table[i].add(null);
      } 

      for (int i = 0; i < temp.length; i++) { 

          // head of the chain at that index 
          Node<K, V> firstNode = temp[i].get(0); 

          while (firstNode != null) { 
              K key = firstNode.key; 
              V val = firstNode.value; 

              // calling the insert function for each node in temp 
              // as the new list is now the bucketArray 
              put(key, val); 
              firstNode = firstNode.next; 
          } 
      } 
  } 
  /** Removes a <key,value> pair entry from the hash table if the key already exists in the table. 
   *  Permits null values but not null keys and permits the same value to be paired 
   *  with different key.
   *  @param key - unique key for a given node.
   * 
   *  @throws IllegalArgumentException when key is null.
   *  @throws NoSuchElementException if the key value is not found in the HashTable.
   */
	@Override
	public void remove(K key) throws IllegalArgumentException,NoSuchElementException {
	  if (key== null)
	      throw new IllegalArgumentException();
		K returnKey = removeKey(key);
		if (returnKey == null) {
		      throw new NoSuchElementException();
		}
	}
  /** Helper function to remove function. Handles the logic of finding where the key's value should
   *  be stored and removes the value from the ArrayList using the native ArrayList remove method.
   *  @return - The key value if the removal was successful, otherwise returns null.
   *  @param key - unique key for a given node.
   * 
   *  @throws IllegalArgumentException when key is null
   */
	private K removeKey(K key){
		int removeIndex = getIndex(key);
	    for (int i = 0;i<table[removeIndex].size();i++){ 
			Node<K,V> currentNode = table[removeIndex].get(i);
			if (currentNode==null) {
	        	return null;
	        }
			else if (currentNode.key.equals(key)) {
	        	table[removeIndex].remove(i);
	            return key; 
	        }
	    }
	    return null;
	}
	/** Determines the size of the HashTable. This is equivalent to the number of nodes in the 
	 *  HashTable.
   *  @return - the number of nodes for the HashTable.
   */
	@Override
	public int size() {
		return table.length;
	}
}
