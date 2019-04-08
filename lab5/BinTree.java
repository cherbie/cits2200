import CITS2200.*;

public class BinTree extends BinaryTree<E>
{
    Object item;
    BinaryTree b1;
    BinaryTree b2;

    //CONSTRUCTORS
    public BinTree() { super(); }
    public BinTree(E item, BinaryTree<E> b1, BinaryTree<E> b2) {  super(item, b1, b2); }

    /**
     * Should override the equals method that all objects have
     * @return true if both binary trees have exactly the same structure, false otherwise.
    **/
    public void equals()
    {

    }

    /**
     * Provides an instance of CITS2200.Iterator
     * @return every element stored in the tree exactly once
    **/
    public Iterator<E> iterator()
    {

    }
}
