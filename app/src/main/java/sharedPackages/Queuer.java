/*
		 Title: Queuer.java
		 Programmer: hugo
		 Date of creation: May 4, 2015
		 Description: A class that facilitates the creation and management of a queue. 
*/


package sharedPackages;

import java.util.Vector;


public class Queuer <T>{ // T is the datatype that will be passed to the class when making this object.
	private Vector<T> queue; // A Vector is like an array, but can be extended. (It is not a fixed length.) Notice how
							 // it is of type "T", the type that is passed when making the object.
	
	public Queuer() { // the constructor. It does not need a parameter for length of the array because vector are
						// variable size.
		queue = new Vector<T>(); //Initializes the queue variable with the correct type. 
	}
	

	public T front(){
		return queue.get(size() - 1); // return the item at the last index.
	}
	

	public int size(){ // note that it does not used the passed type, because the length of an array is not affected by
						// the types it holds.
		return queue.size();
	}

	public T deQueue(){
		return queue.remove(size() - 1); // the remove function reduces the size of the vector array by one.
	}
	

	public void enQueue(T number){
		queue.insertElementAt(number, 0); //insert increases the size of the vector array by one. inserted at the back. 
	}
	

	public boolean isEmpty(){
		return (queue.size()) == 0 ? true : false;  //one line if else; if size == 0 -> true; else -> false;
	}
	

	public void makeEmtpy(){
		queue.clear();
	}
	
}
