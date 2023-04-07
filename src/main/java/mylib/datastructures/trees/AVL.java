package main.java.mylib.datastructures.trees;

import main.java.mylib.datastructures.nodes.TNode;

public class AVL extends BST {
    
    // Member variable
    TNode root; // references the root of the tree
    
    // Constructors
    // Default constructor initializing root to null
    public AVL() {
        root = null;
    }
    
    // Overload constructor AVL(int val) which takes in an integer value,
    // and creates a TNode and use it as root
    public AVL(int val) {
        root = new TNode(val);
    }
    
    // Overload constructor AVL(TNode obj) which takes a TNode as an argument
    // and use it as the root of the tree.
    public AVL(TNode obj) {
        root = obj;
        
        // If the TNode obj has children, the constructor needs to create a
        // balanced tree from passed tree by one of the two following options:
        // • iteratively inserting nodes from the original tree and balancing the
        // new created AVL tree
        // • implementing a full tree balancing algorithm (Bonus)
    }
    
    // Setter and getter for root: the setter function must check if the node has
    // children. If children are found it must do the same as the overload constructor.
    // Hint: it is better to have a helper function (private function) that creates
    // an the AVL tree and call it for the constructor and the setter
    
    // Insert(int val): creates a new node with data val to be inserted into the tree
    // Must maintain the tree balance. It can call the super.insert (insert
    // function from BST), but will need to also balance the tree after
    public void Insert(int val) {
        // TODO: Implement Insert function
    }
    
    // Insert(TNode node) : inserts the node passed as argument into the tree
    // Must maintain the tree balance. It can call the super.insert (insert
    // function from BST), but will need to also balance the tree after
    public void Insert(TNode node) {
        // TODO: Implement Insert function
    }
    
    // Delete(int val): finds the node with val as data and deletes it, if not found prints
    // a statement that the value is not in the tree (Bonus)
    public void Delete(int val) {
        // TODO: Implement Delete function (Bonus)
    }
    
    // Inherited functions
    // TNode Search(int val): inherited from parent
    // printInOrder(): inherited from parent
    // printBF(): inherited from parent
    
    // Helper function to balance the tree
    private void balance() {
        // TODO: Implement balance function
    }
}


