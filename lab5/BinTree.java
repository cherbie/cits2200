import CITS2200.*;

public class BinTree extends BinaryTree<E>
{
    Object item;
    BinaryTree leftTree;
    BinaryTree rightTree;

    //CONSTRUCTORS
    public BinTree() { super(); }
    public BinTree(E item, BinaryTree<E> b1, BinaryTree<E> b2) {  super(item, b1, b2); }

    /**
     * Tests whether the tree is equal to an Object
     * where two trees are equal if either both trees are empty
     * both trees contain equal items at the root (tested using the equals method of E)
     * have equal left subtrees and equal right subtrees (recursively calling the equals method of BinaryTree.
     * @override equals in class Object
     * @return true if both trees are equal
     * @param o Object
    **/
    public abstract boolean equals(Object o)
    {
        else if(!(o instanceof CITS2200.BinaryTree) || o == null)
            return false; //Object is not a BinaryTree or child of binary tree.
        else
        {
            //CHECK IF BOTH TREES ARE EMPTY (TRUE)
            if(isEmpty() && o.isEmpty())
                return true;
            else
            {
                if(getItem() != o.getItem()) //CHECK IF BOTH TREES CONTAIN EQUAL ITEM AT THE ROOT
                    return false;
                if(!leftTree.equals( o.getLeft() )) //CHECK IF EQUAL LEFT SUBSTREES
                    return false;
                if(!rightTree.equals( o.getRight() )) //CHECK IF EQUAL RIGHT SUBTREES
                    return false;
                return true;
            }
        }
    }

    /**
     * Provides an instance of CITS2200.Iterator
     * @return every element stored in the tree exactly once
    **/
    public Iterator<E> iterator()
    {

    }
}
