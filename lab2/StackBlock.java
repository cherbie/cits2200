import CITS2200.*;

public class StackBlock implements CITS2200.Stack
{
    private Object[] stack; //BLOCK IMPLEMENTATION OF A STACK ADT
    private int top; //array index reference to the top element

    /**
     * Constructs a new empty stack of size s.
     * @param s int size
     **/
    public StackBlock(int s)
    {
        stack = new Object[s]; //all array indices are initialised to null
        top = -1;
    }

    /**
     * Tests stack object on if it is empty.
     * @return true iff the stack is empty. False otherwise.
     **/
    public boolean isEmpty()
    {
        return top<0 && stack[0]==null; //top most element = first element;
    }

    /**
     * Tests stack object on the condition that it is full
     * @return true iff the stack is full. False otherwise.
     **/
    public boolean isFull()
    {
        return top>=(stack.length-1); //top most element is the last element
    }

    /**
     * Push Object to the top of the stack.
     * @throws Overflow exception if the stack is full.
     * @param o Object
     **/
    public void push(Object o) throws Overflow
    {
        if(!isFull())
        {
            stack[++top] = o; //assign new object to the top of the stack
        }
        else
        {
            throw new Overflow("Error: STACK IS FULL.");
        }
    }

    /**
     * Examines the top of the stack.
     * @throws Underflow exception if the stack is empty.
     * @return Object at the top of the stack.
     **/
     public Object examine() throws Underflow
     {
        if(isEmpty())
        {
            throw new Underflow("Error: STACK IS EMPTY.");
        }
        else
        {
            return stack[top]; //return the top most element
        }
     }

     /**
      * Remove and return the object at the top of the stack.
      * @return Object at the top of the stack.
      * @throws Underflow exception if the stack is empty.
      **/
      public Object pop() throws Underflow
      {
          if(isEmpty())
          {
              throw new Underflow("No elements in stack.");
          }
          else
          {
              Object o = examine();
              stack[top] = null;
              --top;
              return o; //return the Object that has been removed.
          }
      }
}
