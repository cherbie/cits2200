import java.lang.Object;

public class LockString implements Lock {
  //FIELDS
  private String c; //combination
  private boolean exist; //switch whether string lock is open

  public LockString(int int_comb, boolean b) {
      String str_comb = String.valueOf(int_comb);
      c = str_comb.toString();
      exist = true;
  }

  /**
   * @param oldCom - the current Combination
   * @param newCom - the new combination
   * @return true if the old combination was correct (and hence the
   * combination has been changed to the new one)
   **/
  public boolean changeCombo(int int_oldCom, int int_newCom){
      String str_oldCom = String.valueOf(int_oldCom);
      if(c.equals(str_oldCom)) {
          String str_newCom = String.valueOf(int_newCom);
          c = str_newCom;
          return true;
      }
      else return false;
  }

  /**
   * Close the lock
   * @param void
   * @return true
  **/
  public boolean close() {
      exist = false;
      return !exist;
  }

  /**
   * If the combination is correct, open the lock
   * @param combo - the attempted Combination
   * @return true if the combination is correct, false otherwise
   **/
  public boolean open(int int_comb) {
      String str_comb = String.valueOf(int_comb);
      if(c.equals(str_comb)){
          exist=true;
          return exist;
      }
      else return false;
  }

}
