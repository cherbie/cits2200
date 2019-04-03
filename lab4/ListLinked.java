import CITS2200.*;

public class ListLinked<E> implements CITS2200.List
{
    //FIELDS
    private Link before;
    private Link after;

    //-------------- CONSTRUCTORS
    /**
     * Initialises an empty list.
    **/
    public ListLinked<E>()
    {
        after = new Link(null, null);
        before = new Link(null, after); //linked to after
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
        return w.link == before;
    }

    /**
     * Check if window is over the "after last" position
     * @return true if window is over the after last position
     **/
    public boolean isAfterLast(WindowLinked w)
    {
        return w.link == after;
    }

    //--------- MANIPULATORS
    /**
     * Initialises window to the before first position
     * @param w WindowLinked
    **/
    public void beforeFirst(WindowLinked w)
    {
        w.link = before;
    }

    /**
     * Initialises window to the after last position
     * @param w WindowLinked
    **/
    public void afterLast(WindowLinked w)
    {
        w.link = after;
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
        {
            throw new OutOfBounds("Window is past the end of the list.");
        }
    }

    /**
     * Moves the window to the previous window position
     * @param w WindowLinked
     * @throws OutOfBounds if the window is before the start of the list
    **/
    public void previous(WindowLinked w) throws OutOfBounds
    {
        if(!isBeforeFirst(w))
        {
            Link first = before.successor; //first element in the list.
            Link prev = null;
            while(first.successor != w.link)
            {
                prev = first;
                first = first.successor;
                if(first == after)
                    throw new OutOfBounds("Could not navigate to previous element.");
            }
            w.link = prev; //set window to the previous element.
        }
        else
        {
            throw new OutOfBounds("Window is before the start of the list.");
        }
    }

    /**
     * Extra element is added to the ListBlock after the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws OutOfBounds if the window is past the end of the list
    **/
    public void insertAfter(E e, WindowBlock w) throws OutOfBounds
    {
        if(!isAfterLast(w))
        {
            for(int i = afterLast-1; i > w.index; i--)
            {
                list[i+1] = list[i];
            }
            list[w.index+1] = e;
            afterLast++;
        }
        else
            throw new OutOfBounds("Window is past the end of the list.");
    }

    /**
     * An extra element is added to the list before the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws OutOfBounds if the window is before the start of the list
    **/
    public void insertBefore(E e, WindowBlock w) throws OutOfBounds
    {
        if(!isBeforeFirst(w))
        {
            for(int i = afterLast-1; i >= w.index; i--)
            {
                list[i+1] = list[i];
            }
            afterLast++;
            list[w.index] = e;
            w.index++;
        }
        else
            throw new OutOfBounds("Window is before the start of the list");
    }

    /**
     * Examines the element in the window
     * @return E element under the window
     * @throws OutOfBounds if the window is outside the list
     * @param w WindowLinked
    **/
    public E examine(WindowLinked w) throws OutOfBounds
    {
        if(!isEmpty())
        {
            if(!isBeforeFirst(w))
            {
                if(!isAfterLast(w))
                {
                    return w.link.item;
                }
                else
                    throw new OutOfBounds("Window is over the 'after last' position.");
            }
            else
                throw new OutOfBounds("Window is over the 'before first' position.");
        }
        else
            throw new OutOfBounds("List is empty.");
    }

    /**
     * Replaces the element under the window
     * @return the old element under the window of type E
     * @param e Generic class
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * after last positions or empty.
    **/
    public E replace(E e, WindowLinked w) throws OutOfBounds
    {
        if(!isEmpty())
        {
            if(!isBeforeFirst(w))
            {
                if(!isAfterLast(w))
                {
                    E element = (E) w.link.item;
                    w.link.item = e;
                    return element;
                }
                else
                    throw new OutOfBounds("Window is after the last element.");
            }
            else
                throw new OutOfBounds("Window is before the first element.");
        }
        else
            throw new OutOfBounds("Linked list is empty.");
    }

    /**
     * Deletes the element under the window and places the window
     * over the next element.
     * @return E the old element under the window.
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * before the first element or the linked list is empty.
    **/
    public E delete(WindowLinked w) throws OutOfBounds
    {
      if(!isEmpty())
      {
          if(!isBeforeFirst(w))
          {
              if(!isAfterLast(w))
              {
                  E element = (E) w.link.item;
                  Link next = w.link.successor;
                  previous(w);
                  w.link.successor = next;
                  return element;
              }
              else
                  throw new OutOfBounds("Window is after the last element");
          }
          else
              throw new OutOfBounds("Window is before the first element");
      }
      else
          throw new OutOfBounds("Linked list is empty.");
    }
}
