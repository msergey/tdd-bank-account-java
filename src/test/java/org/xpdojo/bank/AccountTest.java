package org.xpdojo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {

    private Clock clock;

    private Account newAccount() {
        return new Account(clock);
    }

    @BeforeEach
    public void beforeEach() {
        this.clock = mock(Clock.class);
    }

    @Test
    public void newBalancesShouldHaveZeroBalance() {
        Account account = newAccount();
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void depositAmountToSetTheBalance() {
        Account account = newAccount();
        account.deposit(10);
        assertThat(account.getBalance()).isEqualTo(10);
    }

    @Test
    public void depositMultipleAmountsToIncreaseTheBalance() {
        Account account = newAccount();
        account.deposit(10);
        account.deposit(20);
        assertThat(account.getBalance()).isEqualTo(30);
    }

    @Test
    public void withdrawMultipleAmountsToDecreaseTheBalance() {
        Account account = newAccount();
        account.deposit(100);
        account.withdraw(10);
        account.withdraw(20);
        assertThat(account.getBalance()).isEqualTo(70);
    }

    @Test
    public void withdrawExactAmountOfBalanceShouldBePossible() {
        Account account = newAccount();
        account.deposit(100);
        account.withdraw(100);
        assertThat(account.getBalance()).isEqualTo(0);
    }

    @Test
    public void withdrawGreaterBalanceThanExistingShouldThrowException() {
        Account account = newAccount();
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(101);
        });
    }

    @Test
    public void transferBalancesBetweenTwoAccounts() {
        Account account1 = newAccount();
        account1.deposit(100);
        Account account2 = newAccount();
        account2.deposit(100);
        account1.transfer(20, account2);

        assertThat(account1.getBalance()).isEqualTo(80);
        assertThat(account2.getBalance()).isEqualTo(120);
    }

    @Test
    public void transferBalanceHigherThanExistingBalanceShouldThrowExeption() {
        Account account1 = newAccount();
        account1.deposit(100);
        Account account2 = newAccount();
        account2.deposit(200);

        assertThrows(IllegalArgumentException.class, () -> {
            account1.transfer(120, account2);
        });
    }

    @Test
    public void getBalanceAccountSlipShouldReturnDateTimeBalance() {
        Account account = newAccount();
        account.deposit(23);

        LocalDateTime mockedDateTime = LocalDateTime.of(
                LocalDate.of(2020, 7, 16),
                LocalTime.of(10, 33, 43, 130)
        );

        when(clock.now(any())).thenReturn(mockedDateTime);

        assertThat(account.getBalanceAccountSlip()).isEqualTo("2020-07-16 10:33 AM    $23");
    }
}
