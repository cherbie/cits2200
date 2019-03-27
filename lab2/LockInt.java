public class LockInt implements Lock {
    //FIELDS
    private int c; //combination
    private boolean exist; //lock exists or not

    public LockInt(int combination, boolean e) {
        c = combination;
        exist = true;
    }

    /**
     * @param oldCom - the current Combination
     * @param newCom - the new combination
     * @return true if the old combination was correct (and hence the
     * combination has been changed to the new one)
     **/
    public boolean changeCombo(int oldCom, int newCom){
        if(!exist) return false;
        if(oldCom == c) {
            c = newCom;
            return true;
        }
        else return false;
    }

    /**
     * Close the lock
     * @param void
     * @return public void testName() throws Exception {

     }rue
    **/
    public boolean close(){
        exist = false; //close the lock
        return !exist;
    }

    /**
     * If the combination is correct, open the lock
     * @param combo - the attempted Combination
     * @return true if the combination is correct, false otherwise
     **/
    public boolean open(int combo) {
            if(combo == c)
            {
                exist = true; //open lock
                return exist;
            }
            return false;
    }
}
