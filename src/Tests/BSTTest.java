import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    Tree bst;
    Tree emptyBst;

    @BeforeEach
    void prepare(){
        bst = new BST();
        emptyBst = new BST();
        bst.insert(10);
        bst.insert(5);
        bst.insert(90);
        bst.insert(9);
        bst.insert(40);
        bst.insert(20);
        bst.insert(15);
    }

    @Test
    void insert() {
        emptyBst.insert(20);
        emptyBst.insert(10);
        emptyBst.insert(5);
        emptyBst.insert(100);
        emptyBst.insert(10);

        assertEquals(20, emptyBst.search(20).getValue());
        assertEquals(10, emptyBst.search(10).getValue());
        assertEquals(5, emptyBst.search(5).getValue());
        assertEquals(100, emptyBst.search(100).getValue());
    }

    @Test
    void search() {
        assertEquals(20, bst.search(20).getValue());
        assertEquals(10, bst.search(10).getValue());
        assertEquals(5, bst.search(5).getValue());
        assertEquals(null, bst.search(100));

        assertEquals(null, emptyBst.search(30));
    }

    @Test
    void delete() {
        bst.delete(90);
        assertEquals(null, bst.search(90));
        bst.delete(10);
        assertEquals(null, bst.search(10));
        bst.delete(5);
        assertEquals(null, bst.search(5));
    }

    @Test
    void heightTree() {
        assertEquals(4, bst.heightTree());
        assertEquals(0, emptyBst.heightTree());
        bst.insert(13);
        assertEquals(5, bst.heightTree());
        bst.delete(15);
        bst.delete(13);
        assertEquals(3,bst.heightTree());
    }

    @Test
    void leafNumber() {
        assertEquals(2, bst.leafNumber());
        assertEquals(0, emptyBst.leafNumber());
        bst.insert(2);
        assertEquals(3, bst.leafNumber());
        bst.delete(9);
        assertEquals(2, bst.leafNumber());
    }

    @Test
    void nodeNumber() {
        assertEquals(7, bst.nodeNumber());
        assertEquals(0, emptyBst.nodeNumber());
        bst.delete(10);
        bst.delete(909);
        assertEquals(6, bst.nodeNumber());
    }

    @Test
    void innerNodeNumber() {
        assertEquals(5, bst.innerNodeNumber());
        assertEquals(0, emptyBst.innerNodeNumber());
        bst.delete(15);
        assertEquals(4, bst.innerNodeNumber());
    }

    @Test
    void maxElement() {
        assertEquals(90, bst.maxElement());
        assertThrows(NullPointerException.class, () -> emptyBst.maxElement());
    }

    @Test
    void minElement() {
        assertEquals(5, bst.minElement());
        assertThrows(NullPointerException.class, () -> emptyBst.minElement());
    }
}