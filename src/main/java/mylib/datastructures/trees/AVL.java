package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;
import java.util.*;

public class AVL extends BST {
    private TNode root;

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        if (root == null) {
            return;
        }

        this.root = root;

        if (root.getLeft() != null || root.getRight() != null) {
            avlCreator(root);
        }
    }

    public AVL() {
        root = null;
    }

    public AVL(int val) {
        setRoot(new TNode(val, 0, null, null, null));
    }

    public AVL(TNode obj) {

        if (obj == null) {
            return;
        }

        this.root = new TNode(obj.getData(), 0, null, null, null);

        if (hasChildren(obj)) {
            avlCreator(obj);
        }
    }

    private boolean hasChildren(TNode node) {
        return node.getLeft() != null || node.getRight() != null;
    }

    private void avlCreator(TNode bstRoot) {
        Queue<TNode> queue = new LinkedList<>();
        queue.add(bstRoot);

        for (TNode child : new TNode[] { bstRoot.getLeft(), bstRoot.getRight() }) {
            if (child != null) {
                queue.add(child);
            }
        }

        queue.remove();

        while (!queue.isEmpty()) {
            TNode current = queue.remove();
            TNode avlNode = new TNode(current.getData(), 0, null, null, null);
            insert(avlNode);

            for (TNode child : new TNode[] { current.getLeft(), current.getRight() }) {
                if (child != null) {
                    queue.add(child);
                }
            }
        }
    }

    public void insert(TNode nodeToInsert) {
        root = insertNode(root, nodeToInsert, null, null);
    }
    
    private TNode insertNode(TNode current, TNode nodeToInsert, TNode parent, TNode pivot) {
        if (current == null) {
            // If current node is null, insert the new node here
            nodeToInsert.setParent(parent);
            if (getRoot() == null) {
                // If the tree was empty, the new node becomes the root
                this.root = nodeToInsert;
            } else {
                // Otherwise, set the new node as left or right child of parent
                if (parent.getData() > nodeToInsert.getData()) {
                    parent.setLeft(nodeToInsert);
                } else {
                    parent.setRight(nodeToInsert);
                }
            }
            // Perform rebalancing if necessary
            rebalance(nodeToInsert, pivot);
            return nodeToInsert;
        }
        if (current.getData() > nodeToInsert.getData()) {
            // If node to insert is lower than current, insert it in the left subtree
            current.setLeft(insertNode(current.getLeft(), nodeToInsert, current, current.getBalance() != 0 ? current : pivot));
        } else {
            // If node to insert is higher than current, insert it in the right subtree
            current.setRight(insertNode(current.getRight(), nodeToInsert, current, current.getBalance() != 0 ? current : pivot));
        }
        // Return the current node with updated left or right child
        return current;
    }

    public void insert(int val) {
        TNode newNode = new TNode(val, 0, null, null, null);
        if (getRoot() == null) {
            setRoot(newNode);
            return;
        }
        TNode parent = null;
        TNode current = getRoot();
        while (current != null) {
            parent = current;
            if (val < current.getData()) {
                current = current.getLeft();
            } else if (val > current.getData()) {
                current = current.getRight();
            } else {
                return; // Duplicate value not allowed
            }
        }
        if (val < parent.getData()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        newNode.setParent(parent);
        rebalance(getRoot(), newNode);
    }
    

    public void rebalance(TNode nodeToInsert, TNode pivot) {
        if (pivot == null) {
            updateBalances(nodeToInsert, null);
        } else if (nodeToInsert.getData() < pivot.getData()) {
            if (pivot.getBalance() == -1) {
                rotateRight(pivot);
                updateBalances(nodeToInsert, pivot.getParent());
            } else if (pivot.getBalance() == 1) {
                rotateLeft(pivot.getLeft());
                rotateRight(pivot);
                updateBalances(nodeToInsert, pivot.getParent());
            } else {
                pivot.decrementBalance();
                rebalance(nodeToInsert, pivot.getParent());
            }
        } else {
            if (pivot.getBalance() == 1) {
                rotateLeft(pivot);
                updateBalances(nodeToInsert, pivot.getParent());
            } else if (pivot.getBalance() == -1) {
                rotateRight(pivot.getRight());
                rotateLeft(pivot);
                updateBalances(nodeToInsert, pivot.getParent());
            } else {
                pivot.incrementBalance();
                rebalance(nodeToInsert, pivot.getParent());
            }
        }
    }
    
    private void rotateRight(TNode node) {
        TNode pivot = node.getLeft();
        node.setLeft(pivot.getRight());
        if (pivot.getRight() != null) {
            pivot.getRight().setParent(node);
        }
        pivot.setParent(node.getParent());
        if (node.getParent() == null) {
            root = pivot;
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(pivot);
        } else {
            node.getParent().setRight(pivot);
        }
        pivot.setRight(node);
        node.setParent(pivot);
        pivot.setBalance(0);
        node.setBalance(0);
    }
    
    private void rotateLeft(TNode node) {
        TNode pivot = node.getRight();
        node.setRight(pivot.getLeft());
        if (pivot.getLeft() != null) {
            pivot.getLeft().setParent(node);
        }
        pivot.setParent(node.getParent());
        if (node.getParent() == null) {
            root = pivot;
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(pivot);
        } else {
            node.getParent().setRight(pivot);
        }
        pivot.setLeft(node);
        node.setParent(pivot);
        pivot.setBalance(0);
        node.setBalance(0);
    }
    
    private void updateBalances(TNode node, TNode stop) {
        if (node == null || node == stop) {
            return;
        }
        if (node.getParent() == stop) {
            return;
        }
        if (node == node.getParent().getLeft()) {
            node.getParent().decrementBalance();
        } else {
            node.getParent().incrementBalance();
        }
        updateBalances(node.getParent(), stop);
    }
    
    
    

}
