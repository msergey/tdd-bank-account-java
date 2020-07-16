package org.xpdojo.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
