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
//Hi Jie! Hi!
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	//TODO: Documentation
  private static class Node<K,V> extends ArrayList<K> {
	  //Instance Variables
	  private K key;
	  private V value;
	  private Node<K,V> next;
	  
	  //Constructor
	  private Node () {
	    }
//    public String toString() {
//      System.out.println("Key: " .toString(),"Value: ",value.toString());
//      System.out.print(next.toString());
    
    //Need to add methods to get, add, and delete nodes
//      
//      
//	  }
	}
  
  private Node<K,V>[] table;
	
  private int numNodes;
	private int size;
	private double hashLoadFactor;

	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation

	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
		table = (Node<K,V>[]) new Node<?,?>[11];
		size = 0;
		hashLoadFactor = 0.7;
	}
	
	// TODO: comment and complete a constructor that accepts initial capacity and load factor
	public HashTable(int initialCapacity, double loadFactor) {
		this.table = (Node<K,V>[]) new Node<?,?>[initialCapacity];
		size = 0;
		hashLoadFactor = loadFactor;
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
    Node<K,V> currNode = table[index];
    
    //Loop through ArrayList to try and find value.
    for (int i = 0; i < table[index].size(); i++) {
      if (currNode.key == key)
        return currNode.value;
    currNode = currNode.next;
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
    int hash = key.hashCode();
    int index = hash % table.length;
    //Needed if node has never been touched before. How can we instantiate all of the lists.
    if (table[index].key == null) {
      //Also not adding a key/value pair.
      table[index].add(key);
    }
    Node<K,V> currNode = table[index];
    for (int i = 0; i < table[index].size(); i++) {
      
      if (currNode.key == key) {
        table[index].value = value;
        this.size++;
        return;
      }
      currNode = table[index].next;
    }
      table[index].add(key);
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
