import Node.AbstractNode;
import Node.NodeColor;

public class RedBlackTree extends Tree {

    AbstractNode sentintel = null;
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
    public void delete(int number){
        delete(this.root, number);
        if (sentintel != null){
            recoloringDelete(sentintel);
        } else {
            AbstractNode temp = searchD(this.root);
            if (temp != null) {
                recoloringDelete(temp);
            }
        }
    }

    private AbstractNode searchD(AbstractNode root){
        if (root != null){
            if (root.getColor() == 'D')
                return root;
            AbstractNode s = searchD(root.getLeftChild());
            if (s == null){
                return searchD(root.getRightChild());
            } else
                return s;
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
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

            if(root.getLeftChild() == null && root.getRightChild() == null){
                if (root.getColor() == 'B'){
                    sentintel = new NodeColor(null, null, 0, root.getParent());
                    sentintel.setColor('B');
                    root.setLeftChild(sentintel);
                }
            }

            if(root.getLeftChild() == null) {
                if (root.getRightChild() != null) {
                    root.getRightChild().setLevel(root.getLevel());
                    if (root.getRightChild().getColor() == 'R' || root.getColor() == 'R')
                        root.getRightChild().setColor('B');
                    else
                        root.getRightChild().setColor('D');
                    root.getRightChild().setParent(root.getParent());
                }
                return root.getRightChild();
            }
            else if(root.getRightChild() == null){
                if (root.getLeftChild() != null) {
                    root.getLeftChild().setLevel(root.getLevel());
                    if (root.getLeftChild().getColor() == 'R' || root.getColor() == 'R')
                        root.getLeftChild().setColor('B');
                    else
                        root.getLeftChild().setColor('D');
                    root.getLeftChild().setParent(root.getParent());
                }
                return root.getLeftChild();
            }
            root.setValue(maxElement(root.getLeftChild()));
            if (root.getLeftChild().getValue() == root.getValue()){
                root.setLeftChild(root.getLeftChild().getRightChild());
                if (root.getLeftChild() != null)
                    root.getLeftChild().setParent(root);
            } else
            delete(root.getLeftChild(), root.getValue());
            if (sentintel != null){
                recoloringDelete(sentintel);
            } else {
                AbstractNode temp = searchD(root);
                if (temp != null) {
                    recoloringDelete(temp);
                }
            }
        }
        return root;
    }

    private void recoloringDelete(AbstractNode root){
        if (root == null)
            return;
        if (root == this.root )
            this.root.setColor('B');

        AbstractNode sibling;
        AbstractNode left;
        AbstractNode right;
        AbstractNode parent = root.getParent();
        if (parent == null) {
            return;
        }

        if (parent.getLeftChild() == root)
            sibling = parent.getRightChild();
        else
            sibling = parent.getLeftChild();

        if (sibling == null) {
            sibling = new NodeColor(null, null, 0, parent);
            sibling.setColor('B');
        }

        if (root == sentintel){
            if (parent.getLeftChild() == sentintel)
                parent.setLeftChild(null);
            else
                parent.setRightChild(null);
            sentintel = null;
        }

        if (sibling.getLeftChild() != null)
            left = sibling.getLeftChild();
        else {
            left = new NodeColor(null, null, 0, sibling);
            left.setColor('B');
        }

        if (sibling.getRightChild() != null)
            right = sibling.getRightChild();
        else {
            right = new NodeColor(null, null, 0, sibling);
            right.setColor('B');
        }

        if (sibling.getColor() == 'B'){
            root.setColor('B');
            if (right.getColor() == 'B' && left.getColor() == 'B') {
                sibling.setColor('R');
                if (parent.getColor() == 'R')
                    parent.setColor('B');
                else {
                    parent.setColor('D');
                    recoloringDelete(parent);
                }
            }
            else if (parent.getLeftChild() == sibling){
                if (right.getColor() == 'R' && left.getColor() != 'R') {
                    right.setColor('B');
                    rotateToLeft(sibling);
                }
                left.setColor('B');
                rotateToRight(parent);
            } else {
                if (left.getColor() == 'R' && right.getColor() != 'R') {
                    left.setColor('B');
                    rotateToRight(sibling);
                }
                right.setColor('B');
                rotateToLeft(parent);
            }
        } else {
            sibling.setColor('B');
            parent.setColor('R');
            if (parent.getLeftChild() == sibling){
                rotateToRight(parent);
            } else {
                rotateToLeft(parent);
            }
            recoloringDelete(root);
        }
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
