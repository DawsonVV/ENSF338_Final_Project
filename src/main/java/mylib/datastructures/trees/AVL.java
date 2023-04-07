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

    public void Delete(int val) {
        root = deleteNode(root, val);
    }
    
    private TNode deleteNode(TNode root, int val) {
        if (root == null) {
            System.out.println("Value not found in the tree.");
            return root;
        }
        
        // If the value to be deleted is smaller than the root's value,
        // then it lies in the left subtree.
        if (val < root.getData()) {
            root.setLeft(deleteNode(root.getLeft(), val));
        } 
        // If the value to be deleted is greater than the root's value,
        // then it lies in the right subtree.
        else if (val > root.getData()) {
            root.setRight(deleteNode(root.getRight(), val));
        } 
        // If the value to be deleted is equal to the root's value,
        // then this is the node to be deleted.
        else {
            // Node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                TNode temp = null;
                if (temp == root.getLeft()) {
                    temp = root.getRight();
                } else {
                    temp = root.getLeft();
                }
                
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } 
                // One child case
                else {
                    root = temp; // Copy the contents of the non-empty child
                }
                
                temp = null;
            } 
            // Node with two children: Get the inorder successor (smallest
            // in the right subtree)
            else {
                TNode temp = minValueNode(root.getRight());
                
                // Copy the inorder successor's data to this node
                root.setData(temp.getData());
                
                // Delete the inorder successor
                root.setRight(deleteNode(root.getRight(), temp.getData()));
            }
        }
        
        // If the tree had only one node then return
        if (root == null) {
            return root;
        }
    
        // Update the balance factor of the root node
        root.setBalance(height(root.getLeft()) - height(root.getRight()));
        
        // Check if this node is unbalanced and if so, balance it
        if (root.getBalance() > 1 && root.getLeft().getBalance() >= 0) {
            return rightRotate(root);
        }
        if (root.getBalance() > 1 && root.getLeft().getBalance() < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        if (root.getBalance() < -1 && root.getRight().getBalance() <= 0) {
            return leftRotate(root);
        }
        if (root.getBalance() < -1 && root.getRight().getBalance() > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
    
        return root;
    }
    
    private TNode minValueNode(TNode node) {
        TNode current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
    
    private int height(TNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
    
}
