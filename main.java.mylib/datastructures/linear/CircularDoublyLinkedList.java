package main.java.mylib.datastructures.linear;
import main.java.mylib.datastructures.nodes.DNode;
public class CircularDoublyLinkedList<T> extends DoublyLinkedList<T> {
	
			
//				- 2 constructors:
//				o Default constructor with no arguments that creates a null head object
//				o Overload constructor with a Node object argument to use as head
//				o You may combine both using default arguments if you prefer to
		public CircularDoublyLinkedList() {
		}
		
		public CircularDoublyLinkedList(DNode<T> node) {
			node.setNext(node);
			node.setPrev(node);
			super.head = node;
			super.size = 1;
			super.tail = node;
		}
	
//				- InsertHead(node)
//				o Inserts node object at head of the list
		public void insertHead(DNode<T> node) {
			DNode<T> current = node;
			super.size ++;
			while(current.getPrev() != null) {
				current = current.getPrev();
				super.size ++;
			}
			current.setPrev(super.head.getPrev());
			super.head.getPrev().setNext(current);
			super.head = current;
			current = node;
			while(current.getNext() != null) {
				current = current.getNext();
				super.size ++;
			}
			current.setNext(super.tail);
			super.tail = super.head;
		}
	
//				- InsertTail(node)
//				o Inserts node object at the tail of the list
		public void insertTail(DNode<T> node) {
			insertHead(node);
			
		}

	
//				- DeleteHead()
//				o Delete head node
		public void deleteHead() {
			if (super.head != null) {
				if(super.head.getNext() == super.head) {
					super.head = null;
					super.tail = null;
				}else {
					super.head.getNext().setPrev(super.head.getPrev());
					super.head.getPrev().setNext(super.head.getNext());
					super.head = super.head.getNext();
					super.tail = super.head;
					if(super.head == null) {
						super.tail = null;
					}
				}
				super.size --;
			}
		}
	
//				- DeleteTail()
//				o Delete tail node
		public void deleteTail() {
			deleteHead();
		}
	
//		- Sort()
//		o Applies insertion sort to the list
//		o The insertion part will start from the head unlike the usual insertion sort algorithm
//		▪ Instead of tracking back the list
//		o Note that the sort method and SortedInsert can use each other to efficiently
//		reduce code redundancy (not mandatory)
		public void sort() {
		    if (this.size <= 1) {
		        return;
		    }

		    DNode<T> newHead = null;
		    DNode<T> current = this.head;
		    do {
		        DNode<T> node = current;
		        current = current.getNext();
		        if (newHead == null || Integer.parseInt(String.valueOf(node.getData())) < Integer.parseInt(String.valueOf(newHead.getData()))) {
		            node.setNext(newHead);
		            newHead = node;
		        } else {
		            DNode<T> tmp = newHead;
		            while (tmp.getNext() != null && Integer.parseInt(String.valueOf(node.getData())) > Integer.parseInt(String.valueOf(tmp.getNext().getData()))) {
		                tmp = tmp.getNext();
		            }
		            node.setNext(tmp.getNext());
		            tmp.setNext(node);
		        }
		    } while (current != this.head);

		    this.head = newHead;
		    current = this.head;
		    this.tail = this.head;
		}
	
}