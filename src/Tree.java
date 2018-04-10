import Node.*;

abstract public class Tree {
    protected AbstractNode root;
    protected String[] tree;

    public Tree(){
        root = null;
    }

    abstract public void insert(int number);
    abstract public void delete(int number);

    public AbstractNode search(int number){
        return search(number, root);
    }

    private AbstractNode search(int number, AbstractNode root){
        if (root == null)
            return null;
        else if (root.getValue() == number)
            return root;
        else if (number < root.getValue())
            return search(number, root.getLeftChild());
        else
            return search(number, root.getRightChild());
    }

    public void drawTree(){
        int height = heightTree(root,0);
        if (height == 0 && root != null) {
            if (root.getClass() == NodeColor.class)
                System.out.print("[" + root.getColor() + "]");
            System.out.print(root.getValue() + "\n");
        } else {
            tree = new String[height+1];
            for (int i = 0; i  < tree.length; i++)
                tree[i] = "";
            drawTree(root, 0, height);
            for (String s:tree) {
                System.out.println(s);
            }
        }
    }

    protected void drawTree(AbstractNode root, int level, int height) {
        if(tree[level].equals(""))
            for (int i = 0; i < Math.pow(2,(height - level));i++)
                tree[level] += "\t";
        else
            for (int i = 0; i < Math.pow(2,(height - level + 1));i++)
                tree[level] += "\t";
        if (root != null) {
            if (root.getClass() == NodeColor.class)
                tree[level] += "[" + root.getColor() + "]";
            tree[level] += String.valueOf(root.getValue());
        }
        if (level < height){
            if (root == null){
                drawTree(null, level+1, height);
                drawTree(null, level+1, height);
            } else {
                drawTree(root.getLeftChild(), level+1, height);
                drawTree(root.getRightChild(), level+1, height);
            }
        }
    }

    public int heightTree(){
        return heightTree(root,0);
    }

    protected int heightTree(AbstractNode root, int height){
        if (root != null){
            if (root.getLevel() > height)
                height = root.getLevel();
            height = heightTree(root.getLeftChild(), height);
            height = heightTree(root.getRightChild(), height);
        }
        return height;
    }

    public int leafNumber(){
        return leafNumber(root,0);
    }

    private int leafNumber(AbstractNode root, int leaf){
        if (root != null){
            if (root.getLeftChild() == null && root.getRightChild() == null) {
                leaf++;
            } else {
                leaf = leafNumber(root.getLeftChild(), leaf);
                leaf = leafNumber(root.getRightChild(), leaf);
            }
        }
        return leaf;
    }

    public int nodeNumber(){
        return nodeNumber(root,0);
    }

    private int nodeNumber(AbstractNode root, int nodeNumber){
        if (root != null){
            nodeNumber++;
            nodeNumber = nodeNumber(root.getLeftChild(), nodeNumber);
            nodeNumber = nodeNumber(root.getRightChild(), nodeNumber);
        }
        return nodeNumber;
    }

    public int innerNodeNumber(){
        return innerNodeNumber(root, 0);
    }

    private int innerNodeNumber(AbstractNode root, int innerNodeNumber){
        if (root != null){
            if (root.getLeftChild() != null || root.getRightChild() != null) {
                innerNodeNumber++;
                innerNodeNumber = innerNodeNumber(root.getLeftChild(), innerNodeNumber);
                innerNodeNumber = innerNodeNumber(root.getRightChild(), innerNodeNumber);
            }
        }
        return innerNodeNumber;
    }

    public int maxElement(){
        return maxElement(root);
    }

    protected int maxElement(AbstractNode root){
        if (this.root == null) throw new NullPointerException();
        if (root.getRightChild() != null)
            return maxElement(root.getRightChild());
        else
            return root.getValue();

    }

    public int minElement(){
        return minElement(root);
    }

    private int minElement(AbstractNode root){
        if (this.root == null) throw new NullPointerException();
        if (root.getLeftChild() != null){
            return minElement(root.getLeftChild());
        } else
            return root.getValue();
    }
}
