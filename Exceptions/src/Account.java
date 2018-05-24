import java.util.*;
import java.time.*;

public class Account {
    private Integer accountID;
    private Date blockDateTime;
    private Integer sum;

    public Integer getAccountID()
    { return this.accountID; }

    public void setAccountID(Integer accountID)
    { this.accountID = accountID; }

    public Date getBlockDateTime()
    { return this.blockDateTime; }

    public void setBlockDateTime(Date blockDateTime)
    { this.blockDateTime = blockDateTime; }

    public Integer getSum()
    { return this.sum; }

    public void setSum(Integer sum)
    { this.sum = sum;}

    public Account(Integer accountID)
    {
        this.accountID = accountID;
        this.sum = 300;
    }
}
