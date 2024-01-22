package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

// тесты на пополнение баланса

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldNotAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                10,
                5_000,
                15
        );

        account.add(-1_000);

        Assertions.assertEquals(10, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // тесты на оплату

    @Test
    public void testPositivePay() {
        CreditAccount account = new CreditAccount(
                4_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void testNegativePay() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.pay(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void testZeroPay() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void testWhenOverLimit() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        account.pay(8_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }


    // тесты на годовую ставку

    @Test
    public void testRateWhenPositiveBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void testRateWhenNegativeBalance() {
        CreditAccount account = new CreditAccount(
                500,
                5_000,
                15
        );
        account.pay(670);

        Assertions.assertEquals(-25, account.yearChange());
    }

    @Test
    public void testRateWhenZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void testWhenZeroRate() {
        CreditAccount account = new CreditAccount(
                2_000,
                5_000,
                0
        );

        Assertions.assertEquals(0, account.yearChange());
    }


    // тесты исключений

    @Test
    public void testIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(2_000, 5_000, -20));
    }

    @Test
    public void testNegativeCreditLimitException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(2_000, -5_000, 20));
    }

    @Test
    public void testNegativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(-2_000, 5_000, 20));
    }
}
