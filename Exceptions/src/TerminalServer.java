import java.util.*;

public class TerminalServer {
    public TerminalServer() { }

    static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    Exception generateExceptions()
    {
        int rnd = rnd(0, 30);

        if (rnd <= 10) return new ArithmeticException();
        else if (rnd <= 20) return new UnsupportedOperationException();

        return(new TerminalServerException("Проблемы подключения к сети"));
    }
    void lockAccount(Account account)
    {
        account.setBlockDateTime(new Date());
    }

    int getAccountBalance(Account account) throws AccountIsLockedException {
        // проверить, заблокирован ли аккаунт
        if (account.getBlockDateTime() != null
                && ((System.currentTimeMillis() - account.getBlockDateTime().getTime()) <= UserInterface.accoutLockMills)) {
            throw new AccountIsLockedException(
                    "Счет заблокирован " + account.getBlockDateTime().toString() + " неправильно введен pin",
                    new Date(account.getBlockDateTime().getTime() + UserInterface.accoutLockMills));
        } else return account.getSum();
    }
}
