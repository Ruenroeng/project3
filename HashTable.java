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
	private ArrayList<HashTableADT<K, V>> collisionBuckets;
	private int numBuckets;
	private int size;
	private double hashLoadFactor;
	// TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
		
	// TODO: comment and complete a default no-arg constructor
	public HashTable() {
		collisionBuckets = new ArrayList<>();
		numBuckets = 11;
		size = 0;
		hashLoadFactor = 0.7;
		for (int i = 0; i<numBuckets; i++){
			collisionBuckets.add(null);
		}
//a single comment
	}
	
	// TODO: comment and complete a constructor that accepts initial capacity and load factor
	public HashTable(int initialCapacity, double loadFactor) {
		collisionBuckets = new ArrayList<>();
		numBuckets = initialCapacity;
		size = 0;
		hashLoadFactor = loadFactor;
		for (int i=0; i< numBuckets; i++){
			collisionBuckets.add(null);
		}
	}

	// TODO: comment and complete this method
	@Override
	private int getIndex(K key){
		int hash = key.hashCode();
		int index = hash % numBuckets;
		return index;
	}
	public void put(K key, V value) throws IllegalArgumentException {

	}
	
	// TODO: comment and complete this method
	@Override
	public V get(K key) throws NoSuchElementException {
		int index = getIndex(key);
		return null;
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
