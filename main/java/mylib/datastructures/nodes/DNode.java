package main.java.mylib.datastructures.nodes;

public class DNode<T> {
    public T data;
    public DNode<T> next;
    public DNode<T> prev;

    public DNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public T getData() {
    	return this.data;
    }
    
    public DNode<T> getNext() {
    	return this.next;
    }
    
    public DNode<T> getPrev() {
    	return this.prev;
    }
    
    public void setNext(DNode<T> node) {
    	this.next = node;
    }
    
    public void setPrev(DNode<T> node) {
    	this.prev = node;
    }
}