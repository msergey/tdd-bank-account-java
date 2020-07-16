package org.xpdojo.bank;

public class Account {
    private int balance = 0;

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
}
