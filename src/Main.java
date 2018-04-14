public class Main {
    public static void main(String[] args) {
        Tree example = new RedBlackTree();
        example.insert(15);
        example.insert(16);
        example.insert(17);
        example.insert(18);
        example.insert(19);
        example.insert(20);
        example.insert(21);
        example.insert(-2);
        example.insert(0);
        example.insert(22);
        example.insert(23);
        example.insert(24);
        example.insert(25);
        example.insert(26);
        example.insert(27);
        example.insert(-30);
        example.insert(2);
        example.insert(-23);
        example.drawTree();
    }
}
