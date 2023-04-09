package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class DLL<T> extends SLL<T>{
//		Uses a head and tail objects of the base class DNode (to be implemented as part of the
//		base classes mentioned previously)

//		- The constructors will be updated
//		o Default constructor needs to account for tail
//		o Constructor overload with one node initializes the list with head and tail pointing
//		to the same node
		public DLL() {
		}
		
		public DLL(DNode<T> node) {
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
	
//		- Insert(node,position)
//		o Inserts node object in the specified position
//		▪ Ex. Insert(node ,5) → inserts node to 5th position in list
		@Override
		public void insert(DNode<T> node, int position) {
			if (position == 1) {
				insertHead(node);
			}else if(position == size + 1 && this.head != this.tail) {
				insertTail(node);
			}else {
				DNode<T> current = this.head;
				for (int i = 0; i < position-2; i++) {
	                current = current.getNext();
	            }
				node.setNext(current.getNext());
				current.getNext().setPrev(node);
				node.setPrev(current);
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
		public void sortedInsert(DNode<T> node) {
			if (!isSorted()) {
	            sort();
	        }
	        if (head == null || Integer.parseInt(String.valueOf(node.getData())) < Integer.parseInt(String.valueOf(head.getData()))) {
	            insertHead(node);
	        } else if (Integer.parseInt(String.valueOf(node.getData())) > Integer.parseInt(String.valueOf(tail.getData()))&& this.head != this.tail) {
	            insertTail(node);
	        } else {
	            DNode<T> current = head;
	            while (current.getNext() != null && Integer.parseInt(String.valueOf(current.getNext().getData())) < Integer.parseInt(String.valueOf(node.getData()))) {
	                current = current.getNext();
	            }
	            node.setNext(current.getNext());
				current.getNext().setPrev(node);
				node.setPrev(current);
				current.setNext(node);
	            size++;
	        }
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
					DNode<T> current = this.head;
					for (int i = 0;i<this.size-1;i++) {
						if(current.getNext() == null) {
							return;
						}
						if(current.getNext() == node) {
							current.setNext(node.getNext());
							node.getNext().setPrev(current);
							super.size--;
						}
						current = current.getNext();
					}
					
				}
			}
		}
}