package main.java.mylib.datastructures.linear;
import main.java.mylib.datastructures.nodes.DNode;

public class CircularLinkedList<T> extends SinglyLinkedList<T>{
		
//			- 2 constructors:
//			o Default constructor with no arguments that creates a null head object
//			o Overload constructor with a Node object argument to use as head
//			o You may combine both using default arguments if you prefer to
			public CircularLinkedList() {
			}
			
			public CircularLinkedList(DNode<T> node) {
				node.setNext(node);
				super.head = node;
				super.size = 1;
				super.tail = node;
			}
		
//			- InsertHead(node)
//			o Inserts node object at head of the list
			public void insertHead(DNode<T> node) {
				DNode<T> current = node;
				super.size ++;
				while(current.getNext() != null) {
					current = current.getNext();
					super.size ++;
				}
				current.setNext(super.head);
				while(current.getNext() != super.head) {
					current = current.getNext();
					super.size ++;
				}
				current.setNext(node);
				super.head = node;
				super.tail = node;
			}
		
//			- InsertTail(node)
//			o Inserts node object at the tail of the list
			public void insertTail(DNode<T> node) {
				insertHead(node);
				
			}
		
//			- Insert(node,position)
//			o Inserts node object in the specified position
//			▪ Ex. Insert(node ,5) → inserts node to 5th position in list
			public void insert(DNode<T> node, int position) {
				if (position == 1) {
					insertHead(node);
				}else if(position == size + 1) {
					insertTail(node);
				}else {
					DNode<T> current = node;
					for (int i = 0; i < position - 1; i++) {
		                current = current.getNext();
		            }
					node.setNext(current.getNext());
					current.setNext(node);
					size++;
				}
			}
		
//			- SortedInsert(node)
//			o Inserts node object in its proper position in a sorted list
//			o Must check for list sort validity
//			▪ If list is found to be out of order, it must call the sort function first before
//			inserting
//			▪ Note that you should only execute sort if the list is found to be out of order
//			to avoid slowing down the insertion by executing sorting every time you
//			insert
//			▪ Might need to implement a helper function isSorted(), or find a creative
//			way to know if the list is sorted
			public void sortedInsert(DNode<T> node) {
				if (super.head == null) {
		            super.head = node;
		            super.tail = node;
		            super.size++;
		        } else if (Integer.parseInt((String)node.getData()) <= Integer.parseInt((String)super.head.getData())) {
		            node.setNext(super.head);
		            super.head = node;
		            super.size++;
		        } else if (Integer.parseInt((String)node.getData()) >= Integer.parseInt((String)super.tail.getData())) {
		            super.tail.setNext(node);
		            super.tail = node;
		            super.size++;
		        } else {
		        	DNode<T> current = super.head;
		            while (Integer.parseInt((String)current.getNext().getData()) < Integer.parseInt((String)node.getData())) {
		                current = current.getNext();
		            }
		            node.setNext(current.getNext());
		            current.setNext(node);
		            super.size++;
		        }
			}
		
//			- Search(node)
//			o Looks up node in the list
//			▪ If found it returns the object
//			▪ Otherwise returns null
			public DNode<T> search(DNode<T> node) {
				if (node == super.head) {
					return super.head;
				}else if (node == super.tail) {
					return super.tail;
				}else {
					DNode<T> current = super.head;
					while(current != null) {
						if(node == current) {
							return current;
						}
						current = current.getNext();
					}
					return null;
				}
			}
		
//			- DeleteHead()
//			o Delete head node
			public void deleteHead() {
				if (super.head != null) {
					if(super.head.getNext() == super.head) {
						super.head = null;
						super.tail = null;
					}else {
						DNode<T> current = super.head;
						while(current.getNext() != super.head) {
							current = current .getNext();
						}
						super.head = super.head.getNext();
						current.setNext(super.head);
						super.tail = super.head;
						if(super.head == null) {
							super.tail = null;
						}
					}
					super.size --;
				}
			}
		
//			- DeleteTail()
//			o Delete tail node
			public void deleteTail() {
				deleteHead();
			}
		
//			- Delete(node)
//			o Deletes the node if found in the list
			public void delete(DNode<T> node) {
				if(super.head != null) {
					if(super.head == node) {
						deleteHead();
					}else if(super.tail == node) {
						deleteTail();
					}else {
						DNode<T> current = super.head.getNext();
						while(current.getNext() != node) {
							if(current.getNext() == super.head) {
								return;
							}
							current = current.getNext();
						}
						current.setNext(node.getNext());
						super.size--;
					}
				}
			}
		
//			- Sort()
//			o Applies insertion sort to the list
//			o The insertion part will start from the head unlike the usual insertion sort algorithm
//			▪ Instead of tracking back the list
//			o Note that the sort method and SortedInsert can use each other to efficiently
//			reduce code redundancy (not mandatory)
			public void sort() {
				if (super.size <= 1) {
		            return;
		        }
				DNode<T> newHead = null;
		        while (super.head != null) {
		        	DNode<T> node = super.head;
		            super.head = node.getNext();
		            if (newHead == null || Integer.parseInt((String)node.getData()) < Integer.parseInt((String)newHead.getData())) {
		                node.setNext(newHead);
		                newHead = node;
		            } else {
		            	DNode<T> current = newHead;
		                while (current.getNext() != null && Integer.parseInt((String)node.getData()) > Integer.parseInt((String)current.getNext().getData())) {
		                    current = current.getNext();
		                }
		                node.setNext(current.getNext());
		                current.setNext(node);
		            }
		        }
		        super.head = newHead;
		        DNode<T> current = super.head;
		        while (current.getNext() != null) {
		            current = current.getNext();
		        }
		        super.tail = current;
			}
		
//			- Clear()
//			o Deletes the whole list
			public void clear() {
				super.head = null;
				super.tail = null;
				super.size = 0;
			}
		
//			- Print()
//			o Prints the list information on the screen, this includes
//			▪ List length
//			▪ Sorted status
//			▪ List content
//			▪ Make sure to show information with relevant print statements to be
//			readable by the user
			public void print() {
				System.out.println("List length: " + super.size);
		        System.out.println("Sorted status: " + super.isSorted());
		        System.out.println("List content:");
		        DNode<T> current = super.head;
		        while (current != null) {
		            System.out.print(current.getData() + " ");
		            current = current.getNext();
		        }
		        System.out.println();
			}
			
			protected boolean isSorted() {
		        if (super.size <= 1) {
		            return true;
		        }
		        DNode<T> current = super.head;
		        while (current.getNext() != null) {
		            if (Integer.parseInt((String)current.getData()) > Integer.parseInt((String)current.getNext().getData())) {
		                return false;
		            }
		            current = current.getNext();
		        }
		        return true;
		    }
}