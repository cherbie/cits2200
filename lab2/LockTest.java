import java.lang.Object;

public class LockTest {
  private static boolean toBoolean(String tf) {
      if(tf.equals("true")) {
          return true;
      }
      else return false;
  }

  private static boolean testLockInt(LockInt li, int comb) {
  //    double rand = Math.random();
      int randInt = comb%10 * 100;
      System.out.println(randInt + "\n");

      //ATTEMPT TO OPEN INCORRECT AND CORRECT ELEMENT
      boolean attemptToOpen = li.open(randInt);
      assert attemptToOpen == false : "opened on incorrect combination. (INT).";
      attemptToOpen = li.open(comb);
      assert attemptToOpen == true : "did not open on correct combination. (INT)";

      //ATTEMPT TO CHANGE COMBINATION AND OPEN
      boolean attChange = li.changeCombo(randInt, randInt);
      assert attChange == false : "changed combination on incorrect key entered. (INT). ";
      attChange = li.changeCombo(comb, randInt);
      assert attChange == true : "could not change key. (INT).";

      //ATTEMPT TO CLOSE THE LOCK
      attChange = li.close();
      assert attChange == true : "Unable to close the lock. (INT)";

      return true;
  }

  private static boolean testLockString(LockString ls, int comb) {
    int randInt = comb%10 * 100;
    System.out.println(randInt + "\n");

    //ATTEMPT TO OPEN INCORRECT AND CORRECT ELEMENT
    boolean attemptToOpen = ls.open(randInt);
    assert attemptToOpen == false : "opened on incorrect combination. (STRING).";
    attemptToOpen = ls.open(comb);
    assert attemptToOpen == true : "did not open on correct combination. (STRING)";

    //ATTEMPT TO CHANGE COMBINATION AND OPEN
    boolean attChange = ls.changeCombo(randInt, randInt);
    assert attChange == false : "changed combination on incorrect key entered. (INT). ";
    attChange = ls.changeCombo(comb, randInt);
    assert attChange == true : "could not change key. (STRING).";

    //ATTEMPT TO CLOSE THE LOCK
    attChange = ls.close();
    assert attChange == true : "Unable to close the lock. (STRING)";

    return true;
  }

  private static void testMain(int int_comb, boolean tf) {
      System.out.println("->" + tf + " \\ " + int_comb);

      //INTEGER
      Lock li = new LockInt(345, true);
      li.changeCombo(345, 777);
      if (li.open(777)) System.out.println("Hey, I'm in!");
      else System.out.println("Error\n");

      //STRING
      Lock ls = new LockString(345, true);
      System.out.println(ls.changeCombo(345, 777));
      if (ls.open(777)) System.out.println("Hey, I'm in!");
      else System.out.println("Error\n");
/*
      if( !testLockInt(li, int_comb) ) System.out.println("LockInt failed\n");
      else if( !testLockString(ls, int_comb) ) System.out.println("LockString failed\n");
      else System.out.println("Success!\n");
*/
      Lock myLock;
      myLock = new LockInt(345, true);
      myLock.changeCombo(345, 777);
      if (myLock.open(777)) System.out.println("Hey, I'm in!");
      else System.out.println("Error\n");
  }

  public static void main(String[] args) {
    if(args.length < 2) {
      System.out.println("Need to specify combination int & true of false.\n");
    }
    else if(args.length == 2){
      int c = Integer.parseInt(args[0]); //covert command line argument to int
      boolean tf = Boolean.valueOf(args[1]); //convert command line argument to boolean
      if( c < 0 || c > 999) System.out.println("integer between 000 - 999 is needed");
      else testMain(c, tf);
    }
  }
}
