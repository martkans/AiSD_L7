public class NodeColor extends Node {
    private char color;

    public NodeColor(Node leftChild, Node rightChild, int value) {
        super(leftChild, rightChild, value);
        this.color = 'R';
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
