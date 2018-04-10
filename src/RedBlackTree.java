import Node.AbstractNode;
import Node.NodeColor;

public class RedBlackTree extends Tree {

    public RedBlackTree(){
        super();
    }

    @Override
    public void insert(int number) {
        insert(number, root, null);
        AbstractNode root = search(number);
        recoloring(root);
    }

    private AbstractNode insert(int number, AbstractNode root, AbstractNode parent){
        if (this.root == null){
            this.root = new NodeColor(null, null, number, parent);
            return this.root;
        }

        if(root == null) {
            root = new NodeColor(null, null, number, parent);
            return root;
        } else if(number < root.getValue()) {
            root.setLeftChild(insert(number, root.getLeftChild(), root));
            if (root.getLeftChild() != null)
                root.getLeftChild().setLevel(root.getLevel()+1);

        } else if(number > root.getValue()){
            root.setRightChild(insert(number, root.getRightChild(), root));
            if (root.getRightChild() != null)
                root.getRightChild().setLevel(root.getLevel()+1);
        }
        return root;
    }

    @Override
    public void delete(int number) {
        //TO DO
    }

    private void recoloring(AbstractNode root){
        if (root != null){
            if (root == this.root)
                this.root.setColor('B');
            AbstractNode parent = root.getParent();
            AbstractNode grandparent = null;
            if (parent != null) {
                if (parent.getParent() != null)
                    grandparent = root.getParent().getParent();
            }

            if (grandparent == null) return;
            AbstractNode uncle;

            if (parent == grandparent.getLeftChild())
                uncle = grandparent.getRightChild();
            else
                uncle = grandparent.getLeftChild();

            if (uncle == null) {
                uncle = new NodeColor(null, null, 0, grandparent);
                uncle.setColor('B');
            }
            if (parent.getColor() == 'R'){
                if (uncle.getColor() == 'R'){
                    uncle.setColor('B');
                    parent.setColor('B');
                    grandparent.setColor('R');
                    recoloring(grandparent);
                } else {
                    if (parent == grandparent.getLeftChild()){
                        if (root == parent.getRightChild())
                            rotateToLeft(parent);
                        rotateToRight(grandparent);
                        char temp = grandparent.getColor();
                        grandparent.setColor(parent.getColor());
                        parent.setColor(temp);
                    } else {
                        if (root == parent.getLeftChild())
                            rotateToRight(parent);
                        rotateToLeft(grandparent);
                        char temp = grandparent.getColor();
                        grandparent.setColor(parent.getColor());
                        parent.setColor(temp);
                    }
                }

            }
        }

    }
    private void rotateToRight(AbstractNode x){
        AbstractNode y = x.getLeftChild();

        int i = x.getLevel();
        x.setLevel(y.getLevel());
        changeLevel(x.getRightChild());

        x.setLeftChild(y.getRightChild());
        if (y.getRightChild() != null)
            y.getRightChild().setParent(x);
        y.setParent(x.getParent());
        if(y.getParent() == null)
            root = y;
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);
        y.setRightChild(x);
        x.setParent(y);

        y.setLevel(i);
        changeLevel(y.getLeftChild());

    }


    private void rotateToLeft(AbstractNode x){
        AbstractNode y = x.getRightChild();
        int i = x.getLevel();
        x.setLevel(y.getLevel());
        changeLevel(x.getLeftChild());

        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != null)
            y.getLeftChild().setParent(x);
        y.setParent(x.getParent());
        if(x.getParent() == null) {
            root = y;

        }
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);
        y.setLeftChild(x);
        x.setParent(y);

        y.setLevel(i);
        changeLevel(y.getRightChild());
    }

    private void changeLevel(AbstractNode root){
        if (root != null){
            root.setLevel(root.getParent().getLevel()+1);
            changeLevel(root.getLeftChild());
            changeLevel(root.getRightChild());
        }
    }
}
