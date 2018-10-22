/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    TODO:*  add your name(s) and lecture numbers here
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   TODO: add assignment due date and time
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */

import java.util.NoSuchElementException;
import java.util.ArrayList;

// TODO: comment and complete your HashTableADT implementation
// DO NOT ADD PUBLIC MEMBERS TO YOUR CLASS
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// 
// if open addressing: describe probe sequence 
// if buckets: describe data structure for each bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       you may use the hashCode provided by the <K key> object
// 
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	//TODO: Documentation
  private static class Node<K,V> {
	  //Instance Variables
	  K key;
	  V value;
	  Node<K,V> next;
	  //Constructor
	  public Node (K key, V value) {
	    this.key = key;
	    this.value = value;
	    next = null;
	    }
//    public String toString() {
//      System.out.println("Key: " .toString(),"Value: ",value.toString());
//      System.out.print(next.toString());
    
    //Need to add methods to get, add, and delete nodes
//      
//      
//	  }
	}
  
  private ArrayList<Node<K,V>>[] table;
  private ArrayList<Integer> primeList = new ArrayList<Integer>();
  private int primeIndex;
  private int numNodes;
  private int size;
  private double hashLoadFactor;

	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation

	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
		primeList.add(11);
		primeList.add(23);
		primeList.add(47);
		primeList.add(97);
		primeList.add(301);
		primeList.add(1011);
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
		System.out.println("Hash Table Created!"); 
        System.out.println("Number of Buckets " + numNodes); 
        System.out.println("Load Factor : " + hashLoadFactor + "\n"); 
	}
	
	// TODO: comment and complete a constructor that accepts initial capacity and load factor
	public HashTable(int initialCapacity, double loadFactor) {
		primeList.add(11);
		primeList.add(23);
		primeList.add(47);
		primeList.add(97);
		primeList.add(301);
		primeList.add(1011);
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
		else if (initialCapacity<1011) {
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
		System.out.println("Hash Table Created!"); 
        System.out.println("Number of Buckets " + numNodes); 
        System.out.println("Load Factor : " + hashLoadFactor + "\n"); 
		}
 
	// TODO: comment and complete this method
	
	private int getIndex(K key) {
	  int hash = key.hashCode();
		int index = -1;
		/* I am not sure if this length is going to return the number of nodes, but if this doesn't work
		 * we can track as a variable of the hashTable.
		 */
		index = hash % table.length;
		if (index == -1) 
		  throw new NoSuchElementException();
		return index;
	}
  /*
   *Returns:the value to which the specified key is mapped, or null if this map contains no 
   *mapping for the key
   *Throws:
   *NoSuchElementException - if the specified key is null and this map does not
   *permit null keys(optional)
   *
   */ 
  @Override
  public V get(K key) throws NoSuchElementException {
    if (key == null)
       return null;
    V value = getValue(key);
    return value;
  }
  
  // TODO: comment and complete this method
  
  private V getValue(K key) {
    int hash = key.hashCode();
    //Find which node to add to
    int index = hash % table.length;
//    Node<K,V> currNode = table[index].get;
    
    //Loop through ArrayList to try and find value.
    for (int i = 0; i < table[index].size(); i++) {
      if (table[index].get(i).key == key)
        return table[index].get(i).value;
    
    }
      
      throw new NoSuchElementException();
  }
  
  /** inserts a <key,value> pair entry into the hash table if the key already exists in the table, 
   *  replace existing value for that key with the value specified in this call to put.
   *  Permits null values but not null keys and permits the same value to be paired 
   *  with different key.
   * 
   *  throw IllegalArgumentException when key is null
   */
  @Override  
	public void put(K key, V value) throws IllegalArgumentException {
    if (key == null)
      throw new IllegalArgumentException();
    putIndex(key,value);
	}
	
  // TODO: comment and complete this method
  
  private void putIndex(K key, V value) {
    int putindex = getIndex(key);
    Node<K,V> firstNode = table[putindex].get(0);
    while (firstNode != null) { 
    	  
        // If already present the value is updated 
        if (firstNode.key.equals(key)) { 
        	firstNode.value = value; 
            return; 
        } 
        firstNode = firstNode.next; 
    } 
    Node<K,V> newNode = new Node<K,V>(key,value);
    firstNode = table[putindex].get(0);
    newNode.next = firstNode;
    table[putindex].add(0,newNode);
    System.out.println(key + ":" + value + " inserted!");
    size++;
    double currentLoadFactor = (1.0 * size) / numNodes; 
    
    System.out.println("Current Load factor = " + currentLoadFactor); 

    if (currentLoadFactor > hashLoadFactor) { 
        System.out.println("Rehashing: " + currentLoadFactor); 
        // Rehash 
        rehash(); 

        System.out.println("Number of Buckets: " + numNodes + "\n"); 
    } 

    System.out.println("Number of items: " + size); 
    System.out.println("Size of Map: " + numNodes + "\n"); 
  }

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
		System.out.println("Hash Table Created!"); 
      System.out.println("Number of Buckets " + numNodes); 
      System.out.println("Load Factor : " + hashLoadFactor + "\n");

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

      System.out.println("Rehashed"); 
  } 
	// TODO: comment and complete this method
	@Override
	public void remove(K key) throws NoSuchElementException {

	}
	
	// TODO: comment and complete this method
	@Override
	public int size() {
		return -1;
	}
}
