import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {

    Tree redBlackTree;
    Tree emptyRedBlackTree;
    @BeforeEach
    void prepare(){
        redBlackTree = new RedBlackTree();
        emptyRedBlackTree = new RedBlackTree();
        redBlackTree.insert(10);
        redBlackTree.insert(5);
        redBlackTree.insert(90);
        redBlackTree.insert(9);
        redBlackTree.insert(40);
        redBlackTree.insert(20);
        redBlackTree.insert(15);
    }

    @Test
    void insert() {
        emptyRedBlackTree.insert(70);
        emptyRedBlackTree.insert(90);
        emptyRedBlackTree.insert(9);
        emptyRedBlackTree.insert(900);
        emptyRedBlackTree.insert(90);

        assertEquals(70, emptyRedBlackTree.search(70).getValue());
        assertEquals(90, emptyRedBlackTree.search(90).getValue());
        assertEquals(9, emptyRedBlackTree.search(9).getValue());
        assertEquals(900, emptyRedBlackTree.search(900).getValue());
    }

    @Test
    void delete() {
        redBlackTree.delete(40);
        assertEquals(null, redBlackTree.search(40));
        redBlackTree.delete(10);
        assertEquals(null, redBlackTree.search(10));
        redBlackTree.delete(5);
        assertEquals(null, redBlackTree.search(5));
    }

    @Test
    void search() {
        assertEquals(90, redBlackTree.search(90).getValue());
        assertEquals(40, redBlackTree.search(40).getValue());
        assertEquals(5, redBlackTree.search(5).getValue());
        assertEquals(null, redBlackTree.search(100));

        assertEquals(null, emptyRedBlackTree.search(30));
    }

    @Test
    void heightTree() {
        assertEquals(3, redBlackTree.heightTree());
        assertEquals(0, emptyRedBlackTree.heightTree());
        redBlackTree.insert(13);
        assertEquals(3, redBlackTree.heightTree());
/*        bst.delete(15);
        bst.delete(13);
        assertEquals(3,bst.heightTree());*/
    }

    @Test
    void leafNumber() {
        assertEquals(3, redBlackTree.leafNumber());
        assertEquals(0, emptyRedBlackTree.leafNumber());
        redBlackTree.insert(2);
        assertEquals(4, redBlackTree.leafNumber());
//        bst.delete(9);
//        assertEquals(2, bst.leafNumber());
    }

    @Test
    void nodeNumber() {
        assertEquals(7, redBlackTree.nodeNumber());
        assertEquals(0, emptyRedBlackTree.nodeNumber());
/*        bst.delete(10);
        bst.delete(909);
        assertEquals(6, bst.nodeNumber());*/
    }

    @Test
    void innerNodeNumber() {
        assertEquals(4, redBlackTree.innerNodeNumber());
        assertEquals(0, emptyRedBlackTree.innerNodeNumber());
/*        redBlackTree.delete(15);
        assertEquals(4, redBlackTree.innerNodeNumber());*/
    }

    @Test
    void maxElement() {
        assertEquals(90, redBlackTree.maxElement());
        assertThrows(NullPointerException.class, () -> emptyRedBlackTree.maxElement());
    }

    @Test
    void minElement() {
        assertEquals(5, redBlackTree.minElement());
        assertThrows(NullPointerException.class, () -> emptyRedBlackTree.minElement());
    }
}