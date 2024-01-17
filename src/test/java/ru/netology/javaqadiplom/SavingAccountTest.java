package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // -1. Initial Test (функция пополнения счета + баланс в пределах от мин до макс)
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }


    // Проверяем параметры конструктора, условия и исключения по параметрам

    @Test
    public void shouldThrowExceptionWhenBalanceLessThanMin() { // -2. Должна выкидываться ошибка, т.к. баланс меньше минимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    100,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldNonThrowExceptionWhenBalanceEqualsMin() { // +3. НЕ должна выкидываться ошибка, т.к. баланс может быть равен минимальному значению

        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(account.getBalance(), account.getMinBalance());
    }

    @Test
    public void shouldThrowExceptionWhenBalanceMoreThanMax() { // -4. Должна выкидываться ошибка, т.к. баланс больше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldNotThrowExceptionWhenBalanceEqualsMax() { // +5. НЕ должна выкидываться ошибка, т.к. баланс может быть равен максимальному значению

        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(account.getBalance(), account.getMaxBalance());
    }

    @Test
    public void shouldThrowExceptionWhenMinMoreThanMax() { // -6. Должна выкидываться ошибка, т.к. минимальное значение больше максимального

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    10_000,
                    1_000,
                    5
            );
        });
    }

    @Test
    public void shouldNotThrowExceptionWhenMaxEqualsMin() { // +7. НЕ должна выкидываться ошибка, т.к. минимальное значение может равняться максимальному и балансу

        SavingAccount account = new SavingAccount(
                10_000,
                10_000,
                10_000,
                5
        );
        Assertions.assertEquals(account.getMinBalance(), account.getMaxBalance());

    }

    @Test
    public void shouldThrowExceptionWhenBalanceIsNegative() { // -8. Должна выкидываться ошибка, т.к. баланс отрицательный

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -1_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    @Test
    public void shouldThrowExceptionBecauseRateIsNegative() { // +9. Должна выкидываться ошибка, т.к. ставка отрицательная

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    1_000,
                    10_000,
                    -3
            );
        });
    }

// ПРОВЕРКА ФУНКЦИИ PAY

    @Test
    public void shouldPayWhenAmountMoreThenBalance() { // +10. Проверка функции, когда платеж не превышает баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        account.pay(1_000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenBalanceAndAmountEquals() { // +11. Проверка функции, когда платеж равен балансу, но не превышает минимальное значение
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                10
        );

        account.pay(2_000);

        Assertions.assertEquals(2_000 - 2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountMoreThanBalance() { // ---11. Платеж больше баланса, ничего не должно произойти
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenBalanceAfterPaymentBecomeLessThanMinBalance() { // ---12. Платеж больше баланса, после покупки баланс меньше минимального значения, должно выбрасываться исключение
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        account.pay(1_500);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountZero() { // +13. Платеж равен нулю, ничего не должно произойти
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayWhenAmountNegative() { // +14. Платеж меньше нуля, ничего не должно произойти
        SavingAccount account = new SavingAccount(
                1_500,
                1_000,
                10_000,
                10
        );

        account.pay(-100);

        Assertions.assertEquals(1_500, account.getBalance());
    }

}

