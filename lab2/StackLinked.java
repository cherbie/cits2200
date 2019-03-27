import CITS2200.*;

class StackLinked implements CITS2200.Stack
{
    private Link first; //top most element in the linked stack

    /**
     * Constructs a new empty StackLinked Object
     **/
    public StackLinked()
    {
        first = null; //null pointer to linked Object
    }

    /**
     * Tests stack object on if it is empty.
     * @return true iff the stack is empty. False otherwise.
     **/
    public boolean isEmpty()
    {
        return first == null; //top most Link object is null
    }

    /**
     * Create new link to char at the top of the stack.
     * @param c char
     **/
    public void push(Object c)
    {
        Link n = new Link(c, first); //create new link object
        first = n;
    }

    /**
     * Examines the top of the stack.
     * @return Object at the top of the stack.
     **/
     public Object examine()
     {
        return first.item; //return the top most element
     }

     /**
      * Remove and return the object at the top of the stack.
      * @return Object at the top of the stack.
      **/
      public Object pop() throws Underflow
      {
          Object popItem = first.item; //fetch Object to be returned
          first.item = null; //clear data before disgarding
          first = first.successor; //top of stack becomes successor
          return popItem; //return the Object that has been removed.
      }
}
