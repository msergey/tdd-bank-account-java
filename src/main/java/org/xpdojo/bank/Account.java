package org.xpdojo.bank;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Account {
    private final DateTimeFormatter balanceSlipFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private final ZoneId balanceSlipZoneId = ZoneId.of("America/New_York");

    private final Clock clock;

    private int balance = 0;

    public Account(Clock clock) {
        this.clock = clock;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("balance should be greater than amount");
        }
        balance -= amount;
    }

    public void transfer(int amount, Account account) {
        this.withdraw(amount);
        account.deposit(amount);
    }

    public String getBalanceAccountSlip() {
        LocalDateTime localDate = clock.now(balanceSlipZoneId);
        return localDate.format(balanceSlipFormatter) + "    " + "$" + balance;
    }
}
