import java.util.Date;
import java.util.regex.Pattern;
import javafx.util.*;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    // private boolean pinCorrect = false;

    public TerminalImpl() {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator();
    }

    public boolean checkPin(String pinString) {
        Pattern pattern = Pattern.compile("\\d{4}");
        return pattern.matcher(pinString).matches();

        // this.pinCorrect = pattern.matcher(pinString).matches();
        // return this.pinCorrect;
    }

    void lockAccount(Account account)
    {
        this.server.lockAccount(account);
    }

    public Pair<String, Integer> getUserAccountBalance(Account account)
    {
        Integer sum = 0;
        String exceptionString = "";

        try {
            sum =  this.checkAccount(account);
        }
        catch (SecurityException ex)
        { exceptionString = "Попытка проверить состояние счета без ввода pin"; }
        catch (AccountIsLockedException ex)
        {
            exceptionString = ex.getMessage() + " Дата разблокировки: " + ex.getDatetimeunlock().toString();
        }
        catch (NullPointerException ex)
        {
            exceptionString = "Баланс счета не инициализирован";
        }
        return new Pair<String, Integer>(exceptionString, sum);
    }

    @Override
    public int checkAccount(Account account) throws AccountIsLockedException, SecurityException, NullPointerException {
        if (!UserInterface.pinEntered) throw new SecurityException();

        Integer sum = 0;
        try {
            sum = this.server.getAccountBalance(account);
        } catch (AccountIsLockedException | NullPointerException ex ) {
            throw ex;
        }

        return sum;
    }

    public String checkUserAccount(Account account){
        try {
            int accountBalance = this.checkAccount(account);
        } catch (SecurityException ex)
        { return "Попытка проверить состояние счета без ввода pin"; }
        catch (AccountIsLockedException ex)
        { return ex.getMessage() + " Дата разблокировки: " + ex.getDatetimeunlock().toString(); }
        catch (NullPointerException ex)
        { return "На счете отсутствуют денежные средства"; }

        return null;
    }

    @Override
    public int getMoney(int money) throws Exception {
        if (!UserInterface.pinEntered) throw new SecurityException();
        if (money % 100 > 0) throw new IllegalArgumentException();

        throw this.server.generateExceptions();
    }

    public String getUserMoney(String moneyString)
    {
        String exceptionString = "";

        try {
            int money = this.getMoney(Integer.parseInt(moneyString));
        }
        catch (NumberFormatException ex)
        { exceptionString = "Введены неверные данные";}
        catch (IllegalArgumentException ex)
        { exceptionString = "Сумма не кратна 100";}
        catch (ArithmeticException ex)
        { exceptionString = "Неправильная арифметическая операция";}
        catch (UnsupportedOperationException ex)
        { exceptionString = "Обнаружена неподдерживаемая операция";}
        catch (TerminalServerException ex)
        { exceptionString = "Ошибка при доступе к серверу:" + ex.getMessage(); }
        catch (Exception e) {
            exceptionString = "Произошла ошибка при снятии денежных средств. Повторите попытку позже.";
        }

       return exceptionString;
    }

    @Override
    public int putMoney(int money) {
        if (!UserInterface.pinEntered) throw new SecurityException();
        return 0;
    }
}
