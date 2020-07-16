package org.xpdojo.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    public void newBalancesShouldHaveZeroBalance() {
        Account account = new Account();
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void depositAmountToSetTheBalance() {
        Account account = new Account();
        account.deposit(10);
        assertThat(account.getBalance()).isEqualTo(10);
    }

    @Test
    public void depositMultipleAmountsToIncreaseTheBalance() {
        Account account = new Account();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.getBalance()).isEqualTo(30);
    }

    @Test
    public void withdrawMultipleAmountsToDecreaseTheBalance() {
        Account account = new Account();
        account.deposit(100);
        account.withdraw(10);
        account.withdraw(20);
        assertThat(account.getBalance()).isEqualTo(70);
    }

    @Test
    public void withdrawExactAmountOfBalanceShouldBePossible() {
        Account account = new Account();
        account.deposit(100);
        account.withdraw(100);
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void withdrawGreaterBalanceThanExistingShouldThrowException() {
        Account account = new Account();
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(101);
        });
    }

    @Test
    public void transferBalancesBetweenTwoAccounts() {
        Account account1 = new Account();
        account1.deposit(100);
        Account account2 = new Account();
        account2.deposit(100);
        account1.transfer(20, account2);

        assertThat(account1.getBalance()).isEqualTo(80);
        assertThat(account2.getBalance()).isEqualTo(120);
    }

    @Test
    public void transferBalanceHigherThanExistingBalanceShouldThrowExeption() {
        Account account1 = new Account();
        account1.deposit(100);
        Account account2 = new Account();
        account2.deposit(200);

        assertThrows(IllegalArgumentException.class, () -> {
            account1.transfer(120, account2);
        });
    }
}
