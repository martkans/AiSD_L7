public class BST {
    private Node root;
    private int nodeNumber;
    private int leafNumber;
    private int height;
    private int innerNodeNumber;
    private String[] tree;

    public BST(){
        root = null;
        nodeNumber = 0;
    }

    public void insert(int number){
        insert(number, root);
    }

    private Node insert(int number, Node root){
        if (this.root == null){
            this.root = new Node(null, null, number);
            return this.root;
        }

        if(root == null) {
            root = new Node(null, null, number);
            return root;
        } else if(number < root.getValue()) {
            root.setLeftChild(insert(number, root.getLeftChild()));
            if (root.getLeftChild() != null)
                root.getLeftChild().setLevel(root.getLevel()+1);
        } else if(number > root.getValue()){
            root.setRightChild(insert(number, root.getRightChild()));
            if (root.getRightChild() != null)
                root.getRightChild().setLevel(root.getLevel()+1);
        }
        return root;
    }

    public Node search(int number){
        return search(number, root);
    }

    private Node search(int number, Node root){
        if (root == null)
            return null;
        else if (root.getValue() == number)
            return root;
        else if (number < root.getValue())
            return search(number, root.getLeftChild());
        else
            return search(number, root.getRightChild());
    }

    public void delete(int number){
        delete(root, number);
    }

    private Node delete(Node root, int number){
        if (root == null)
            return null;
        else if(root.getValue() > number)
            root.setLeftChild(delete(root.getLeftChild(), number));
        else if(root.getValue() < number)
            root.setRightChild(delete(root.getRightChild(), number));
        else {
            if(root.getLeftChild() == null)
                return root.getRightChild();
            else if(root.getRightChild() == null)
                return root.getLeftChild();

            root.setValue(maxElement(root.getLeftChild()));
            delete(root.getLeftChild(),root.getValue());
        }
        return root;
    }

    public void drawTree(){
        heightTree();
        if (height == 0 && root != null)
            System.out.println(root.getValue());
        else {
            tree = new String[heightTree()+1];
            for (int i = 0; i  < tree.length; i++)
                tree[i] = "";
            drawTree(root, 0);
            for (String s:tree) {
                System.out.println(s);
            }
        }
    }

    private void drawTree(Node root, int level) {
        if(tree[level].equals(""))
            for (int i = 0; i < Math.pow(2,(height - level));i++)
                tree[level] += "\t";
        else
            for (int i = 0; i < Math.pow(2,(height - level + 1));i++)
                tree[level] += "\t";

        if (root != null)
            tree[level] += String.valueOf(root.getValue());

        if (level < height){
            if (root == null){
                drawTree(null, level+1);
                drawTree(null, level+1);
            } else {
                drawTree(root.getLeftChild(), level+1);
                drawTree(root.getRightChild(), level+1);
            }
        }
    }

    public int heightTree(){
        height = 0;
        heightTree(root);
        return height;
    }

    private void heightTree(Node root){
        if (root != null){
            if (root.getLevel() > height)
                height = root.getLevel();
            heightTree(root.getLeftChild());
            heightTree(root.getRightChild());
        }
    }

    public int leafNumber(){
        leafNumber = 0;
        if (root != null)
            leafNumber(root);
        return leafNumber;
    }

    private void leafNumber(Node root){
        if (root != null){
            if (root.getLeftChild() == null && root.getRightChild() == null) {
                leafNumber++;
            } else {
                leafNumber(root.getLeftChild());
                leafNumber(root.getRightChild());
            }
        }
    }

    public int nodeNumber(){
        nodeNumber = 0;
        if (root != null)
            nodeNumber(root);
        return nodeNumber;
    }

    private void nodeNumber(Node root){
        if (root != null){
            nodeNumber++;
            nodeNumber(root.getLeftChild());
            nodeNumber(root.getRightChild());
        }
    }

    public int innerNodeNumber(){
        innerNodeNumber = 0;
        if (this.root != null)
            innerNodeNumber(root);
        return innerNodeNumber;
    }

    private void innerNodeNumber(Node root){
        if (root != null){
            if (root.getLeftChild() != null || root.getRightChild() != null) {
                innerNodeNumber(root.getLeftChild());
                innerNodeNumber(root.getRightChild());
                innerNodeNumber++;
            }
        }
    }

    public int maxElement(){
        return maxElement(root);
    }

    private int maxElement(Node root){
        if (this.root == null) throw new NullPointerException();
        if (root.getRightChild() != null)
            return maxElement(root.getRightChild());
        else
            return root.getValue();

    }

    public int minElement(){
        return minElement(root);
    }

    private int minElement(Node root){
        if (this.root == null) throw new NullPointerException();
        if (root.getLeftChild() != null){
            return minElement(root.getLeftChild());
        } else
            return root.getValue();
    }
}
