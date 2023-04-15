package com.example;

import static org.junit.Assert.*;
import org.junit.Test;

import com.example.datastructures.nodes.TNode;
import com.example.datastructures.trees.AVL;
import com.example.datastructures.trees.BST;

public class treesTest {

    @Test
    public void testBST() {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(20);
        bst.insert(8);
        bst.insert(30);

        assertEquals(10, bst.getRoot().getData());
        assertEquals(5, bst.getRoot().getLeft().getData());
        assertEquals(20, bst.getRoot().getRight().getData());
        assertEquals(8, bst.getRoot().getLeft().getRight().getData());
        assertEquals(30, bst.getRoot().getRight().getRight().getData());

        bst.delete(20);
        assertNull(bst.search(20));
        
        assertEquals(30, bst.getRoot().getRight().getData());

        assertEquals(10, bst.getRoot().getData());

        bst.insert(15);
        assertNotNull(bst.search(15));
    }

    @Test
    public void testInsert() {
        AVL avl = new AVL();
        avl.insert(5);
        avl.insert(10);
        avl.insert(15);
        assertEquals(5, avl.getRoot().getData());
    }

    @Test
    public void testDelete() {
        AVL avl = new AVL();
        avl.insert(5);
        avl.insert(10);
        avl.insert(15);
        avl.delete(10);
        assertNull(avl.search(10));
    }

    @Test
    public void testSearch() {
        AVL avl = new AVL();
        avl.insert(5);
        avl.insert(10);
        avl.insert(15);
        TNode result = avl.search(10);
        assertEquals(10, result.getData());
    }

    @Test
public void testInsertDuplicates() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    avl.insert(10);
    assertEquals(10, avl.getRoot().getRight().getData());
}

@Test
public void testDeleteNonexistentNode() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    avl.delete(20);
    assertEquals(5, avl.getRoot().getData());
}

@Test
public void testDeleteLeafNode() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    avl.delete(15);
    assertNull(avl.search(15));
}

@Test
public void testDeleteNodeWithOneChild() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    avl.insert(12);
    avl.delete(15);
    assertNull(avl.search(15));
    assertEquals(10, avl.getRoot().getRight().getData());
}

@Test
public void testDeleteNodeWithTwoChildren() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    avl.insert(12);
    avl.delete(10);
    assertNull(avl.search(10));
    assertEquals(15, avl.getRoot().getRight().getData());
}

@Test
public void testSearchNonexistentNode() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    TNode result = avl.search(20);
    assertNull(result);
}

@Test
public void testGetRoot() {
    AVL avl = new AVL();
    avl.insert(5);
    avl.insert(10);
    avl.insert(15);
    TNode root = avl.getRoot();
    assertEquals(5, root.getData());
}

@Test
    public void testAVLConstructorWithSingleNode() {
        TNode node = new TNode(5, 0, null, null, null);
        AVL avl = new AVL(node);
        assertEquals(avl.getRoot(), node);
    }

    @Test
    public void testAVLConstructorWithNullNode() {
        AVL avl = new AVL(null);
        assertNull(avl.getRoot());
    }

    // Additional tests for both BST and AVL

    @Test
public void testEmptyBST() {
    BST bst = new BST();
    assertNull(bst.getRoot());
}

@Test
public void testEmptyAVL() {
    AVL avl = new AVL();
    assertNull(avl.getRoot());
}

@Test
public void testBSTInsertionAndSearch() {
    BST bst = new BST();
    bst.insert(10);
    bst.insert(5);
    bst.insert(20);
    bst.insert(8);
    bst.insert(30);
    
    TNode result1 = bst.search(20);
    TNode result2 = bst.search(8);
    TNode result3 = bst.search(15);
    assertNotNull(result1);
    assertNotNull(result2);
    assertNull(result3);
    assertEquals(20, result1.getData());
    assertEquals(8, result2.getData());
}

@Test
public void testAVLInsertionAndSearch() {
    AVL avl = new AVL();
    avl.insert(10);
    avl.insert(5);
    avl.insert(20);
    avl.insert(8);
    avl.insert(30);
    
    TNode result1 = avl.search(20);
    TNode result2 = avl.search(8);
    TNode result3 = avl.search(15);
    assertNotNull(result1);
    assertNotNull(result2);
    assertNull(result3);
    assertEquals(20, result1.getData());
    assertEquals(8, result2.getData());
}

@Test
public void testAVLRotation() {
    AVL avl = new AVL();
    avl.insert(10);
    avl.insert(5);
    avl.insert(20);
    avl.insert(8);
    avl.insert(15);
    avl.insert(30);
    avl.insert(25);
    avl.insert(40);
    
    assertEquals(10, avl.getRoot().getData());
    assertEquals(5, avl.getRoot().getLeft().getData());
    assertEquals(20, avl.getRoot().getRight().getData());
}


}
