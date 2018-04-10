package Node;

abstract public class AbstractNode {
    protected AbstractNode leftChild;
    protected AbstractNode rightChild;
    protected int value;
    protected int level;

    public AbstractNode(AbstractNode leftChild, AbstractNode rightChild, int value) {
        this.level = 0;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
    }

    public AbstractNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AbstractNode leftChild) {
        this.leftChild = leftChild;
    }

    public AbstractNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(AbstractNode rightChild) {
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

    abstract public char getColor();
    abstract public void setColor(char color);
    abstract public AbstractNode getParent();
    abstract public void setParent(AbstractNode node);
}
