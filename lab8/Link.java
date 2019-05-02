public class Link<E>
{
    public E element;
    public int priority;
    public Link<E> next;

    public Link(E e, int p, Link<E> n)
    {
        this.element = e;
        this.priority = p;
        this.next = n;
    }
}