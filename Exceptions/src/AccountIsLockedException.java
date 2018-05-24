import java.util.*;

public class AccountIsLockedException extends Exception{
    private Date datetimeunlock;
    public Date getDatetimeunlock() { return datetimeunlock;}

    public AccountIsLockedException(String message, Date datetimeunlock) {
        super(message);
        this.datetimeunlock = datetimeunlock;
    }
}
