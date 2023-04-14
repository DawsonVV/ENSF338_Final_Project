package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class StackLL<T> extends SLL<T>{
//		This is a Singly Linked List Data structure that will implement the following:
//		- Uses a head object of the base class Node (to be implemented as part of the base classes
//		mentioned previously) and a tail object to keep track of the end of the list
		protected DNode<T> head = null;
		protected DNode<T> tail = null;
//		- Has an integer member variable to keep track of the size of the List (update when
//		necessary)
		protected int size = 0;
	
//		- 2 constructors:
//		o Default constructor with no arguments that creates a null head object
//		o Overload constructor with a Node object argument to use as head
//		o You may combine both using default arguments if you prefer to
		public StackLL() {
			super();
		}
		
		public StackLL(DNode<T> node) {
			super(node);
		}
		
		public void push(DNode<T> node) {
			super.insertHead(node);
		}
		
		public DNode<T> pop(){
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
		
//		- InsertHead(node)
//		o Inserts node object at head of the list
		public void insertHead(DNode<T> node) {

		}
	
//		- InsertTail(node)
//		o Inserts node object at the tail of the list
		public void insertTail(DNode<T> node) {
			
		}
	
//		- Insert(node,position)
//		o Inserts node object in the specified position
//		▪ Ex. Insert(node ,5) → inserts node to 5th position in list
		public void insert(DNode<T> node, int position) {
			
		}
	
//		- SortedInsert(node)
//		o Inserts node object in its proper position in a sorted list
//		o Must check for list sort validity
//		▪ If list is found to be out of order, it must call the sort function first before
//		inserting
//		▪ Note that you should only execute sort if the list is found to be out of order
//		to avoid slowing down the insertion by executing sorting every time you
//		insert
//		▪ Might need to implement a helper function isSorted(), or find a creative
//		way to know if the list is sorted
		public void sortedInsert(DNode<T> node) {
			
		}
	

//		- DeleteHead()
//		o Delete head node
		public void deleteHead() {
			
		}
	
//		- DeleteTail()
//		o Delete tail node
		public void deleteTail() {
			
		}
	
//		- Delete(node)
//		o Deletes the node if found in the list
		public void delete(DNode<T> node) {
			
		}
	
//		- Sort()
//		o Applies insertion sort to the list
//		o The insertion part will start from the head unlike the usual insertion sort algorithm
//		▪ Instead of tracking back the list
//		o Note that the sort method and SortedInsert can use each other to efficiently
//		reduce code redundancy (not mandatory)
		public void sort() {

		}
	
//		- Print()
//		o Prints the list information on the screen, this includes
//		▪ List length
//		▪ Sorted status
//		▪ List content
//		▪ Make sure to show information with relevant print statements to be
//		readable by the user
		public void print() {

		}
		
		public static void main(String[] args) {
			DNode<Integer> node1 = new DNode<Integer>(10);
			DNode<Integer> node2 = new DNode<Integer>(9);
			DNode<Integer> node3 = new DNode<Integer>(8);
			DNode<Integer> node4 = new DNode<Integer>(7);
			DNode<Integer> node5 = new DNode<Integer>(6);
			DNode<Integer> node6 = new DNode<Integer>(5);
			DNode<Integer> node7 = new DNode<Integer>(4);
			DNode<Integer> node8 = new DNode<Integer>(3);
			DNode<Integer> node9 = new DNode<Integer>(2);
			DNode<Integer> node10 = new DNode<Integer>(1);
		
			StackLL<Integer> stack = new StackLL<Integer>();

		    System.out.println("Stack is empty? " + stack.empty()); // true

		    stack.push(node10);
		    stack.push(node9);
		    stack.push(node8);

		    System.out.println("Top element: " + stack.peek().getData()); // 3
		    System.out.println("Stack size: " + stack.size()); // 3
		    System.out.println("Stack is empty? " + stack.empty()); // false

		    stack.pop();

		    System.out.println("Top element: " + stack.peek().getData()); // 2
		    System.out.println("Stack size: " + stack.size()); // 2

		    stack.pop();
		    stack.pop();

		    System.out.println("Stack is empty? " + stack.empty()); // true

		    stack.pop();
		    
		    stack.peek();
		}
}