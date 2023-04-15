package mylib.datastructures.linear;

import mylib.datastructures.nodes.DNode;

public class SLL<T> {
//	1. singlyLL:
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
	public SLL() {
	}
	
	public SLL(DNode<T> node) {
		this.head = node;
		DNode<T> current = node;
		this.size = 1;
		while(current.getNext() != null) {
			current = current.getNext();
			this.size++;
		}
		this.tail = current;
	}
	
	public int getSize() {
		return this.size;
	}

//		- InsertHead(node)
//		o Inserts node object at head of the list
	public void insertHead(DNode<T> node) {
		DNode<T> current = node;
		this.size ++;
		while(current.getNext() != null) {
			current = current.getNext();
			this.size ++;
		}
		if(this.tail == null) {
			this.tail = current;
		}
		current.setNext(this.head);
		this.head = node;
	}

//		- InsertTail(node)
//		o Inserts node object at the tail of the list
	public void insertTail(DNode<T> node) {
		
		DNode<T> current = node;
		this.size ++;
		while(current.getNext() != null) {
			current = current.getNext();
			this.size ++;
		}
		if(this.head == null) {
			this.head = node;
			this.tail = node;
		}else {
			this.tail.setNext(node);
			this.tail = current;
		}
	}

//		- Insert(node,position)
//		o Inserts node object in the specified position
//		▪ Ex. Insert(node ,5) → inserts node to 5th position in list
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
            current.setNext(node);
            size++;
        }
	}

//		- Search(node)
//		o Looks up node in the list
//		▪ If found it returns the object
//		▪ Otherwise returns null
	public DNode<T> search(DNode<T> node) {
		if (node == this.head) {
			return this.head;
		}else if (node == this.tail) {
			return this.tail;
		}else {
			DNode<T> current = this.head;
			for(int i = 0; i < this.size;i++) {
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
			if(this.head.getNext() == null) {
				this.head = null;
				this.tail = null;
			}else {
				this.head = this.head.getNext();
				if(this.head == null) {
					this.tail = null;
				}
			}
			this.size --;
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
				DNode<T> current = this.head;
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
	public void delete(DNode<T> node) {
		if(this.head != null) {
			if(this.head == node) {
				deleteHead();
			}else if(this.tail == node) {
				deleteTail();
			}else {
				DNode<T> current = this.head;
				for (int i = 0;i<this.size-1;i++) {
					if(current.getNext() == null) {
						return;
					}
					if(current.getNext() == node) {
						current.setNext(node.getNext());
						this.size--;
					}
					current = current.getNext();
					
				}
				
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
		if (this.size <= 1) {
            return;
        }
		DNode<T> newHead = null;
        while (this.head != null) {
        	DNode<T> node = this.head;
            this.head = node.getNext();
            if (newHead == null || Integer.parseInt(String.valueOf(node.getData())) < Integer.parseInt(String.valueOf(newHead.getData()))) {
                node.setNext(newHead);
                newHead = node;
            } else {
            	DNode<T> current = newHead;
                while (current.getNext() != null && Integer.parseInt(String.valueOf(node.getData())) > Integer.parseInt(String.valueOf(current.getNext().getData()))) {
                    current = current.getNext();
                }
                node.setNext(current.getNext());
                current.setNext(node);
            }
        }
        this.head = newHead;
        DNode<T> current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        this.tail = current;
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
		System.out.println("List length: " + this.size);
        System.out.println("Sorted status: " + isSorted());
        System.out.println("List content:");
        DNode<T> current = this.head;
        for(int i = 0; i<this.size;i++) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
        System.out.println();
	}
	
	protected boolean isSorted() {
        if (this.size <= 1) {
            return true;
        }
        DNode<T> current = this.head;
        for (int i = 0;i<this.size-1;i++) {
            if (Integer.parseInt(String.valueOf(current.getData())) > Integer.parseInt(String.valueOf(current.getNext().getData()))) {
                return false;
            }
            current = current.getNext();
        }
        return true;
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
		
		SLL<Integer> list1 = new SLL<Integer>();
		list1.print();
		//Expected
		//List length: 0
		//Sorted status: true
		//List content:
		//
		
		SLL<Integer> list2 = new SLL<Integer>(node1);
		list2.print();
		//Expected
		//List length: 1
		//Sorted status: true
		//List content: 
		//10
		
		list2.insertHead(node2);
		list2.print();
		//Expected
		//List length: 2
		//Sorted status: true
		//List content:
		//9 10
		
		list2.insertTail(node4);
		list2.print();
		//Expected
		//List length: 3
		//Sorted status: false
		//List content:
		//9 10 7
		
		list2.sortedInsert(node3);
		list2.print();
		//Expected
		//List length: 4
		//Sorted status: True
		//List content:
		//7 8 9 10
		
		
		list2.insert(node5,3);
		list2.print();
		//Expected
		//List length: 5
		//Sorted status: false
		//List content:
		//7 8 6 9 10
		
		list2.insert(node6,1);
		list2.print();
		//Expected
		//List length: 6 
		//Sorted status: false
		//List content:
		//5 7 8 6 9 10
		
		list2.insert(node7,list2.getSize()+1);
		list2.print();
		//Expected
		//List length: 7
		//Sorted status: false
		//List content:
		//5 7 8 6 9 10 4
		
		node9 = list2.search(node4);
		if(node9 != null) {
			System.out.println("Found");
		}else {
			System.out.println("Not Found");
		}
		System.out.println();
		//Expected
		//Found
		
		node10 = list2.search(node8);
		if(node10 != null) {
			System.out.println("Found");
		}else {
			System.out.println("Not Found");
		}
		System.out.println();
		//Expected
		//Not Found

		list2.sort();
		list2.print();
		//Expected 
		//List length: 7
		//Sorted status: true
		//List content:
		//4 5 6 7 8 9 10
		
		list2.deleteHead();
		list2.print();
		//Expected
		//List length: 6
		//Sorted status: true
		//List content:
		//5 6 7 8 9 10
		
		list2.deleteTail();
		list2.print();
		//Expected
		//List length: 5
		//Sorted status: 
		//List content:
		//5 6 7 8 9
		
		list2.delete(node4);
		list2.print();
		//Expected
		//List length: 4
		//Sorted status: true
		//List content:
		//5 6 8 9
		
		list2.delete(node8);
		list2.print();
		//Expected
		//List length: 4
		//Sorted status: true
		//List content:
		//5 6 8 9
		
		list2.clear();
		list2.print();
		//Expected
		//List length: 0
		//Sorted status: true
		//List content:
		//
	}
}