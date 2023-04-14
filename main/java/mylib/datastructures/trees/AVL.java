package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;

public class AVL extends BST {
    private TNode root;

    public void setRoot(TNode root) {
        this.root = root;
        balanceTree();
    }

    public TNode getRoot() {
        return root;
    }

    public AVL() {
        this.root = null;
    }

    public AVL(int val) {
        this.root = new TNode(val, 0, null, null, null);
    }

    public AVL(TNode obj) {
        this.root = obj;
        balanceTree();
    }

    @Override
    // Overriding the insert method of BST
    public void insert(TNode node) {
        super.insert(node);
        balanceTree();
    }

    @Override
    // Overriding the insert method of BST
    public void insert(int val) {
        super.insert(val);
        balanceTree();
    }

    @Override
    // Overriding the delete method of BST
    public void delete(int val) {
        TNode nodeToDelete = search(val);
        if (nodeToDelete == null) {
            System.out.println("Node not found: " + val);
        } else {
            super.delete(nodeToDelete.getData());
            balanceTree();
        }
    }

    // Balancing the tree
    private void balanceTree() {
        if (root != null) {
            root = balanceTree(root);
        }
    }

    private TNode balanceTree(TNode node) {
        if (node == null) {
            return null;
        }
        int balanceFactor = calculateBalanceFactor(node.getLeft()) - calculateBalanceFactor(node.getRight());
        boolean isLeftHeavy = balanceFactor > 1;
        boolean isRightHeavy = balanceFactor < -1;
        if (isLeftHeavy ? calculateBalanceFactor(node.getLeft().getLeft()) >= calculateBalanceFactor(node.getLeft().getRight()) : false) {
            node = rotateRight(node);
        } else if (isRightHeavy ? calculateBalanceFactor(node.getRight().getRight()) >= calculateBalanceFactor(node.getRight().getLeft()) : false) {
            node = rotateLeft(node);
        } else {
            newHeight(node);
        }
        return node;
    }
    
    private int calculateBalanceFactor(TNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.getBalance();
        }
    }

    private int getBalance(TNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.getBalance();
        }
    }
    

    // Helper methods
    private TNode rotateLeft(TNode node) {
        TNode newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        newHeight(node);
        newHeight(newRoot);
        return newRoot;
    }

    private TNode rotateRight(TNode node) {
        TNode newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        newHeight(node);
        newHeight(newRoot);
        return newRoot;
    }

    private void newHeight(TNode node) {
        if (node != null) {
            int leftHeight = getBalance(node.getLeft());
            int rightHeight = getBalance(node.getRight());
            node.setBalance(Math.max(leftHeight, rightHeight) + 1);
        }
    }
}
