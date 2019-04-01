import CITS2200.*;

//include class WindowBlock

public class ListBlock<E>
{
    //FIELDS
    private Object[] list;
    private int afterLast;
    private int beforeFirst;

    //-------------- CONSTRUCTORS
    /**
     * Initialises an empty list with two associated window positions
     * before first and after last
    **/
    public ListBlock(int size)
    {
        list = new Object[size];
        afterLast = 0;
        beforeFirst = -1;
    }

    //-------------- CHECKERS
    /**
     * Check if list is empty
     * @return true if the list is empty, false otherwise
    **/
    public boolean isEmpty()
    {
        return (afterLast - beforeFirst) == 1;
    }

    public boolean isFull()
    {
        return (afterLast-beforeFirst) >= list.length+1;
    }

    /**
     * Check if window is over the "before first" position
     * @return true if window is over the before first position
    **/
    public boolean isBeforeFirst(WindowBlock w)
    {
        return w.index == beforeFirst;
    }

    /**
     * Check if window is over the "after last" position
     * @return true if window is over the after last position
     **/
    public boolean isAfterLast(WindowBlock w)
    {
        return w.index == list.length;
    }

    //--------- MANIPULATORS
    /**
     * Initialises window to the before first position
     * @param w WindowBlock
    **/
    public void beforeFirst(WindowBlock w)
    {
        w.index = beforeFirst;
    }

    /**
     * Initialises window to the after last position
     * @param w WindowBlock
    **/
    public void afterLast(WindowBlock w)
    {
        w.index = afterLast;
    }

    /**
     * Moves the window to the next window position
     * @param w WindowBlock
     * @throws Overflow if the window is over the after last position
    **/
    public void next(WindowBlock w) throws Overflow
    {
        if(!isAfterLast(w))
        {
            w.index++; //next window position
        }
        else
        {
            throws new Overflow("Window in 'after last' position.");
        }
    }

    /**
     * Moves the window to the previous window position
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
    **/
    public void previous(WindowBlock w) throws Underflow
    {
        if(!isBeforeFirst(w))
        {
            w.index--;
        }
        else
        {
            throws new Underflow("Window in 'before first' position.");
        }
    }

    /**
     * Extra element is added to the ListBlock after the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws Overflow if the window is over the after last position
    **/
    public void insertAfter(E e, WindowBlock w) throws Overflow
    {
        if(!isFull())
        {
            if(!isAfterLast(w))
            {
                list[w.index+1] = e;
                afterLast++;
            }
            else
            {
                throws new Overflow("Window is over the after last position.");
            }
        }
        else
            throw new Overflow("Block implementation of list is full.");
    }

    /**
     * An extra element is added to the list before the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
    **/
    public void insertBefore(E e, WindowBlock w) throws Underflow
    {
        if(!isFull())
        {
            if(!isBeforeFirst(w))
            {
                for(int i = after-1; i >= w.index; i--)
                {
                    list[i+1] = list[i];
                }
                afterLast++;
                list[w.index] = e;
                w.index++;
            }
            else
                throw new Underflow("Window is over the 'before first' position.");
        }
        else
          throw new Underflow("Block implementation of list is full.");
    }

    /**
     * Examines the element in the window
     * @return E element under the window
     * @throws Underflow if the window is over the before first position
     * @throws Overflow if the window is over the after last position
     * @param w WindowBlock
    **/
    public E examine(WindowBlock w) throws Underflow, Overflow
    {
        if(!isBeforeFirst(w))
        {
            if(!isAfterLast(w))
            {
                return list[w.index];
            }
            else
            {
                throw new Overflow("Window is over the 'after last' position.");
            }
        }
        else
        {
            throw Underflow("Window is over the 'before first' position.");
        }
    }

    /**
     * Replaces the element under the window
     * @return the old element under the window of type E
     * @param e Generic class
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
     * @throws Overflow if the window is over the after last position
    **/
    public E replace(E e, WindowBlock w) throws Underflow, Overflow
    {
        if(!isEmpty())
        {
            if(!isBeforeFirst(w))
            {
                if(!isAfterLast(w))
                {
                    E element = list[w.index];
                    list[w.index] = e;
                    return element;
                }
                else
                    throw new OverFlow("Window is in the 'after last' position.");
            }
            else
                throw new Underflow("Window is in the 'before first' position.");
        }
        else
            throw new Overflow("List is empty.");
    }

    /**
     * Deletes the element under the window and places the window
     * over the next element.
     * @return E the old element under the window.
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
     * @throws Overflow if the window is over the after last position.
    **/
    public E delete(WindowBlock w) throws Underflow, Overflow
    {
      if(!isEmpty())
      {
          if(!isBeforeFirst(w))
          {
              if(!isAfterLast(w))
              {
                  E element = list[w.index];
                  for(int i = afterLast-1; i >= w.index; i--)
                  {
                      
                  }
                  list[w.index] = e;
                  return element;
              }
              else
                  throw new OverFlow("Window is in the 'after last' position.");
          }
          else
              throw new Underflow("Window is in the 'before first' position.");
      }
      else
          throw new Overflow("List is empty.");
    }
}
