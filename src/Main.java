public class Main {
    public static void main(String[] args) {
        Tree redBlackTree = new BST();
        redBlackTree.insert(30);
//        redBlackTree.insert(20);
//        redBlackTree.insert(40);
//        redBlackTree.insert(35);
//        redBlackTree.insert(50);
        redBlackTree.delete(30);
        redBlackTree.drawTree();
    }
}
