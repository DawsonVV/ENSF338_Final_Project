package mylib.datastructures.nodes;


public class TNode {
    private int data;
    private TNode left;
    private TNode right;
    private TNode parent;
    private int balance;

    // Default constructor
    public TNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balance = 0;
    }

    // Overloaded constructor
    public TNode(int data, int balance, TNode parent, TNode left, TNode right) {
        this.data = data;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    // Getters
    public int getData() {
        return data;
    }

    public TNode getLeft() {
        return left;
    }

    public TNode getRight() {
        return right;
    }

    public TNode getParent() {
        return parent;
    }

    public int getBalance() {
        return balance;
    }

    // Setters
    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // Print node information
    public void print() {
        System.out.println("Data: " + getData() + ", " + "Balance: " + getBalance());
    }

    // Return data member as a string
    public String toString() {
        return Integer.toString(data);
    }

    public void decrementBalance() {
        this.balance--;
    }

    public void incrementBalance() {
        this.balance++;
    }
}
