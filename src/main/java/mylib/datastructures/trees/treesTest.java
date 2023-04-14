package main.java.mylib.datastructures.trees;
import main.java.mylib.datastructures.nodes.TNode;

public class treesTest {
    public static void main(String[] args) {
        
        TNode root = new TNode(10, 0, null, null, null);
        TNode root2 = new TNode(100, 0, null, null, null);
        BST tree = new BST(root);
        tree.insert(new TNode(5, 0, null, null, null));
        tree.insert(new TNode(15, 0, null, null, null));
        tree.insert(new TNode(1, 0, null, null, null));
        tree.insert(new TNode(7, 0, null, null, null));
        tree.insert(12);
        tree.insert(20);
        tree.insert(new TNode(8, 0, null, null, null));
        tree.delete(15);
        tree.delete(155);
        tree.delete(5);
        tree.printBF();
        tree.printInOrder(root);
        

    }

}
