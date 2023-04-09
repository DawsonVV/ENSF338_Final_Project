package main.java.mylib.datastructures.trees;
import main.java.mylib.datastructures.nodes.TNode;

import java.util.LinkedList;
import java.util.Queue;

public class AVL extends BST {

  TNode root;

  public TNode getRoot() {
    return root;
  }
  
  public void setRoot(TNode root) {
    if (root == null) {
      return;
    }
    this.root = root;
    if (root.getLeft() != null || root.getRight() != null) {
      constructAVL(root);
    }
  }

  public AVL() {
    this.root = null;
  }

  public AVL(int val) {
    this.root = (new TNode(val, 0, null, null, null));
  }

  public AVL(TNode obj) {
    if (obj != null) {
      this.root = new TNode(obj.getData(), 0, null, null, null);
      constructAVL(obj);
    }
  }
  
  private void constructAVL(TNode bstRoot) {
    Queue<TNode> queue = new LinkedList<TNode>();
    queue.add(bstRoot);
  
    do {
      TNode current = queue.remove();
      TNode avlNode = new TNode(current.getData(), 0, null, null, null);
      insert(avlNode);
  
      if (current.getLeft() != null) {
        queue.add(current.getLeft());
      }
      if (current.getRight() != null) {
        queue.add(current.getRight());
      }
    } while (!queue.isEmpty());
  }
  

public void insert(TNode nodeToInsert) {
    insertHelper(nodeToInsert);
}


public void insert(int val) {
    TNode nodeToInsert = new TNode(val, 0, null, null, null);
    insertHelper(nodeToInsert);
}

// Helper method for inserting a node into the tree
private void insertHelper(TNode nodeToInsert) {
    TNode current = getRoot();
    TNode lastNode = null;
    TNode pivot = null;


    while (current != null) {
        lastNode = current;
        if (current.getBalance() != 0) {
            pivot = current;
        }
        if (current.getData() > nodeToInsert.getData()) {
            current = current.getLeft();
        } else {
            current = current.getRight();
        }
    }

    if (lastNode == null) {
        this.root = nodeToInsert; 
    } else if (lastNode.getData() > nodeToInsert.getData()) {
        lastNode.setLeft(nodeToInsert);
    } else {
        lastNode.setRight(nodeToInsert);
    }

    nodeToInsert.setParent(lastNode);
    reOrder(nodeToInsert, pivot);
}

public void reOrder(TNode nodeToInsert, TNode pivot) {
    if (pivot == null) {
        // Adding to a subtree with all 0 balances
        reOrderSubtree(nodeToInsert, null, null);
    } else if (pivot.getBalance() == +1 && nodeToInsert.getData() < pivot.getData()
            || pivot.getBalance() == -1 && nodeToInsert.getData() > pivot.getData()) {
        // Adding to the shorter subtree
        reOrderSubtree(nodeToInsert, pivot, null);
    } else {
        // Adding to the longer subtree
        TNode ancestor = pivot.getParent();
        TNode son = pivot.getBalance() == -1 ? pivot.getLeft() : pivot.getRight();
        if (nodeToInsert.getData() < son.getData()) {
            // Outside case 3a
            rotateRight(pivot, son, ancestor, nodeToInsert);
        } else {
            // Case 3b: adding a node to the inside subtree
            TNode grandson = son.getBalance() == +1 ? son.getRight() : son.getLeft();
            rotateLeft(grandson, son);
            rotateRight(pivot, grandson, ancestor, nodeToInsert);
        }
    }
}

private void reOrderSubtree(TNode node, TNode pivot, TNode stop) {
    TNode current = node;
    while (current != stop) {
        if (current.getData() < pivot.getData()) {
            pivot.incrementBalance();
            pivot = pivot.getLeft();
        } else {
            pivot.decrementBalance();
            pivot = pivot.getRight();
        }
        current = current.getParent();
    }
}

private void rotateRight(TNode pivot, TNode son, TNode ancestor, TNode nodeToInsert) {
    if (ancestor == null) {
        son.setParent(null);
        root = son;
    } else if (nodeToInsert.getData() < ancestor.getData()) {
        ancestor.setLeft(son);
        son.setParent(ancestor);
    } else {
        ancestor.setRight(son);
        son.setParent(ancestor);
    }
    pivot.setLeft(son.getRight());
    if (pivot.getLeft() != null) {
        pivot.getLeft().setParent(pivot);
    }
    son.setRight(pivot);
    pivot.setParent(son);
    pivot.setBalance(pivot.getBalance() == -1 ? 0 : -1);
    son.setBalance(0);
    reOrderSubtree(nodeToInsert, son, pivot);
}

private void rotateLeft(TNode pivot, TNode son) {
    TNode grandson = son.getRight();
    son.setRight(grandson.getLeft());
    if (son.getRight() != null) {
        son.getRight().setParent(son);
    }
    grandson.setLeft(son);
    son.setParent(grandson);
    son.setBalance(son.getBalance() == +1 ? 0 : +1);
    grandson.setBalance(0);
}



  
}