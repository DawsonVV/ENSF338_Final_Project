package main.java.mylib.datastructures.linear;

public class SinglyLinkedList<T> {
//	1. singlyLL:
//		This is a Singly Linked List Data structure that will implement the following:
//		- Uses a head object of the base class Node (to be implemented as part of the base classes
//		mentioned previously) and a tail object to keep track of the end of the list
		private Node<T> head;
		private Node<T> tail;
//		- Has an integer member variable to keep track of the size of the List (update when
//		necessary)
		private int size = 0;
	
//		- 2 constructors:
//		o Default constructor with no arguments that creates a null head object
//		o Overload constructor with a Node object argument to use as head
//		o You may combine both using default arguments if you prefer to
		public SinglyLinkedList() {
			this.head = null;
			this.tail = null;
		}
		
		public SinglyLinkedList(Node<T> node) {
			this.head = node;
			Node<T> current = node;
			this.size = 1;
			while(current.getNext() != null) {
				current = current.getNext();
				this.size++;
			}
			this.tail = current;
		}
	
//		- InsertHead(node)
//		o Inserts node object at head of the list
		public void insertHead(Node<T> node) {
			Node<T> current = node;
			this.size ++;
			while(current.getNext() != null) {
				current = current.getNext();
				this.size ++;
			}
			current.setNext(this.head);
			this.head = current;
		}
	
//		- InsertTail(node)
//		o Inserts node object at the tail of the list
		public void insertTail(Node<T> node) {
			this.tail.setNext(node);
			Node<T> current = node;
			this.size ++;
			while(current.getNext() != null) {
				current = current.getNext();
				this.size ++;
			}
			this.tail = current;
			
		}
	
//		- Insert(node,position)
//		o Inserts node object in the specified position
//		▪ Ex. Insert(node ,5) → inserts node to 5th position in list
		public void insert(Node<T> node, int position) {
			if (position == 1) {
				insertHead(node);
			}else if(position == size + 1) {
				insertTail(node);
			}else {
				Node<T> current = node;
				for (int i = 0; i < position - 1; i++) {
	                current = current.getNext();
	            }
				node.setNext(current.getNext());
				current.setNext(node);
				size++;
			}
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
		public void sortedInsert(Node<T> node) {
			
		}
	
//		- Search(node)
//		o Looks up node in the list
//		▪ If found it returns the object
//		▪ Otherwise returns null
		public Node<T> search(Node<T> node) {
			if (node == this.head) {
				return this.head;
			}else if (node == this.tail) {
				return this.tail;
			}else {
				Node<T> current = this.head;
				while(current != null) {
					if(node == current) {
						return current;
					}
					current = current.getNext();
				}
				return null;
			}
		}
	
//		- DeleteHead()
//		o Delete head node
		public void deleteHead() {
			if (this.head != null) {
				this.head = this.head.getNext();
				this.size --;
				if(this.head == null) {
					this.tail = null;
				}
			}
		}
	
//		- DeleteTail()
//		o Delete tail node
		public void deleteTail() {
			if(this.tail != null) {
				if(this.head.getNext() == null) {
					this.head = null;
					this.tail = null;
				}else {
					Node current = this.head;
					while(current.getNext() != tail) {
						current = current.getNext();
					}
					this.tail = current;
					this.tail.setNext(null);
				}
				this.size--;
			}
		}
	
//		- Delete(node)
//		o Deletes the node if found in the list
		public void delete(Node<T> node) {
			if(this.head != null) {
				if(this.head == node) {
					deleteHead();
				}else if(this.tail == node) {
					deleteTail();
				}else {
					Node<T> current = this.head;
					while(current.getNext() != node) {
						current = current.getNext();
					}
					current.setNext(node.getNext());
					this.size--;
				}
			}
		}
	
//		- Sort()
//		o Applies insertion sort to the list
//		o The insertion part will start from the head unlike the usual insertion sort algorithm
//		▪ Instead of tracking back the list
//		o Note that the sort method and SortedInsert can use each other to efficiently
//		reduce code redundancy (not mandatory)
		public void sort() {
			
		}
	
//		- Clear()
//		o Deletes the whole list
		public void clear() {
			this.head = null;
			this.tail = null;
			this.size = 0;
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
}