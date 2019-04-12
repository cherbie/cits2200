import CITS2200.*;

public class ListLinkedBefore implements CITS2200.List
{
    //FIELDS
    private Link before;
    private Link after;
    private Link beforeAfter;

    //-------------- CONSTRUCTORS
    /**
     * Initialises an empty list.
    **/
    public ListLinkedBefore()
    {
        after = new Link(null, before);
        before = new Link(null, after); //linked to after
        beforeAfter = before;
    }

    //-------------- CHECKERS
    /**
     * Check if list is empty
     * @return true if the list is empty, false otherwise
    **/
    public boolean isEmpty()
    {
        return before.successor == after;
    }

    /**
     *Check if window is over the "before first" position
     * @return true if window is over the before first position
    **/
    public boolean isBeforeFirst(WindowLinked w)
    {
        return w.link == after;
    }

    /**
     * Check if window is over the "after last" position
     * @return true if window is over the after last position
     **/
    public boolean isAfterLast(WindowLinked w)
    {
        return w.link == beforeAfter;
    }

    //--------- MANIPULATORS
    /**
     * Initialises window to the before first position
     * @param w WindowLinked
    **/
    public void beforeFirst(WindowLinked w)
    {
        w.link = after;
    }

    /**
     * Initialises window to the after last position
     * @param w WindowLinked
    **/
    public void afterLast(WindowLinked w)
    {
        w.link = beforeAfter;
    }

    /**
     * Moves the window to the next window position
     * @param w WindowLinked
     * @throws OutOfBounds if the window is past the end of the list
    **/
    public void next(WindowLinked w) throws OutOfBounds
    {
        if(!isAfterLast(w))
        {
            w.link = w.link.successor; //next window position not deleting element
        }
        else
            throw new OutOfBounds("next: bad input for window.");
    }

    /**
     * Moves the window to the previous window position
     * @param w WindowLinked
     * @throws OutOfBounds if the window is before the start of the list
    **/
    public void previous(WindowLinked w) throws OutOfBounds
    {
        if(!isEmpty() && !isBeforeFirst(w))
        {
            Link first = after; //first element in the list.
            while(first.successor != w.link.successor)
            {
                first = first.successor;
                if(first == after)
                    throw new OutOfBounds("previous: could not navigate to previous element.");
            }
            w.link = first; //set window to the previous element.
        }
        else
            throw new OutOfBounds("previous: window is out of list.");
    }

    /**
     * Extra element is added to the ListBlock after the window
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is past the end of the list
    **/
    public void insertAfter(Object e, WindowLinked w) throws OutOfBounds
    {
        if(w.link.successor != null && !isAfterLast(w))
        {
            Link next = new Link(e, w.link.successor.successor); //link points to previous window successor
            w.link.successor.successor = next;
        }
        else
            throw new OutOfBounds("insert after: window is not in the list.");
    }

    /**
     * An extra element is added to the list before the window
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is before the start of the list
    **/
    public void insertBefore(Object e, WindowLinked w) throws OutOfBounds
    {
        if(!isBeforeFirst(w) && w.link.successor != null)
        {
            Link next = new Link(e, w.link.successor); //the current window;
            w.link.successor = next; //point new element to current window
            if(w.link == beforeAfter)
                after = next;
        }
        else
            throw new OutOfBounds("Window is before the start of the list");
    }

    /**
     * Examines the element in the window
     * @return Object element under the window
     * @throws OutOfBounds if the window is outside the list
     * @param w WindowLinked
    **/
    public Object examine(WindowLinked w) throws OutOfBounds
    {
        if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
        {
            return w.link.successor.item;
        }
        else
            throw new OutOfBounds("examine(): window is outside the list.");
    }

    /**
     * Replaces the element under the window
     * @return the old element under the window of type E
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * after last positions or empty.
    **/
    public Object replace(Object e, WindowLinked w) throws OutOfBounds
    {
        if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
        {
            Object element = w.link.successor.item;
            w.link.successor.item = e;
            return element;
        }
        else
            throw new OutOfBounds("replace(): window is outside the list.");
    }

    /**
     * Deletes the element under the window and places the window
     * over the next element.
     * @return Object the old element under the window.
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * before the first element or the linked list is empty.
    **/
    public Object delete(WindowLinked w) throws OutOfBounds
    {
      if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
      {
          if(after == w.link.successor.successor) //after is the next item after window
          {
              Object element = w.link.successor.item;
              w.link.successor = after;
              return element;
          }
          Object element = w.link.successor.item;
          w.link.successor.item = w.link.successor.successor.item;
          w.link.successor.successor = w.link.successor.successor.successor; // next item's successor
          return element;
      }
      else
          throw new OutOfBounds("delete(): window is outside of list.");
    }
}