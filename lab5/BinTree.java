import CITS2200.*;
import java.util.*;

public class BinTree<E> extends BinaryTree<E>
{
    private Object item;
    private BinaryTree leftTree;
    private BinaryTree rightTree;

    //CONSTRUCTORS
    /* Note: super() calls the constructor of the parent class */

    public BinTree()
    {
        super();
    }

    public BinTree(E item, BinaryTree<E> b1, BinaryTree<E> b2)
    {
        super(item, b1, b2);
    }

    /**
     * Tests whether the tree is equal to an Object
     * where two trees are equal if either both trees are empty
     * both trees contain equal items at the root (tested using the equals method of E)
     * have equal left subtrees and equal right subtrees (recursively calling the equals method of BinaryTree.
     * @override equals in class Object
     * @return true if both trees are equal
     * @param o Object
    **/
    public boolean equals(Object o)
    {
        if(!(o instanceof CITS2200.BinaryTree) || o == null)
            return false; //Object is not a BinaryTree or child of binary tree.
        //CHECK IF BOTH TREES ARE EMPTY (TRUE)
        if(this.isEmpty() && ((BinaryTree)o).isEmpty()) //special case
            return true;
        if(this.isEmpty() | ((BinaryTree)o).isEmpty())
            return false;
        if(this.getItem().equals( ((BinaryTree)o).getItem() )) //CHECK IF EQUAL LEFT SUBTREES
              return this.getLeft().equals(((BinaryTree)o).getLeft()) && this.getRight().equals(((BinaryTree)o).getRight());
        return false;
    }

    /**
     * Provides an instance of CITS2200.Iterator and
     * traverse the tree enqueing every element
     * @return every element stored in the tree exactly once
    **/
    public QueueIterator<E> iterator()
    {
        //preTransversal(this);
        return new QueueIterator<E>();
    }

    /**
     * Transverses through binary tree using preTransversal
     * @return E or the "leave"

    public void preTransversal(BinaryTree b)
    {
        if(!b.isEmpty())
        {
            preTransversal(b.getLeft());
            preTransversal(b.getRight());
        }
        else
        {
            list.add((E) b.getItem());
            return;
        }
    }
    */

    //sub-class
    private class QueueIterator<A> implements CITS2200.Iterator<A>
    {
        private LinkedList<BinaryTree<A>> l;

        /**
         * Constructor for the iterator implementing a Linked List ADT
        **/
        public QueueIterator()
        {
            l = new LinkedList<BinaryTree<A>>();
            l.offer((BinaryTree<A>)BinTree.this);
        }

        /**
         * Tests if there is a next item to return
         * @return true if there is another element and false otherwise
        **/
        public boolean hasNext()
        {
            return l.peek() != null;
        }

        /**
         * Moves the iterator to the next element
         * @return the next element
         * @throws OutOfBounds if there is no next element
        **/
        public A next() throws OutOfBounds
        {
            BinaryTree<A> ret = l.remove();
            BinaryTree<A> left = ret.getLeft();
            if(!left.isEmpty())
                l.offer(left);
            left = ret.getRight();
            if(!left.isEmpty())
                l.offer(left);
            return ret.getItem();
        }
    }

}
