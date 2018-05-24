import java.util.*;
import java.io.*;
import javafx.util.*;

public class UserInterface {
    public static Integer accoutLockMills = 5000;
    public static boolean pinEntered = false;

    public static boolean pinEnter(TerminalImpl terminal) {
        System.out.println("Введите pin:");

        Scanner in = new Scanner(System.in);
        if (!terminal.checkPin(in.nextLine())) {
            System.out.println("Ошибка! pin код не верный");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TerminalImpl terminalImpl = new TerminalImpl();

        // Проверка состояния счета
        String resultString = terminalImpl.checkUserAccount(null);
        if (resultString != null) System.out.println(resultString);

        Account account = new Account(11111);
        int x = 1;
        do {
            boolean pinEnterResult = pinEnter(terminalImpl);
            if (pinEnterResult) break;

            if (x == 3)
                terminalImpl.lockAccount(account); // account.setBlockDateTime(new Date());
        } while (x++ < 3);

        pinEntered = true;

        // Проверка баланса
        Pair<String, Integer> accountBalance = terminalImpl.getUserAccountBalance(account);
        if (!accountBalance.getKey().isEmpty()) {
            System.out.println(accountBalance.getKey());
            System.exit(0);
        } else System.out.printf("Баланс счета = %d\n", accountBalance.getValue());

        // Если счет не заблокирован, попытка снять деньги
        System.out.println("Введите сумму для снятия денег:");
        Scanner in = new Scanner(System.in);
        resultString = terminalImpl.getUserMoney(in.nextLine());
        if (resultString != null) System.out.println(resultString);
    }
}
