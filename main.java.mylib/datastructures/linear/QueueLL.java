package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class QueueLL<T> extends SLL<T>{

//	- 2 constructors:
//	o Default constructor with no arguments that creates a null head object
//	o Overload constructor with a Node object argument to use as head
//	o You may combine both using default arguments if you prefer to
	public QueueLL() {
		super();
	}
	
	public QueueLL(DNode<T> node) {
		super(node);
	}

	public void enqueue(DNode<T> node) {
		super.insertTail(node);
	}
	
	public DNode<T> dequeue(){
		DNode<T> temp = super.head;
		if (temp != null) {
			super.deleteHead();
		}
		return temp;
	}
	
	public DNode<T> peek(){
		return super.head;
	}
	
	public boolean empty() {
		return (super.size == 0);
	}
	
	public int size() {
        return super.size;
    }
//	- InsertHead(node)
//	o Inserts node object at head of the list
	public void insertHead(DNode<T> node) {
		
	}

//	- InsertTail(node)
//	o Inserts node object at the tail of the list
	public void insertTail(DNode<T> node) {
		
	}

//	- Insert(node,position)
//	o Inserts node object in the specified position
//	▪ Ex. Insert(node ,5) → inserts node to 5th position in list
	public void insert(DNode<T> node, int position) {
		
	}

//	- SortedInsert(node)
//	o Inserts node object in its proper position in a sorted list
//	o Must check for list sort validity
//	▪ If list is found to be out of order, it must call the sort function first before
//	inserting
//	▪ Note that you should only execute sort if the list is found to be out of order
//	to avoid slowing down the insertion by executing sorting every time you
//	insert
//	▪ Might need to implement a helper function isSorted(), or find a creative
//	way to know if the list is sorted
	public void sortedInsert(DNode<T> node) {
		
	}

//	- Search(node)
//	o Looks up node in the list
//	▪ If found it returns the object
//	▪ Otherwise returns null

//	- DeleteHead()
//	o Delete head node
	public void deleteHead() {
		
	}

//	- DeleteTail()
//	o Delete tail node
	public void deleteTail() {
		
	}

//	- Delete(node)
//	o Deletes the node if found in the list
	public void delete(DNode<T> node) {
		
	}

//	- Sort()
//	o Applies insertion sort to the list
//	o The insertion part will start from the head unlike the usual insertion sort algorithm
//	▪ Instead of tracking back the list
//	o Note that the sort method and SortedInsert can use each other to efficiently
//	reduce code redundancy (not mandatory)
	public void sort() {
		
	}

//	- Print()
//	o Prints the list information on the screen, this includes
//	▪ List length
//	▪ Sorted status
//	▪ List content
//	▪ Make sure to show information with relevant print statements to be
//	readable by the user
	public void print() {
		
	}
	
}