package Node;

public class NodeColor extends AbstractNode {
    private char color;
    private AbstractNode parent;

    public NodeColor(AbstractNode leftChild, AbstractNode rightChild, int value, AbstractNode parent) {
        super(leftChild, rightChild, value);
        this.parent = parent;
        this.color = 'R';
    }

    @Override
    public char getColor() {
        return color;
    }

    @Override
    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public AbstractNode getParent() {
        return parent;
    }

    @Override
    public void setParent(AbstractNode node) {
        parent = node;
    }
}
