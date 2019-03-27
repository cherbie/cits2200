
public class StackBlockTest
{
    private static void testStackBlock()
    {
        String[] values = {"0","1","2","3","4","5","6","7", "EXTRA"};
        int size = 3;

        StackBlock sb = new StackBlock(size); //StackBlock Object


        //TEST IF NEW STACK IS INITIALISED AS EMPTY.
        assert !sb.isEmpty() : "Error: new stack is not initialised to empty.";

        //PUSH OBJECT TO THE STACK
        sb.push(values[0]);
        System.out.println(sb.examine());
        assert values[0]==sb.examine() : "Error: error with stack examination.";
        System.out.println( size + "\n");
        for(int i = 1; i < size; i++)
        {
            System.out.println(i + " - " + sb.isFull() + "\n");
            sb.push(values[i]);
            System.out.println("top value = " + sb.examine());
            assert values[i]==sb.examine() : "Error: error with stack examination.";
        }
        System.out.println("Successfully pushed all Object[] elements to the stack.");

        //EXAMINE THE LAST AND TOP MOST STACK ELEMENT.
        assert sb.examine() == values[size-1] : "Error: Top element of stack != last element of Object[].";
        assert sb.isFull() : "Error: Stack is not full.";

        //EXAMINE STACK ELEMENT GETTING POPPED
        String pop_str = ""; //String Object
        pop_str = (String) sb.examine();
        assert pop_str == sb.pop() : "Error: examined element and popped element are not equivalent.";
        assert pop_str != sb.pop() : "Error: consecutive pops return the same Objects.";
        System.out.println("Successful stack.pop()");

        //EXAMINE SUCCESSIVE POPS
        for(int i = size-1;i >= 0; i--)
        {
            String s = (String) sb.pop();
            System.out.println("-> " + s + "\n");
        }
    }

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Error: Insufficient input command line arguments specified.");
            return;
        }
        //int size = Integer.parseInt(args[0]);
        testStackBlock();
        System.out.println("Done!\n");
    }
}
