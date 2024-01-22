package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankBTest {

    @Test
    public void shouldTransferFromSavingToCredit() {
        Bank bank = new Bank();
        SavingAccount savingAccount = new SavingAccount(
                10_000,
                100,
                1_000_000,
                5
        );
        CreditAccount creditAccount = new CreditAccount(
                1_000,
                10_000,
                5
        );
        bank.transfer(savingAccount, creditAccount, 1_000);

        Assertions.assertEquals(9_000, savingAccount.getBalance());
        Assertions.assertEquals(2_000, creditAccount.getBalance());
    }

    @Test
    public void shouldTransferFromCreditToSaving() {
        Bank bank = new Bank();
        SavingAccount savingAccount = new SavingAccount(
                10_000,
                100,
                1_000_000,
                5
        );
        CreditAccount creditAccount = new CreditAccount(
                1_000,
                10_000,
                5
        );
        bank.transfer(creditAccount, savingAccount, 1_000);

        Assertions.assertEquals(11_000, savingAccount.getBalance());
        Assertions.assertEquals(0, creditAccount.getBalance());
    }

    @Test
    public void shouldTransferFromSavingToSaving() {
        Bank bank = new Bank();
        SavingAccount savingAccount1 = new SavingAccount(
                10_000,
                100,
                1_000_000,
                5
        );
        SavingAccount savingAccount2 = new SavingAccount(
                5_000,
                100,
                1_000_000,
                5
        );

        bank.transfer(savingAccount1, savingAccount2, 2_000);

        Assertions.assertEquals(8_000, savingAccount1.getBalance());
        Assertions.assertEquals(7_000, savingAccount2.getBalance());
    }

    @Test
    public void shouldTransferFromCreditToCredit() {
        Bank bank = new Bank();
        CreditAccount creditAccount1 = new CreditAccount(
                0,
                10_000,
                5
        );
        CreditAccount creditAccount2 = new CreditAccount(
                1_000,
                10_000,
                5
        );

        bank.transfer(creditAccount2, creditAccount1, 500);

        Assertions.assertEquals(500, creditAccount1.getBalance());
        Assertions.assertEquals(500, creditAccount2.getBalance());
    }

    @Test
    public void shouldTransferFromOneSavingToItself() {
        Bank bank = new Bank();
        SavingAccount savingAccount = new SavingAccount(
                5_000,
                100,
                1_000_000,
                5
        );

        bank.transfer(savingAccount, savingAccount, 1_000);

        Assertions.assertEquals(5_000, savingAccount.getBalance());
    }

    @Test
    public void shouldNOTTransferFromEmptySavingAccount() {
        Bank bank = new Bank();
        SavingAccount savingAccount1 = new SavingAccount(
                0,
                0,
                1_000_000,
                5
        );

        SavingAccount savingAccount2 = new SavingAccount(
                1_000,
                100,
                1_000_000,
                5
        );

        bank.transfer(savingAccount1, savingAccount2, 1_000);

        Assertions.assertEquals(0, savingAccount1.getBalance());
        Assertions.assertEquals(1_000, savingAccount2.getBalance());
    }

    @Test
    public void shouldNOTTransferFromEmptyCreditAccountMoreThanLimit() {
        Bank bank = new Bank();
        CreditAccount creditAccount = new CreditAccount(
                0,
                5_000,
                5
        );

        SavingAccount savingAccount = new SavingAccount(
                1_000,
                100,
                10_000,
                5
        );

        bank.transfer(creditAccount, savingAccount, 6_000);

        Assertions.assertEquals(0, creditAccount.getBalance());
        Assertions.assertEquals(1_000, savingAccount.getBalance());
    }
}
