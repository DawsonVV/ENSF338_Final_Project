package mylib.datastructures.linear;
import mylib.datastructures.nodes.DNode;
public class CDLL<T> extends DLL<T> {
	
			
//				- 2 constructors:
//				o Default constructor with no arguments that creates a null head object
//				o Overload constructor with a Node object argument to use as head
//				o You may combine both using default arguments if you prefer to
	public CDLL() {
	}
	
	public CDLL(DNode<T> node) {
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
	
		CDLL<Integer> list1 = new CDLL<Integer>();
		list1.print();
		//Expected
		//List length: 0
		//Sorted status: true
		//List content:
		//
		
		CDLL<Integer> list2 = new CDLL<Integer>(node1);
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
		//Sorted status: true
		//List content:
		//7 9 10
		
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
		//6 7 8 9 10
		
		list2.delete(node4);
		list2.print();
		//Expected
		//List length: 4
		//Sorted status: true
		//List content:
		//6 8 9 10
		
		list2.delete(node8);
		list2.print();
		//Expected
		//List length: 4
		//Sorted status: true
		//List content:
		//6 8 9 10
		
		list2.clear();
		list2.print();
		//Expected
		//List length: 0
		//Sorted status: true
		//List content:
		//
	}
	
}