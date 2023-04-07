package main.java.mylib.datastructures.trees;
import main.java.mylib.datastructures.nodes.TNode;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private TNode root;

    // Default constructor
    public BST() {
        this.root = null;
    }

    // Overload constructor with integer value
    public BST(int val) {
        this.root = new TNode(val, 0, null, null, null);
    }

    // Overload constructor with TNode
    public BST(TNode obj) {
        this.root = obj;
    }

    // Getter and setter for root
    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    // Insert a new node with value val
    public void insert(int val) {
        TNode newNode = new TNode(val, 0, null, null, null);
        if (root == null) {
            root = newNode;
        } else {
            TNode curr = root;
            TNode parent = null;
            while (curr != null) {
                parent = curr;
                if (val < curr.getData()) {
                    curr = curr.getLeft();
                } else {
                    curr = curr.getRight();
                }
            }
            newNode.setParent(parent);
            if (val < parent.getData()) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
        }
    }

    // Insert a node into the tree
    public void insert(TNode node) {
        if (root == null) {
            root = node;
        } else {
            TNode curr = root;
            TNode parent = null;
            while (curr != null) {
                parent = curr;
                if (node.getData() < curr.getData()) {
                    curr = curr.getLeft();
                } else {
                    curr = curr.getRight();
                }
            }
            node.setParent(parent);
            if (node.getData() < parent.getData()) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
        }
    }

    // Delete a node with value val
    public void delete(int val) {
        TNode node = search(val);
        if (node == null) {
            System.out.println("Value " + val + " not found in the tree");
        } else {
            if (node.getLeft() == null) {
                transplant(node, node.getRight());
            } else if (node.getRight() == null) {
                transplant(node, node.getLeft());
            } else {
                TNode successor = minimum(node.getRight());
                if (successor.getParent() != node) {
                    transplant(successor, successor.getRight());
                    successor.setRight(node.getRight());
                    successor.getRight().setParent(successor);
                }
                transplant(node, successor);
                successor.setLeft(node.getLeft());
                successor.getLeft().setParent(successor);
            }
        }
    }

    // Helper method for delete
    private void transplant(TNode u, TNode v) {
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != null) {
            v.setParent(u.getParent());
        }
    }

    public TNode search(int val) {
        TNode curr = root;
        while (curr != null && curr.getData() != val) {
            if (val < curr.getData()) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        return curr;
    }
    
    public void printInOrder() {
        inOrderTraversal(root);
        System.out.println();
    }
    
    private void inOrderTraversal(TNode node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderTraversal(node.getRight());
        }
    }
    
    public void printBF() {
        if (root == null) {
            return;
        }
        Queue<TNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TNode node = queue.poll();
                System.out.print(node.getData() + " ");
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
            System.out.println();
        }
    }

    private TNode minimum(TNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
    
}
           
