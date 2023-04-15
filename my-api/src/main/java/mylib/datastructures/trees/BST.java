package mylib.datastructures.trees;

import mylib.datastructures.nodes.TNode;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

    TNode root;

    public BST() {
        root = null;
    }

    public BST(int val) {
        root = new TNode(val, 0, null, null, null);
    }

    public BST(TNode obj) {
        this.root = obj;
    }

    // Setter and getter for root
    public TNode getRoot() {
        return this.root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    public void insert(TNode node) {
        setRoot(insertHelper(getRoot(), node));
    }

    private TNode insertHelper(TNode current, TNode nodeToInsert) {
        if (current == null) {
            return nodeToInsert;
        }
        if (current.getData() > nodeToInsert.getData()) {
            current.setLeft(insertHelper(current.getLeft(), nodeToInsert));
            current.getLeft().setParent(current); // set the parent of the newly inserted node
        } else {
            current.setRight(insertHelper(current.getRight(), nodeToInsert));
            current.getRight().setParent(current); // set the parent of the newly inserted node
        }
        return current;
    }

    public void insert(int val) {
        TNode nodeToInsert = new TNode(val, 0, null, null, null);
        setRoot(insertHelper(getRoot(), nodeToInsert));
    }

    public void delete(int val) {
        setRoot(delete(getRoot(), val));
    }

    private TNode delete(TNode node, int val) {
        if (node == null) {
            System.out.println("Object to delete not found, tree is empty.");
            return null;
        }
        if (val < node.getData()) {
            node.setLeft(delete(node.getLeft(), val));
        } else if (val > node.getData()) {
            node.setRight(delete(node.getRight(), val));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                TNode successor = findSuccessor(node.getRight());
                node.setData(successor.getData());
                node.setRight(delete(node.getRight(), successor.getData()));
            }
        }
        return node;
    }

    private TNode findSuccessor(TNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void printInOrder(TNode current) {
        if (current != null) {
            printInOrder(current.getLeft());
            current.print();
            printInOrder(current.getRight());
        }
    }

    // prints the content of the tree in Breadth-First order
    public void printBF() {
        if (getRoot() == null) {
            return;
        } else {
            Queue<TNode> queue = new LinkedList<TNode>();
            queue.add(getRoot());

            while (!queue.isEmpty()) {
                TNode node = queue.remove();
                System.out.println(node.toString());
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
        }
    }

    public TNode search(int val) {
        TNode current = getRoot();
        while (current != null && current.getData() != val) {
            current = (current.getData() > val) ? current.getLeft() : current.getRight();
        }
        return current;
    }

}