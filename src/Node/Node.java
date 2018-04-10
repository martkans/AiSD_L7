package Node;

public class Node extends AbstractNode {
    public Node(Node leftChild, Node rightChild, int value) {
        super(leftChild, rightChild, value);
    }

    @Override
    public char getColor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setColor(char color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AbstractNode getParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setParent(AbstractNode node) {
        throw new UnsupportedOperationException();
    }
}
