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
        return w.index == afterLast;
    }

    //--------- MANIPULATORS
    /**
     * Initialises window to the before first position
     * @param w WindowBlock
    **/
    public void beforeFirst(WindowBlock w)
    {

    }

    /**
     * Initialises window to the after last position
     * @param w WindowBlock
    **/
    public void afterLast(WindowBlock w)
    {

    }

    /**
     * Moves the window to the next window position
     * @param w WindowBlock
     * @throws Overflow if the window is over the after last position
    **/
    public void next(WindowBlock w) throws Overflow
    {

    }

    /**
     * Moves the window to the previous window position
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
    **/
    public void previous(WindowBlock w) throws Underflow
    {

    }

    /**
     * Extra element is added to the ListBlock after the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws Overflow if the window is over the after last position
    **/
    public void insertAfter(E e, WindowBlock w) throws Overflow
    {

    }

    /**
     * An extra element is added to the list before the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
    **/
    public void insertBefore(E e, WindowBlock w) throws Underflow
    {

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

    }

    /**
     * Replaces the element under the window
     * @return E the old element under the window
     * @param e Generic class
     * @param w WindowBlock
     * @throws Underflow if the window is over the before first position
     * @throws Overflow if the window is over the after last position
    **/
    public E replace(E e, WindowBlock w) throws Underflow, Overflow
    {

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

    }
}
