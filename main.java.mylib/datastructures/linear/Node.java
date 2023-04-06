package main.java.mylib.datastructures.linear;

public class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public T getData() {
    	return this.data;
    }
    
    public Node<T> getNext() {
    	return this.next;
    }
    
    public Node<T> getPrev() {
    	return this.prev;
    }
    
    public void setNext(Node<T> node) {
    	this.next = node;
    }
    
    public void setPrev(Node<T> node) {
    	this.prev = node;
    }
}