public class Node {
    private Node leftChild;
    private Node rightChild;
    private int value;
    private int level;

    public Node(Node leftChild, Node rightChild, int value) {
        this.level = 0;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
