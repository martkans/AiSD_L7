import Node.*;

public class BST extends Tree{

    public BST(){
        super();
    }

    public void insert(int number){
        insert(number, root);
    }

    private AbstractNode insert(int number, AbstractNode root){
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

    public void delete(int number){
        delete(root, number);
    }

    private AbstractNode delete(AbstractNode root, int number){
        if (root == null)
            return null;
        else if(root.getValue() > number)
            root.setLeftChild(delete(root.getLeftChild(), number));
        else if(root.getValue() < number)
            root.setRightChild(delete(root.getRightChild(), number));
        else {
            if (root == this.root){
                if (root.getLeftChild() == null && root.getRightChild() == null) {
                    this.root = null;
                    return null;
                } else if (root.getLeftChild() == null && root.getRightChild() != null){
                    root.setValue(minElement(root.getRightChild()));
                    root.setRightChild(delete(root.getRightChild(),root.getValue()));
                } else if (root.getLeftChild() != null && root.getRightChild() == null){
                    root.setValue(maxElement(root.getLeftChild()));
                    root.setLeftChild(delete(root.getLeftChild(),root.getValue()));
                }
            }

            if(root.getLeftChild() == null) {
                if (root.getRightChild() != null)
                    root.getRightChild().setLevel(root.getLevel());
                return root.getRightChild();
            }
            else if(root.getRightChild() == null) {
                if (root.getLeftChild() != null)
                    root.getLeftChild().setLevel(root.getLevel());
                return root.getLeftChild();
            }

            root.setValue(maxElement(root.getLeftChild()));
            if (root.getLeftChild().getValue() == root.getValue()){
                root.setLeftChild(root.getLeftChild().getRightChild());
            } else
                delete(root.getLeftChild(), root.getValue());
        }
        return root;
    }
}
