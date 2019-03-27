class StackLinkedTest
{
  private static void testStackLink()
  {
      String[] values = {"0","1","2","3","4","5","6","7", "EXTRA"};
      int size = values.length-1;
      StackLinked sl = new StackLinked(); //StackLinked Object


      //TEST IF NEW STACK IS INITIALISED AS EMPTY.
      assert !sl.isEmpty() : "Error: new stack is not initialised to empty.";

      //PUSH OBJECT TO THE LINKED STACK
      sl.push(values[0]);
      System.out.println(values[0] + " == " + sl.examine());
      assert values[0]==sl.examine() : "Error: error with stack examination.";
      for(int i = 1; i < size; i++)
      {
          sl.push(values[i]);
          System.out.println("top value = " + sl.examine());
          assert values[i]==sl.examine() : "Error: error with stack examination.";
      }
      System.out.println("Successfully pushed all Object[] elements to the stack.");

      //EXAMINE STACK ELEMENT GETTING POPPED
      String pop_str = ""; //String Object
      pop_str = (String) sl.examine();
      System.out.println("top --> " + pop_str);
      String f_st = (String) sl.pop();
      assert pop_str == f_st : "Error: examined element and popped element are not equivalent.";
      String s_nd = (String) sl.pop();
      System.out.println(s_nd);
      assert pop_str != s_nd : "Error: consecutive pops return the same Objects.";
      System.out.println("Successful stack.pop()");

      //EXAMINE SUCCESSIVE POPS
      for(int i = size-3;i >= 0; i--)
      {
          String s = (String) sl.pop();
          System.out.println("-> " + s + "\n");
      }
  }

    public static void main(String[] args)
    {
        assert args.length == 1 : "Incorrect number of command line arguments";
        testStackLink();
        System.out.println("Successful implementation of a linked stack!\n");
    }
}
