package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class DoublyLinkedList<T> extends SinglyLinkedList<T>{
//		Uses a head and tail objects of the base class DNode (to be implemented as part of the
//		base classes mentioned previously)

//		- The constructors will be updated
//		o Default constructor needs to account for tail
//		o Constructor overload with one node initializes the list with head and tail pointing
//		to the same node
		public DoublyLinkedList() {
		}
		
		public DoublyLinkedList(DNode<T> node) {
			super.head = node;
			super.size = 1;
			super.tail = node;
		}
	
//		- The remaining functionalities from singlyLL will practically be modified to account for the
//		previous reference in the DNode
		
//		- InsertHead(node)
//		o Inserts node object at head of the list
		@Override
		public void insertHead(DNode<T> node) {
			DNode<T> current = node;
			super.size ++;
			while(current.getNext() != null) {
				current = current.getNext();
				super.size ++;
			}
			super.head.setPrev(current);
			current.setNext(super.head);
			current = node;
			while(current.getPrev() != null) {
				current = current.getPrev();
				super.size ++;
			}
			super.head = current;
		}
	
//		- InsertTail(node)
//		o Inserts node object at the tail of the list
		@Override
		public void insertTail(DNode<T> node) {
			super.tail.setNext(node);
			DNode<T> current = node;
			super.size ++;
			while(current.getPrev() != null) {
				current = current.getPrev();
				super.size ++;
			}
			current.setPrev(super.tail);
			if (super.head == null) {
				super.head = current;
			}
			current = node;
			while(current.getNext() != null) {
				current = current.getNext();
				super.size ++;
			}
			super.tail = current;
			
		}
	
//		- DeleteHead()
//		o Delete head node
		@Override
		public void deleteHead() {
			if (super.head != null) {
				if(super.head.getNext() == null) {
					super.head = null;
					super.tail = null;
				}else {
					super.head = super.head.getNext();
					super.head.setPrev(null);
				}
				super.size --;
			}
		}
	
//		- DeleteTail()
//		o Delete tail node
		@Override
		public void deleteTail() {
			if(super.tail != null) {
				if(super.head.getNext() == null) {
					super.head = null;
					super.tail = null;
				}else {
					super.tail = super.tail.getPrev();
					super.tail.setNext(null);
				}
				super.size--;
			}
		}
	
//		- Delete(node)
//		o Deletes the node if found in the list
		@Override
		public void delete(DNode<T> node) {
			if(super.head != null) {
				if(super.head == node) {
					deleteHead();
				}else if(super.tail == node) {
					deleteTail();
				}else {
					DNode<T> current = super.head.getNext();
					while(current.getNext() != node) {
						if(current.getNext() == null) {
							return;
						}
						current = current.getNext();
					}
					current.setNext(node.getNext());
					node.getNext().setPrev(current);
					super.size--;
				}
			}
		}
}