public interface Lock {

    /**If the combination is correct, change the combination key.
     * @param oldCom - the current Combination
     * @param newCom - the new combination
     * @return true if the old combination was correct (and hence the
     * combination has been changed to the new one)
    */
    public boolean changeCombo(int oldCom, int newCom);

    /**Close the lock
     * @param void
     * @return true
     */
    public boolean close();

    /**If the combination is correct, open the lock
     * @param combo - the attempted Combination
     * @return true if the combination is correct, false otherwise
     */
    public boolean open(int combo);
}
