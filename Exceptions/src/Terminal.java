public interface Terminal {
    int checkAccount(Account account) throws AccountIsLockedException, SecurityException, NullPointerException;
    int getMoney(int money) throws Exception;
    int putMoney(int money);
}
