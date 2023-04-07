package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;

public class AVL extends BST {
    private TNode root;
    
    public AVL() {
        root = null;
    }

    public AVL(int val) {
        this.root = new TNode(val);
    }

    public AVL(TNode obj) {
        root = obj;
        root.setParent(null);
        balanceTree(root);
    }
    
    private void balanceTree(TNode node) {
        if (node == null) {
            return;
        }
    
        int balance = calculateBalance(node);
        node.setBalance(balance);
    
        if (balance > 1) {
            if (calculateBalance(node.getLeft()) > 0) {
                node = rightRotate(node);
            } else {
                node.setLeft(leftRotate(node.getLeft()));
                node = rightRotate(node);
            }
        } else if (balance < -1) {
            if (calculateBalance(node.getRight()) < 0) {
                node = leftRotate(node);
            } else {
                node.setRight(rightRotate(node.getRight()));
                node = leftRotate(node);
            }
        }
    
        if (node.getParent() != null) {
            balanceTree(node.getParent());
        } else {
            root = node;
        }
    }
    
    private int calculateBalance(TNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }
    
    private int getHeight(TNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
    
    private TNode rightRotate(TNode node) {
        TNode newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        if (newRoot.getRight() != null) {
            newRoot.getRight().setParent(node);
        }
        newRoot.setRight(node);
        newRoot.setParent(node.getParent());
        node.setParent(newRoot);
        node.setBalance(calculateBalance(node));
        newRoot.setBalance(calculateBalance(newRoot));
        return newRoot;
    }
    
    private TNode leftRotate(TNode node) {
        TNode newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        if (newRoot.getLeft() != null) {
            newRoot.getLeft().setParent(node);
        }
        newRoot.setLeft(node);
        newRoot.setParent(node.getParent());
        node.setParent(newRoot);
        node.setBalance(calculateBalance(node));
        newRoot.setBalance(calculateBalance(newRoot));
        return newRoot;
    }
    
    private TNode createAVLTree(TNode node) {
        if (node == null) {
            return null;
        }
        TNode newRoot = new TNode(node.getData(), node.getBalance(), null, null, null);
        newRoot.setLeft(createAVLTree(node.getLeft()));
        newRoot.setRight(createAVLTree(node.getRight()));
        balanceTree(newRoot);
        return newRoot;
    }
    
    public void setRoot(TNode node) {
        root = new TNode(node.getData(), node.getBalance(), null, null, null);
        if (node.getLeft() != null || node.getRight() != null) {
            root.setLeft(createAVLTree(node.getLeft()));
            root.setRight(createAVLTree(node.getRight()));
        }
        balanceTree(root);
    }

    public void insert(int val) {
        TNode newNode = new TNode(val);
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
        balanceTree(newNode);
    }

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
        balanceTree(node);
    }

    

    

}
